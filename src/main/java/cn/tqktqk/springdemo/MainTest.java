package cn.tqktqk.springdemo;

import cn.tqktqk.springdemo.gui.LibraryAction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;

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
        LibraryAction libraryAction = (LibraryAction) ctx.getBean("libraryAction");
        libraryAction.init();
        libraryAction.setVisible(true);
        //聚焦
        LibraryAction.status.requestFocus();
    }
}
