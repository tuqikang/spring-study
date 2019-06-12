package cn.tqktqk.springdemo.gui;

import cn.tqktqk.springdemo.gui.admin.AdminMenuPort;
import cn.tqktqk.springdemo.model.result.UserLoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ___________ ________     ____  __.
 * \__    ___/ \_____  \   |    |/ _|
 * |    |     /  / \  \  |      <
 * |    |    /   \_/.  \ |    |  \
 * |____|    \_____\ \_/ |____|__ \
 *
 * @Author: tuqikang
 * @Date: 2019-05-09 18:42
 */
@Component
public class UserPortFactory {

    @Autowired
    private NormalPort userPort;

    @Autowired
    private AdminMenuPort adminMenuPort;

    public NormalPort getUserPort(){
        return userPort;
    }

    public AdminMenuPort getAdminPort(){
        return adminMenuPort;
    }
}
