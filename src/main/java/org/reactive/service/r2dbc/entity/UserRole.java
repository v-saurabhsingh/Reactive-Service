package org.reactive.service.r2dbc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@Table("user_role")
public class UserRole {

    @Id
    private Long id;
    private Long userId;
    private Long roleId;
}
