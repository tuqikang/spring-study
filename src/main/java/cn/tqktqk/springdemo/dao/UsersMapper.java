package cn.tqktqk.springdemo.dao;


import cn.tqktqk.springdemo.model.result.UserLoginResult;import org.apache.ibatis.annotations.Param;

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
}
