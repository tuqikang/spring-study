package cn.tqktqk.springdemo;

import cn.tqktqk.springdemo.dao.UsersMapper;
import cn.tqktqk.springdemo.model.entity.Users;
import cn.tqktqk.springdemo.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * ___________ ________     ____  __.
 * \__    ___/ \_____  \   |    |/ _|
 * |    |     /  / \  \  |      <
 * |    |    /   \_/.  \ |    |  \
 * |____|    \_____\ \_/ |____|__ \
 *
 * @Author: tuqikang
 * @Date: 2019-04-15 15:00
 */
public class MainTest {
    public static void main(String[] args) throws IOException {
        ApplicationContext ctx = null;
        ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        UserService userService = (UserService) ctx.getBean("userService");
        List<Users> list = userService.getAll();
        list.stream().forEach(p->{
            System.out.println(p);
        });

        System.out.println("-----");
        Users users = userService.findUserByUserName("1740611104");
        System.out.println(users);
    }
}
