package cn.tqktqk.springdemo.gui.admin;

import cn.tqktqk.springdemo.dao.DefinitionMapper;
import cn.tqktqk.springdemo.exceptions.ServerException;
import cn.tqktqk.springdemo.model.entity.DefinitionEntity;
import cn.tqktqk.springdemo.model.enums.RoleEnum;
import cn.tqktqk.springdemo.utils.ValidRegex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

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
 * @date ：Created in 2019-06-14 16:05
 * @description：规章定义端口
 * @modified By：
 * @version:
 */
@Component
public class DefinitionPort extends JFrame {

    @Autowired
    private DefinitionMapper definitionMapper;

    JRadioButton studentRadio = new JRadioButton("学生");
    JRadioButton teacherRadio = new JRadioButton("老师");

    private JButton ensure = new JButton("确定");
    private JButton cancel = new JButton("取消");

    private JLabel label1 = new JLabel("数量上限");
    private JTextField text1 = new JTextField(10);

    private JLabel label2 = new JLabel("天数上限");
    private JTextField text2 = new JTextField(10);

    private JLabel label3 = new JLabel("超时罚款(元/天)");
    private JTextField text3 = new JTextField(10);

    private JPanel jPanel = new JPanel();

    private ButtonGroup group;

    public void init() {
        setBounds(780, 350, 400, 400);
        setLayout(new GridLayout(1, 1));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        remove(jPanel);
        autoPack();
        add(jPanel);

        ensure.addActionListener(p -> {
            DefinitionEntity entity = getByForm();
            entity.setUpdate(LocalDate.now());
            if (definitionMapper.selectByRole(entity.getRoleId()) == null) {
                definitionMapper.insertByRole(entity);
            } else {
                definitionMapper.updateByRole(entity);
            }
            JOptionPane.showMessageDialog(null, "设置成功", "成功", JOptionPane.PLAIN_MESSAGE);
            flush();
            setVisible(false);
        });
        cancel.addActionListener(p -> {
            flush();
            setVisible(false);
        });

        pack();
        setVisible(true);


    }

    private void autoPack() {
        jPanel = new JPanel(new GridLayout(5, 2));
        jPanel.add(label1);
        jPanel.add(text1);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel.add(label2);
        jPanel.add(text2);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel.add(label3);
        jPanel.add(text3);
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel.add(studentRadio);
        jPanel.add(teacherRadio);
        group = new ButtonGroup();
        group.add(studentRadio);
        group.add(teacherRadio);
        jPanel.add(ensure);
        jPanel.add(cancel);
    }

    private DefinitionEntity getByForm() {
        String s1 = text1.getText() == null ? "" : text1.getText();
        if (!s1.matches(ValidRegex.REGEX_POSITIVE)) {
            JOptionPane.showMessageDialog(null, "书数目上限必须是正数", "错误", JOptionPane.ERROR_MESSAGE);
            throw new ServerException("书数目上限必须是正数");
        }
        int upper = Integer.valueOf(s1);

        String s2 = text2.getText() == null ? "" : text2.getText();
        if (!s2.matches(ValidRegex.REGEX_POSITIVE)) {
            JOptionPane.showMessageDialog(null, "天数上限必须是正数", "错误", JOptionPane.ERROR_MESSAGE);
            throw new ServerException("天数上限必须是正数");
        }
        int time = Integer.valueOf(s2);

        String s3 = text3.getText() == null ? "" : text3.getText();
        if (!s3.matches(ValidRegex.REGEX_POSITIVE)) {
            JOptionPane.showMessageDialog(null, "罚金必须是正数", "错误", JOptionPane.ERROR_MESSAGE);
            throw new ServerException("罚金必须是正数");
        }
        double forfeit = Double.valueOf(s3);

        DefinitionEntity entity = new DefinitionEntity();
        entity.setForfeit(forfeit);
        entity.setTimeLimit(time);
        entity.setUpperLimit(upper);

        int role;
        if (teacherRadio.isSelected()) {
            role = RoleEnum.getCodeByMsg("老师");
        } else {
            role = RoleEnum.getCodeByMsg("学生");
        }
        entity.setRoleId(role);
        return entity;
    }

    public void flush(){
        text1.setText("");
        text2.setText("");
        text3.setText("");
    }

}
