package org.reactive.service.r2dbc.service;

import org.reactive.service.constant.RoleEnum;
import org.reactive.service.r2dbc.entity.Role;
import org.reactive.service.r2dbc.repo.RoleRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@Slf4j
@Service
public class RoleServiceImpl extends AbstractService implements RoleService {

    private final RoleRepository roleRepository;

    public Flux<Role> init(){
        return Flux.fromArray(RoleEnum.values()).flatMap(roleEnum -> {
            Role role=new Role();
            role.setId(roleEnum.getId());
            role.setName(roleEnum.name());
            role.setNew(true);
            role.setDescription(roleEnum.getDescription());
            return findByRoleName(roleEnum.name()).switchIfEmpty(saveRole(role));
        });
    }


    @Override
    public Flux<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Mono<Role> saveRole(Role role) {

        return roleRepository.save(role).doOnSubscribe(subscription -> log.info("Role persistence subscribed"));
    }

    @Override
    public Mono<Role> findByRoleId(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Mono<Role> findByRoleName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}

