package cn.tqktqk.springdemo.dao;


import cn.tqktqk.springdemo.model.entity.DefinitionEntity;import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 规则定义表 Mapper 接口
 * </p>
 *
 * @author tuqikang
 * @since 2019-05-09
 */
public interface DefinitionMapper {

    DefinitionEntity selectByRole(@Param("role")Integer role);

    Integer upperLimit(Integer role);
}
