package cn.tqktqk.springdemo.gui;

import cn.tqktqk.springdemo.dao.BooksMapper;
import cn.tqktqk.springdemo.model.entity.BooksEntity;
import cn.tqktqk.springdemo.model.result.UserLoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ___________ ________     ____  __.
 * \__    ___/ \_____  \   |    |/ _|
 * |    |     /  / \  \  |      <
 * |    |    /   \_/.  \ |    |  \
 * |____|    \_____\ \_/ |____|__ \
 *
 * @Author: tuqikang
 * @Date: 2019-05-09 20:16
 */
@Component
public class LendPort extends JFrame {

    @Autowired
    private BooksMapper booksMapper;

    @Autowired
    private FindPort findPort;

    private JLabel display  = new JLabel();

    public void init(UserLoginResult userInfo){
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());
        JPanel j1 = new JPanel(new BorderLayout());
        JTextField findText = new JTextField(20);
        j1.add(findText,BorderLayout.CENTER);
        JButton jb1 = new JButton("搜索");
        j1.add(jb1,BorderLayout.EAST);
        JPanel j2 = new JPanel();
        add(j1,BorderLayout.NORTH);
        add(display,BorderLayout.CENTER);


        jb1.addActionListener(p->{
            String findFlag  = findText.getText();
            List<BooksEntity> entitys =booksMapper.selectList(findFlag);
            findPort.init(userInfo,entitys);
        });



        setBounds(735,350,330,330);
        setVisible(true);
    }
}
