package io.github.carllhw.example.webflux.functional;

import java.util.Objects;

import io.github.carllhw.example.webflux.annotation.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * rest client
 *
 * @author carllhw
 */
@Slf4j
public class RestClient {

    public static void main(final String[] args) {
        final User user = User.builder()
                .id("1")
                .name("Test")
                .email("test@example.org")
                .build();
        final WebClient client = WebClient.create("http://localhost:8080");
        final Mono<User> createdUser = client.post()
                .uri("/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .exchange()
                .flatMap(response -> response.bodyToMono(User.class));
        log.info(Objects.requireNonNull(createdUser.block()).toString());
    }
}
