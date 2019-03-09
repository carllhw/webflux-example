package io.github.carllhw.example.webflux.annotation;

import lombok.*;

/**
 * user
 *
 * @author carllhw
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private String id;

    private String name;

    private String email;
}
