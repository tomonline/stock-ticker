package online.iamtom.stock_ticker.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WebsocketMessage<T> {

    private String action;
    private LocalDateTime actionTS;
    private T message;

    public WebsocketMessage(T message) {
        this.message = message;
    }
}
