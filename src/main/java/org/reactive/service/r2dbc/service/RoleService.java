package org.reactive.service.r2dbc.service;

import org.reactive.service.r2dbc.entity.Role;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoleService {


    Flux<Role> init();

    Flux<Role> findAllRoles();

    Mono<Role> saveRole(Role role);

    Mono<Role> findByRoleId(Long id);

    Mono<Role> findByRoleName(String roleName);

}
