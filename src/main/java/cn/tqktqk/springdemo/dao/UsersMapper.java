package cn.tqktqk.springdemo.dao;

import cn.tqktqk.springdemo.model.entity.Users;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author tuqikang
 * @since 2019-04-15
 */
public interface UsersMapper{

    Users infoById(String id);

    List<Users> getAllUser();
}
