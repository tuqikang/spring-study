package cn.tqktqk.springdemo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ___________ ________     ____  __.
 * \__    ___/ \_____  \   |    |/ _|
 * |    |     /  / \  \  |      <
 * |    |    /   \_/.  \ |    |  \
 * |____|    \_____\ \_/ |____|__ \
 *
 * @Author: tuqikang
 * @Date: 2019-05-09 16:11
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum RoleEnum {
    学生(1, "学生"),
    老师(2, "老师"),
    管理员(3, "admin");

    private Integer roleId;

    private String roleName;

    public static Integer getCodeByMsg(String roleName) {
        RoleEnum[] values = RoleEnum.values();
        for (RoleEnum value : values) {
            if (value.roleName.equals(roleName)) {
                return value.roleId;
            }
        }
        return null;
    }
}
