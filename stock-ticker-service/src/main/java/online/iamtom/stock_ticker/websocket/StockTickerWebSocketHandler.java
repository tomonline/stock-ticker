package online.iamtom.stock_ticker.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
public class StockTickerWebSocketHandler extends TextWebSocketHandler {
    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
        sessions.remove(session);
    }

    public void broadcast(String message) {
        log.info("Broadcasting message to {} clients: {}", sessions.size(), message);
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(message));
                } catch (Exception ignored) {}
            }
        }
    }
}