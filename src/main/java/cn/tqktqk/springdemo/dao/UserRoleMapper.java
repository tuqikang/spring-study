package cn.tqktqk.springdemo.dao;


import org.apache.ibatis.annotations.Param; /**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author tuqikang
 * @since 2019-05-09
 */
public interface UserRoleMapper{

    void insert(@Param("userId")Integer userId, @Param("role")int role);
}
