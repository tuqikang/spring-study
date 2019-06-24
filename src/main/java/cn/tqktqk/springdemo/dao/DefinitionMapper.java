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

    //数据库查找role的规章
    DefinitionEntity selectByRole(@Param("role")Integer role);

    Integer upperLimit(Integer role);

    void insertByRole(@Param("entity")DefinitionEntity entity);

    void updateByRole(@Param("entity")DefinitionEntity entity);
}
