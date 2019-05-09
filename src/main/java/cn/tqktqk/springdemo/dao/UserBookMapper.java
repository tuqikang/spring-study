package cn.tqktqk.springdemo.dao;

import cn.tqktqk.springdemo.model.result.BookSimpleResult;import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户图书关系表 Mapper 接口
 * </p>
 *
 * @author tuqikang
 * @since 2019-05-09
 */
public interface UserBookMapper{

    List<BookSimpleResult> bookSimpleInfoByUserAndStatus(@Param("userId")Integer userId, @Param("status")Integer status);
}
