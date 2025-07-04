package online.iamtom.stock_ticker.services;

import lombok.extern.slf4j.Slf4j;
import online.iamtom.stock_ticker.dto.WebsocketMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class NotificationService {

    public void sendMessage(Object message) {
        WebsocketMessage<?> websocketMessage = new WebsocketMessage<>(message);
        websocketMessage.setAction("SAVE");
        websocketMessage.setActionTS(LocalDateTime.now());

        log.info(String.valueOf(websocketMessage));
    }
}
