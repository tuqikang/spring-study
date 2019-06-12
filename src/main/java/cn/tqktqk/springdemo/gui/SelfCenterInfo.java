package cn.tqktqk.springdemo.gui;

import cn.tqktqk.springdemo.dao.UserBookMapper;
import cn.tqktqk.springdemo.dao.UsersMapper;
import cn.tqktqk.springdemo.model.result.SimpleCountResult;
import cn.tqktqk.springdemo.model.result.UserInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ___________ ________     ____  __.
 * \__    ___/ \_____  \   |    |/ _|
 * |    |     /  / \  \  |      <
 * |    |    /   \_/.  \ |    |  \
 * |____|    \_____\ \_/ |____|__ \
 *
 * @Author: tuqikang
 * @Date: 2019-05-09 19:36
 */
@Component
public class SelfCenterInfo extends JFrame {
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UserBookMapper userBookMapper;

    JPanel jpnel1= new JPanel();

    public void init(Integer userId) {
        remove(jpnel1);
        jpnel1 = new JPanel(new GridLayout(5, 2));
        jpnel1.setBounds(735, 350, 430, 170);
        setLayout(new GridLayout(1,1));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(735, 350, 430, 170);

        Map<Integer, Integer> map = new HashMap<>(4);
        map.put(1, 0);
        map.put(2, 0);
        map.put(3, 0);
        List<SimpleCountResult> countResultList = userBookMapper.countByStatus(userId);
        if (countResultList != null && !countResultList.isEmpty()) {
            countResultList.forEach(p -> map.put(p.getStatus(), p.getAmount()));
        }
        UserInfos userInfos = usersMapper.selectUserInfo(userId);

//        jpnel1.setLayout(new GridLayout(5, 2));
        add(jpnel1);
        jpnel1.add(new JLabel("用户名:" + userInfos.getUsername()));
        jpnel1.add(new JLabel("姓名:" + userInfos.getNickname()));
        jpnel1.add(new JLabel("性别:" + userInfos.getGender()));
        jpnel1.add(new JLabel("年龄:" + userInfos.getAge()));
        jpnel1.add(new JLabel("电话:" + userInfos.getPhone()));
        jpnel1.add(new JLabel("邮箱:" + userInfos.getEmail()));
        jpnel1.add(new JLabel("总借阅数:" + countResultList.stream().mapToInt(p -> p.getAmount()).sum()));

        JPanel j2 = new JPanel(new BorderLayout());
        j2.add(new Label("已归还数:" + map.get(2)), BorderLayout.CENTER);
        JPanel j22 = new JPanel(new BorderLayout());
        j2.add(j22, BorderLayout.EAST);
        jpnel1.add(j2);

        JPanel j1 = new JPanel(new BorderLayout());
        j1.add(new Label("未归还数:" + map.get(1)), BorderLayout.CENTER);
        jpnel1.add(j1);


        JPanel j3 = new JPanel(new BorderLayout());
        j3.add(new Label("未按时归还数:" + map.get(3)), BorderLayout.CENTER);
        jpnel1.add(j3);

        setVisible(true);


    }

}
