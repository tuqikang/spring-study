package cn.tqktqk.springdemo.service;

import cn.tqktqk.springdemo.dao.UserBookMapper;
import cn.tqktqk.springdemo.model.result.BookSimpleResult;
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
 * @Date: 2019-05-09 16:27
 */
@Service
public class UserBookService {

    @Autowired
    private UserBookMapper userBookMapper;


    public List<BookSimpleResult> unReturned(Integer userId,Integer status){
        return userBookMapper.bookSimpleInfoByUserAndStatus(userId,status);
    }
}
