package com.illtamer.sillage.rear.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 角色类型枚举
 * */
@Getter
@AllArgsConstructor
public enum RoleType {

    /**
     * 管理员
     * */
    ADMIN("ROLE_ADMIN", -1),

    /**
     * 游客
     * */
    VISITOR("ROLE_VISITOR", 0),

    /**
     * 用户
     * */
    USER("ROLE_USER", 1);

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
