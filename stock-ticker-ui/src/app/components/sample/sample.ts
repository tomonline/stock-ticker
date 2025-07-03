import { Component } from '@angular/core';
import { Subscription } from 'rxjs';
import { WebSocketService } from '../../services/web-socket.service';

@Component({
  selector: 'app-sample',
  imports: [],
  templateUrl: './sample.html',
  styleUrl: './sample.scss'
})
export class Sample {

  message: string = '';

  private wsSubscription?: Subscription;

  constructor(private readonly wsService: WebSocketService) {}

  ngOnInit() {
    this.wsSubscription = this.wsService.connect('ws://localhost:8080/ws/stock-ticker').subscribe({
      next: (message) => {
        console.log('Received:', message);
        this.message = message; // Update the message to display in the template
      },
      error: (err) => {
        console.error('WebSocket error:', err);
      },
      complete: () => {
        console.log('WebSocket connection closed');
      }
    });
  }

  ngOnDestroy() {
    this.wsSubscription?.unsubscribe();
    this.wsService.close();
  }  

}
