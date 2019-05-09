package cn.tqktqk.springdemo.model.result;

import lombok.Data;

/**
 * ___________ ________     ____  __.
 * \__    ___/ \_____  \   |    |/ _|
 * |    |     /  / \  \  |      <
 * |    |    /   \_/.  \ |    |  \
 * |____|    \_____\ \_/ |____|__ \
 *
 * @Author: tuqikang
 * @Date: 2019-05-09 15:52
 */
@Data
public class UserLoginResult {

    private Integer userId;

    private String username;

    private String password;

    private Integer role;

}
