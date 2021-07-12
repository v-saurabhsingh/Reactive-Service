package com.jfl.service.constant;

import lombok.Getter;

@Getter
public enum RoleEnum {

    OWNER_ROLE(1L,"OR","Owner is root user"),
    ADMIN_ROLE(2L,"AR","This role can manage entire portal"),
    AREA_MANAGER_ROLE(3L,"ARM","This role defines a specific area admin"),
    STORE_MANAGER_ROLE(4L,"SRM","This role define a specific store admin"),
    STORE_STAFF_ROLE(5L,"SSR","This role define a specific store employee"),
    DELIVERY_BOY_ROLE(6L,"SMR","This role define a delivery boy"),
    REPORTS_VIEW_ROLE(7L,"RVR","This role a some reports view access");

    private Long id;
    private String roleAlias;
    private String description;

    RoleEnum(Long id,String roleAlias,String description) {
        this.id=id;
        this.roleAlias=roleAlias;
        this.description = description;
    }
}
