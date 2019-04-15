package cn.tqktqk.springdemo.service;

import cn.tqktqk.springdemo.dao.UsersMapper;
import cn.tqktqk.springdemo.model.entity.Users;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ___________ ________     ____  __.
 * \__    ___/ \_____  \   |    |/ _|
 * |    |     /  / \  \  |      <
 * |    |    /   \_/.  \ |    |  \
 * |____|    \_____\ \_/ |____|__ \
 *
 * @Author: tuqikang
 * @Date: 2019-04-15 15:02
 */
@Service("userService")
public class UserService {
    @Autowired
    private UsersMapper usersMapper;

    public Users findUserByUserName(String id){
        return usersMapper.infoById(id);
    }

    public List<Users> getAll(){
        return usersMapper.getAllUser();
    }
}
