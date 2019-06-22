package cn.tqktqk.springdemo.dao;


import cn.tqktqk.springdemo.model.entity.UsersEntity;
import cn.tqktqk.springdemo.model.result.UserInfos;
import cn.tqktqk.springdemo.model.result.UserLoginResult;import org.apache.ibatis.annotations.Param;

import javax.swing.*;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author tuqikang
 * @since 2019-05-09
 */
public interface UsersMapper {

    UserLoginResult login(@Param("username")String username);

    UserInfos selectUserInfo(@Param("userId")Integer userId);

    int insertUser(@Param("user")UsersEntity user);

    int updatePassword(@Param("username")String username, @Param("newPwd")String newPwd);
}
