package com.illtamer.sillage.rear.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 角色类型枚举
 * */
@Getter
@AllArgsConstructor
public enum RoleType {

    ADMIN("ROLE_ADMIN", -1),

    VISITOR("ROLE_VISITOR", 0);

    /**
     * 用户角色
     * */
    private final String role;

    /**
     * 账号类型
     * */
    private final int type;

    public static RoleType getRoleType(int type) {
        switch (type) {
            case -1: return ADMIN;
            case 0: return VISITOR;
            default: throw new IllegalArgumentException("Unknown type: " + type);
        }
    }

}
