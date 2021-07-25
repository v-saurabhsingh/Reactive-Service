package org.reactive.service.r2dbc.repo;

import org.reactive.service.r2dbc.entity.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends R2dbcRepository<User,Long> {
}
