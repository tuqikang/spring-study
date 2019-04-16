package cn.tqktqk.springdemo;

import cn.tqktqk.springdemo.model.entity.Users;
import cn.tqktqk.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
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
@ContextConfiguration("classpath*:spring-mybatis.xml")
public class MainTest {

    @Autowired
    private UserService userService;

    public void test(){
        List<Users> list = userService.getAll();
        list.stream().forEach(p->{
            System.out.println(p);
        });

        System.out.println("-----");
        Users users = userService.findUserByUserName("1740611104");
        System.out.println(users);
    }

    public static void main(String[] args) throws IOException {
        new MainTest().test();
//        ApplicationContext ctx = null;
//        ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
//        UserService userService = (UserService) ctx.getBean("userService");

    }
}
