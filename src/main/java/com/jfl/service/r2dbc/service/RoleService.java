package com.jfl.service.r2dbc.service;

import com.jfl.service.mongo.Mongo;
import com.jfl.service.r2dbc.entity.Role;
import org.springframework.data.relational.core.sql.In;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoleService {


    Flux<Role> init();

    Flux<Role> findAllRoles();

    Mono<Role> saveRole(Role role);

    Mono<Role> findByRoleId(Long id);

    Mono<Role> findByRoleName(String roleName);

}
