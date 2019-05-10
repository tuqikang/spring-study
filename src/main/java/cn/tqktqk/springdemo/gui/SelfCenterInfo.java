package cn.tqktqk.springdemo.gui;

import cn.tqktqk.springdemo.dao.UserBookMapper;
import cn.tqktqk.springdemo.dao.UsersMapper;
import cn.tqktqk.springdemo.model.result.SimpleCountResult;
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

    public void init(Integer userId){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(735,350,430,170);

        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,0);
        map.put(2,0);
        map.put(3,0);
        List<SimpleCountResult> countResultList =  userBookMapper.countByStatus(userId);
        if (countResultList!=null && !countResultList.isEmpty()){
            countResultList.forEach(p->map.put(p.getStatus(),p.getAmount()));
        }
        setVisible(true);

        setLayout(new GridLayout(4,2));
        add(new JLabel("用户名:"+"21211121"));
        add(new JLabel("姓名:"+"涂齐康"));
        add(new JLabel("性别:"+"男"));
        add(new JLabel("年龄:"+29));
        add(new JLabel("总借阅数:"+"28"));

        JPanel j2 = new JPanel(new BorderLayout());
        j2.add(new Label("已归还数:"+map.get(2)),BorderLayout.CENTER);
        JButton jb2 = new JButton("查看具体");
        JPanel j22 = new JPanel(new BorderLayout());
        j22.add(jb2,BorderLayout.WEST);
        j2.add(j22,BorderLayout.EAST);
        add(j2);

        JPanel j1 = new JPanel(new BorderLayout());
        j1.add(new Label("未归还数:"+map.get(1)),BorderLayout.CENTER);
        JButton jb1 = new JButton("查看具体");
        j1.add(jb1,BorderLayout.EAST);
        add(j1);


        JPanel j3 = new JPanel(new BorderLayout());
        j3.add(new Label("未按时归还数:"+map.get(3)),BorderLayout.CENTER);
        JButton jb3 = new JButton("查看具体");
        j3.add(jb3,BorderLayout.EAST);
        add(j3);

        setVisible(true);


    }

}
