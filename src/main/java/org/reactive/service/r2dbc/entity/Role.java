package org.reactive.service.r2dbc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@Table("role")
public class Role implements Persistable<Long> {

    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private String description;

    @Transient
    private boolean isNew=false;

    @Override
    public boolean isNew() {
        return isNew;
    }
}
