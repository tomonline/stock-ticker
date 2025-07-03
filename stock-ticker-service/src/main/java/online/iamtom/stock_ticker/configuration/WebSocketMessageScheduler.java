package online.iamtom.stock_ticker.configuration;

import lombok.extern.slf4j.Slf4j;
import online.iamtom.stock_ticker.websocket.StockTickerWebSocketHandler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WebSocketMessageScheduler {
    private final StockTickerWebSocketHandler handler;

    public WebSocketMessageScheduler(StockTickerWebSocketHandler handler) {
        this.handler = handler;
    }

    @Scheduled(fixedRate = 5000)
    public void sendTimedMessage() {
        log.info("Sending timed message to WebSocket clients");
        handler.broadcast("Timed message: " + System.currentTimeMillis());
    }
}