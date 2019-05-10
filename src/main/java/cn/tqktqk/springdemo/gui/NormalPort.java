package cn.tqktqk.springdemo.gui;

import cn.tqktqk.springdemo.dao.DefinitionMapper;
import cn.tqktqk.springdemo.dao.UsersMapper;
import cn.tqktqk.springdemo.model.entity.DefinitionEntity;
import cn.tqktqk.springdemo.model.result.UserLoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * ___________ ________     ____  __.
 * \__    ___/ \_____  \   |    |/ _|
 * |    |     /  / \  \  |      <
 * |    |    /   \_/.  \ |    |  \
 * |____|    \_____\ \_/ |____|__ \
 *
 * @Author: tuqikang
 * @Date: 2019-05-09 18:44
 */
@Component
public class NormalPort extends JFrame implements UserPortFactory {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private DefinitionMapper definitionMapper;

    @Autowired
    private LendPort lendPort;

    @Override
    public void design(UserLoginResult loginResul) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(735,350,330,330);
        setLayout(new BorderLayout());

        JPanel topJpanel = new JPanel(new GridLayout(1,2));
        JLabel nicknameJLabel = new JLabel("您好:"+loginResul.getNickname());
        nicknameJLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        topJpanel.add(new Label());topJpanel.add(nicknameJLabel);
        add(topJpanel,BorderLayout.NORTH);

        JPanel centerJpanel =  new JPanel(new BorderLayout());
        JPanel centerOfCenter = new JPanel(new GridLayout(4,1));
        centerJpanel.add(centerOfCenter,BorderLayout.CENTER);
        JButton lendButton = new JButton("借书");
        JButton repayButton = new JButton("还书");
        JButton selfButton = new JButton("个人中心");
        JButton definitionButton = new JButton("规定须知");
        add(centerJpanel,BorderLayout.CENTER);
        centerOfCenter.add(lendButton);
        centerOfCenter.add(repayButton);
        centerOfCenter.add(selfButton);
        centerOfCenter.add(definitionButton);

        definitionButton.addActionListener(p->{
            DefinitionEntity definition = definitionMapper.selectByRole(loginResul.getRole());
            StringBuffer sb = new StringBuffer();
            LocalDate now = LocalDate.now();
            sb.append("您的借书上限为：").append(definition.getUpperLimit()).append("本\n").append("时间限制为：")
                    .append(definition.getTimeLimit()).append("天\n例如:在").append(now).append("借书\n需在")
                    .append(now.plusDays(definition.getTimeLimit())).append("前还书\n").append("超过还书时间，每天需罚：").append(definition.getForfeit()).append("元");
            JOptionPane.showMessageDialog(null,sb.toString(),"成功",JOptionPane.PLAIN_MESSAGE);
        });

        lendButton.addActionListener(p->lendPort.init(loginResul));

        setVisible(true);

    }
}
