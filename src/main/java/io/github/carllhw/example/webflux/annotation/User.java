package io.github.carllhw.example.webflux.annotation;

import lombok.*;

/**
 * user
 *
 * @author carllhw
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private String id;

    private String name;

    private String email;
}
