package org.reactive.service.r2dbc.service;

import org.reactive.service.constant.RoleEnum;
import org.reactive.service.r2dbc.entity.User;
import org.reactive.service.r2dbc.entity.UserRole;
import org.reactive.service.r2dbc.repo.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Data
@Slf4j
@Service
@DependsOn("roleServiceImpl")
public class UserServiceImpl extends AbstractService implements UserService {

    private final RoleService roleService;
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;


    @PostConstruct
    public void init() throws Exception {
        User user=new User();
        user.setActive(true);
        user.setUsername("v-saurabh.singh");
        user.setPassword("123456");
        user.setName("Saurabh Singh");
        user.setEmail("v-saurabh.singh@jublfood.com");
        user.setMobileNo("9696848127");
        user.setRoleAlias(RoleEnum.OWNER_ROLE.getRoleAlias());
        roleService.init()
                .then(findByUserId(1L))
                .switchIfEmpty(saveUserWithRole(user,RoleEnum.OWNER_ROLE))
                .subscribe();
    }

    @Override
    public Flux<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> findByUserId(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<User> saveUser(User user){
       return userRepository.save(user).doOnSubscribe(subscription -> log.info("User persistence subscribed"));
    }

    @Override
    @Transactional
    public Mono<User> saveUserWithRole(User user,RoleEnum roleEnum) {
         return saveUser(user).zipWith(roleService.findByRoleName(roleEnum.name()))
                .flatMap(t1 -> {
                    UserRole userRole = new UserRole();
                    userRole.setUserId(t1.getT1().getId());
                    userRole.setRoleId(t1.getT2().getId());
                    userRoleService.saveUserRoleMapping(userRole).subscribe(userRole1 -> log.info(userRole1.toString()));
                    return Mono.just(t1.getT1());
                });
    }
}
