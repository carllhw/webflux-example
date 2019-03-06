package io.github.carllhw.example.webflux.annotation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * user service
 *
 * @author carllhw
 */
@Service
public class UserService {

    private final Map<String, User> data = new ConcurrentHashMap<>();

    Flux<User> list() {
        return Flux.fromIterable(data.values());
    }

    Flux<User> getByIds(final Flux<String> ids) {
        return ids.flatMap(id -> Mono.justOrEmpty(data.get(id)));
    }

    Mono<User> getById(final String id) {
        return Mono.justOrEmpty(data.get(id))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException()));
    }

    Mono<User> createOrUpdate(final User user) {
        data.put(user.getId(), user);
        return Mono.just(user);
    }

    Mono<User> delete(final String id) {
        return Mono.justOrEmpty(data.remove(id));
    }
}
