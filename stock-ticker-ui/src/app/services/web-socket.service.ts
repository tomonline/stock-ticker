import { Injectable } from '@angular/core';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { Observable, timer } from 'rxjs';
import { retryWhen, delayWhen, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private socket$?: WebSocketSubject<any>;
  private reconnectAttempts = 0;
  private readonly maxReconnectAttempts = 10;
  private readonly reconnectDelay = 2000; // ms

  connect(url: string): Observable<any> {
    this.socket$ = webSocket(url);
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
