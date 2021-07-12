package com.jfl.service.r2dbc.repo;

import com.jfl.service.r2dbc.entity.UserRole;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends R2dbcRepository<UserRole,Long> {
}
