package org.reactive.service.r2dbc.service;

import org.reactive.service.constant.RoleEnum;
import org.reactive.service.r2dbc.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Flux<User> findAllUsers();

    Mono<User> saveUser(User user);

    Mono<User> findByUserId(Long id);

    Mono<User> saveUserWithRole(User user, RoleEnum roleEnum);
}
