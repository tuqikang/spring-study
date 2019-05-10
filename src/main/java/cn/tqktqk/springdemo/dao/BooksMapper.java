package cn.tqktqk.springdemo.dao;


import cn.tqktqk.springdemo.model.entity.BooksEntity;import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 图书表 Mapper 接口
 * </p>
 *
 * @author tuqikang
 * @since 2019-05-09
 */
public interface BooksMapper {

    List<BooksEntity> selectList(@Param("findFlag")String findFlag);

    BooksEntity selectById(@Param("id")Integer id);

    int updateRestCount(@Param("id")Integer id);
}
