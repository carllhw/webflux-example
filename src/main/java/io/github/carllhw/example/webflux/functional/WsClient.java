package io.github.carllhw.example.webflux.functional;

import java.net.URI;
import java.time.Duration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;

/**
 * WebSocket client
 *
 * @author carllhw
 */
@Slf4j
public class WsClient {

    public static void main(final String[] args) {
        final WebSocketClient client = new ReactorNettyWebSocketClient();
        client.execute(URI.create("ws://localhost:8080/echo"), session ->
                session.send(Flux.just(session.textMessage("Hello")))
                        .thenMany(session.receive().take(1).map(WebSocketMessage::getPayloadAsText))
                        .doOnNext(log::info)
                        .then())
                .block(Duration.ofMillis(5000));
    }
}
