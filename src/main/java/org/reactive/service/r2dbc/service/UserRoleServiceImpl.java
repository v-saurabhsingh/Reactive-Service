package org.reactive.service.r2dbc.service;

import org.reactive.service.r2dbc.entity.UserRole;
import org.reactive.service.r2dbc.repo.UserRoleRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Data
@Slf4j
@Service
public class UserRoleServiceImpl extends AbstractService implements UserRoleService{

    private final UserRoleRepository userRoleRepository;

    @Override
    public Mono<UserRole> saveUserRoleMapping(UserRole userRole){
        return userRoleRepository.save(userRole).doOnSubscribe(subscription -> log.info("UserRole persistence subscribed"));
    }

}
