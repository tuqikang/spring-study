package cn.tqktqk.springdemo.gui;

import cn.tqktqk.springdemo.dao.BooksMapper;
import cn.tqktqk.springdemo.dao.DefinitionMapper;
import cn.tqktqk.springdemo.dao.UserBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * █████▒█      ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 *
 * @author ：涂齐康
 * @date ：Created in 2019-06-12 10:54
 * @description：还书gui
 * @modified By：
 * @version:
 */
@Component
public class ReturnBookPort extends JFrame {

    @Autowired
    private UserBookMapper userBookMapper;

    @Autowired
    private BooksMapper booksMapper;

    @Autowired
    private DefinitionMapper definitionMapper;

    JPanel jpnel1= new JPanel();

    public void init(){

    }
}
