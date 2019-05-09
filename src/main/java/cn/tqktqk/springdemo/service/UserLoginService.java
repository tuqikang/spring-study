package cn.tqktqk.springdemo.service;

import cn.tqktqk.springdemo.dao.UsersMapper;
import cn.tqktqk.springdemo.exceptions.ServerException;
import cn.tqktqk.springdemo.model.enums.RoleEnum;
import cn.tqktqk.springdemo.model.result.UserLoginResult;
import cn.tqktqk.springdemo.utils.MyMD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;

/**
 * ___________ ________     ____  __.
 * \__    ___/ \_____  \   |    |/ _|
 * |    |     /  / \  \  |      <
 * |    |    /   \_/.  \ |    |  \
 * |____|    \_____\ \_/ |____|__ \
 *
 * @Author: tuqikang
 * @Date: 2019-05-09 15:51
 */
@Service("userLoginService")
public class UserLoginService {

    @Autowired
    private UsersMapper usersMapper;

    public UserLoginResult login(String username,String password){
        UserLoginResult loginResult = usersMapper.login(username);
        if (loginResult==null){
            JOptionPane.showMessageDialog(null, "用户名不存在", "错误",JOptionPane.ERROR_MESSAGE);
            throw new ServerException("用户名不存在");
        }
        if (loginResult.getRole()==null){
            loginResult.setRole(RoleEnum.学生.getRoleId());
        }
        if (!MyMD5Util.checkpassword(password,loginResult.getPassword())){
            JOptionPane.showMessageDialog(null, "密码错误", "错误",JOptionPane.ERROR_MESSAGE);
            throw new ServerException("密码错误");
        }
        return loginResult;
    }
}
