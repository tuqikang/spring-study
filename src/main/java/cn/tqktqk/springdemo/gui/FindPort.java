package cn.tqktqk.springdemo.gui;

import cn.tqktqk.springdemo.dao.BooksMapper;
import cn.tqktqk.springdemo.dao.DefinitionMapper;
import cn.tqktqk.springdemo.dao.UserBookMapper;
import cn.tqktqk.springdemo.exceptions.ServerException;
import cn.tqktqk.springdemo.model.entity.BooksEntity;
import cn.tqktqk.springdemo.model.entity.DefinitionEntity;
import cn.tqktqk.springdemo.model.result.UserLoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
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
 * @Date: 2019-05-10 09:25
 */
@Component
public class FindPort extends JFrame {

    @Autowired
    private UserBookMapper userBookMapper;

    @Autowired
    private BooksMapper booksMapper;

    @Autowired
    private DefinitionMapper definitionMapper;

    private JCheckBox[] jCheckBox;

    JPanel jpnel1= new JPanel();

    @Transactional(rollbackFor = Exception.class)
    public void init(UserLoginResult userInfo, List<BooksEntity> list) {
        remove(jpnel1);
        jpnel1 = new JPanel(new GridLayout(list.size() + 1, 1));
        int sizeHight = list.size() + 1;
        jpnel1.setBounds(735, 350, 330, sizeHight * 40);
        setBounds(735, 350, 330, sizeHight * 40);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(1,1));
        add(jpnel1);
//        jpnel1.setLayout(new GridLayout(list.size() + 1, 1));o
        setLayout(new GridLayout(list.size() + 1, 1));
        jCheckBox = new JCheckBox[list.size()];
        for (int i = 0; i < list.size(); i++) {
            JPanel j1 = new JPanel(new BorderLayout());
            jCheckBox[i] = new JCheckBox("" + list.get(i).getId());
            jCheckBox[i].setSize(30, 20);
            j1.add(jCheckBox[i], BorderLayout.WEST);
            JLabel jl1 = new JLabel(list.get(i).toString());
            jl1.setSize(300, 20);
            j1.add(jl1, BorderLayout.CENTER);
            jpnel1.add(j1);
        }
        JPanel jpForJb = new JPanel();
        jpForJb.setSize(330, 20);
        JButton jb1 = new JButton("确定");
        JButton jb2 = new JButton("取消");
        jpForJb.add(jb1);
        jpForJb.add(jb2);
        jpnel1.add(jpForJb);
        pack();
        jb1.addActionListener(p -> {
            List<Integer> bookIds = new ArrayList<>();
            for (JCheckBox jbx : jCheckBox) {
                if (jbx.isSelected()) {
                    bookIds.add(Integer.valueOf(jbx.getText()));
                }
            }
            if (bookIds.isEmpty()) {
                JOptionPane.showMessageDialog(null, "请选择至少一本书", "错误", JOptionPane.ERROR_MESSAGE);
                throw new ServerException("没有选择任何一本书");
            } else {
//                int upper = definitionMapper.upperLimit(userInfo.getRole());
                DefinitionEntity definition = definitionMapper.selectByRole(userInfo.getRole());
                int myNowCount = userBookMapper.nowCount(userInfo.getUserId());
                if (myNowCount + bookIds.size() > definition.getUpperLimit()) {
                    JOptionPane.showMessageDialog(null, "你借阅的书：" + bookIds.size() + "本加上已经借阅"+myNowCount+"本已经超过上限" + definition.getUpperLimit(), "错误", JOptionPane.ERROR_MESSAGE);
                    throw new ServerException("溢出");
                }
                List<BooksEntity> can = new ArrayList<>();
                List<BooksEntity> noCan = new ArrayList<>();
                bookIds.forEach(it -> {
                    BooksEntity entity = booksMapper.selectById(it);
                    if (entity.getRest() > 0) {
                        can.add(entity);
                    } else {
                        noCan.add(entity);
                    }
                });
                StringBuffer sb = new StringBuffer();
                if (!noCan.isEmpty()) {
                    for (BooksEntity be : noCan) {
                        sb.append("未成功借阅图书:" + be.getId() + "---" + be.getName() + "\n");
                    }
                }
                if (!can.isEmpty()) {
                    for (BooksEntity be : can) {
                        LocalDate today = LocalDate.now();
                        int userFlag = userBookMapper.insertLendInfo(userInfo.getUserId(), be.getId(), today, today.plusDays(definition.getTimeLimit()));
                        int bookFlag = booksMapper.updateRestCount(be.getId());
                        if (userFlag!=1 && bookFlag!=1){
                            JOptionPane.showMessageDialog(null, "系统错误" + definition.getUpperLimit(), "错误", JOptionPane.ERROR_MESSAGE);
                            throw new ServerException("系统内部错误");
                        }
                        sb.append("成功借阅图书:" + be.getId() + "---" + be.getName() + "\n");
                    }

                }
                JOptionPane.showMessageDialog(null, sb.toString(), "tips", JOptionPane.PLAIN_MESSAGE);
                setVisible(false);
            }
        });
        jb2.addActionListener(p -> setVisible(false));
        setVisible(true);

    }
}
