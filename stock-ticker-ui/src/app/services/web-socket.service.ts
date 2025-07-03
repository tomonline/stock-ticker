import { Injectable } from '@angular/core';
import { Observable, timer } from 'rxjs';
import { delayWhen, retryWhen, tap } from 'rxjs/operators';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private socket$?: WebSocketSubject<any>;
  private reconnectAttempts = 0;
  private readonly maxReconnectAttempts = 10;
  private readonly reconnectDelay = 2000; // ms

  connect(url: string): Observable<any> {

    this.socket$ = webSocket({
      url: url,
      deserializer: e => e.data,  // Optional: parse JSON here if needed
      openObserver: {
        next: () => console.log('WebSocket connected'),
      },
      closeObserver: {
        next: () => console.warn('WebSocket disconnected'),
      }
    }); 
    
    return this.socket$.asObservable().pipe(
      retryWhen(errors =>
        errors.pipe(
          tap(() => {
            this.reconnectAttempts++;
            if (this.reconnectAttempts > this.maxReconnectAttempts) {
              throw new Error('Max reconnect attempts reached');
            }
            console.warn(`WebSocket disconnected. Attempting reconnect #${this.reconnectAttempts}`);
          }),
          delayWhen(() => timer(this.reconnectDelay))
        )
      )
    );
  }

  send(data: any): void {
    this.socket$?.next(data);
  }

  close(): void {
    this.socket$?.complete();
    this.reconnectAttempts = 0;
  }
}
