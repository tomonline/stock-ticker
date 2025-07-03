package online.iamtom.stock_ticker.configuration;

import online.iamtom.stock_ticker.websocket.StockTickerWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@EnableScheduling
public class WebsocketConfig implements WebSocketConfigurer {

    @Bean
    public StockTickerWebSocketHandler stockTickerWebSocketHandler() {
        return new StockTickerWebSocketHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(stockTickerWebSocketHandler(), "/ws/stock-ticker")
                .setAllowedOrigins("*");
    }
}