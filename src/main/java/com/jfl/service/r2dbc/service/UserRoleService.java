package com.jfl.service.r2dbc.service;

import com.jfl.service.r2dbc.entity.UserRole;
import reactor.core.publisher.Mono;

public interface UserRoleService {

    Mono<UserRole> saveUserRoleMapping(UserRole userRole);

}
