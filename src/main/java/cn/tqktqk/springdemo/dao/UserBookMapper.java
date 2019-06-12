package cn.tqktqk.springdemo.dao;

import cn.tqktqk.springdemo.model.result.BookSimpleResult;
import cn.tqktqk.springdemo.model.result.SimpleCountResult;
import cn.tqktqk.springdemo.model.result.UserBookInfoResult;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
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

    List<SimpleCountResult> countByStatus(@Param("userId")Integer userId);

    int nowCount(@Param("userId")Integer userId);

    int insertLendInfo(@Param("userId")Integer userId, @Param("bookId")Integer bookId, @Param("today")LocalDate today, @Param("expire")LocalDate expire);


    List<UserBookInfoResult> bookInfoByUserAndStatus(@Param("userId")Integer userId, @Param("status")Integer status);

    int updateStatusById(@Param("id")Integer id, @Param("statusId")Integer statusId);
}
