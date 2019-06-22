package cn.tqktqk.springdemo.gui;

import cn.tqktqk.springdemo.dao.UsersMapper;
import cn.tqktqk.springdemo.exceptions.ServerException;
import cn.tqktqk.springdemo.model.result.UserLoginResult;
import cn.tqktqk.springdemo.utils.MyMD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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
 * @date ：Created in 2019-06-22 17:09
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class UpdatePasswordPort extends JFrame {

    @Autowired
    private UsersMapper usersMapper;

    private static final String OLD_INFO_USERPSD = "请输入原密码";

    private static final String NEW_INFO_USERPSD = "请输入新密码";

    private static final char defaultChar = '*';

    private JButton entry = new JButton("确定");

    private JButton cancel = new JButton("取消");

    private JLabel username = new JLabel("用户名");
    private JTextField ut = new JTextField(20);

    private JLabel oldPassword = new JLabel("旧密码");
    private JPasswordField ot = new JPasswordField(20);

    private JLabel newPassword = new JLabel("新密码");
    private JPasswordField nt = new JPasswordField(20);

    private static int state = 0;

    public void init() {
        if (state == 1) {
            setVisible(true);
            return;
        }
        state = 1;
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(735, 350, 430, 170);
        setLayout(new GridLayout(4, 1));
        JPanel j1 = new JPanel(new BorderLayout());
        j1.add(username, BorderLayout.WEST);
        j1.add(ut, BorderLayout.CENTER);

        JPanel j2 = new JPanel(new BorderLayout());
        j2.add(oldPassword, BorderLayout.WEST);
        j2.add(ot, BorderLayout.CENTER);

        JPanel j3 = new JPanel(new BorderLayout());
        j3.add(newPassword, BorderLayout.WEST);
        j3.add(nt, BorderLayout.CENTER);

        JPanel j4 = new JPanel(new GridLayout(1, 2));
        j4.add(entry);
        j4.add(cancel);

        add(j1);
        add(j2);
        add(j3);
        add(j4);
        addAllListener();
        pack();
        setVisible(true);
    }

    private void addAllListener() {
        ot.addFocusListener(new OldPassListener());
        nt.addFocusListener(new NewPassListener());
        cancel.addActionListener(p -> {
            flush();
            setVisible(false);
        });
        entry.addActionListener(p -> {
            UserLoginResult result = usersMapper.login(ut.getText());
            if (result == null) {
                JOptionPane.showMessageDialog(null, "用户名不存在", "错误", JOptionPane.ERROR_MESSAGE);
                throw new ServerException("用户不存在");
            }
            if (MyMD5Util.checkpassword(ot.getText(), result.getPassword())) {
                String str = nt.getText();
                if (str == null || str.equals("")) {
                    JOptionPane.showMessageDialog(null, "新密码不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                    throw new ServerException("新密码不能为空");
                }
                String newPwd = MyMD5Util.encoderByMd5(str);
                int success = usersMapper.updatePassword(ut.getText(), newPwd);
                if (success != 1) {
                    JOptionPane.showMessageDialog(null, "系统错误", "错误", JOptionPane.ERROR_MESSAGE);
                    throw new ServerException("系统错误");
                }
                JOptionPane.showMessageDialog(null, "修改成功", "成功", JOptionPane.PLAIN_MESSAGE);
                flush();
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "密码不对", "错误", JOptionPane.ERROR_MESSAGE);
                throw new ServerException("密码不对");
            }
        });
    }

    public void flush() {
        ut.setText("");
        ot.setText("");
        nt.setText("");
    }

    private class OldPassListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent focusEvent) {
            String pswd = String.valueOf(ot.getPassword());
            System.out.println(pswd);
            if (pswd.equals(OLD_INFO_USERPSD)) {
                ot.setText("");
                ot.setEchoChar(defaultChar);
                ot.setForeground(Color.PINK);
            }
        }

        @Override
        public void focusLost(FocusEvent focusEvent) {
            String pswd = new String(ot.getPassword()).trim();
            // 如果没有输入密码. 就用明文 提示用户输入密码
            if (pswd.equals("")) {
                ot.setEchoChar('\0');
                ot.setText(OLD_INFO_USERPSD);
                ot.setForeground(Color.LIGHT_GRAY);
            }
        }
    }

    private class NewPassListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent focusEvent) {
            String pswd = String.valueOf(nt.getPassword());
            System.out.println(pswd);
            if (pswd.equals(NEW_INFO_USERPSD)) {
                nt.setText("");
                nt.setEchoChar(defaultChar);
                nt.setForeground(Color.PINK);
            }
        }

        @Override
        public void focusLost(FocusEvent focusEvent) {
            String pswd = new String(nt.getPassword()).trim();
            // 如果没有输入密码. 就用明文 提示用户输入密码
            if (pswd.equals("")) {
                nt.setEchoChar('\0');
                nt.setText(NEW_INFO_USERPSD);
                nt.setForeground(Color.LIGHT_GRAY);
            }
        }
    }
}
