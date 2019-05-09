package cn.tqktqk.springdemo.gui;

import cn.tqktqk.springdemo.model.enums.RoleEnum;
import cn.tqktqk.springdemo.model.result.UserLoginResult;
import cn.tqktqk.springdemo.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.concurrent.TimeUnit;

/**
 * ___________ ________     ____  __.
 * \__    ___/ \_____  \   |    |/ _|
 * |    |     /  / \  \  |      <
 * |    |    /   \_/.  \ |    |  \
 * |____|    \_____\ \_/ |____|__ \
 *
 * @Author: tuqikang
 * @Date: 2019-04-20 18:40
 */
@Component("libraryAction")
public class LibraryAction extends JFrame {

    @Autowired
    private UserLoginService userLoginService;

    private JTextField username=new JTextField(20);
    private JPasswordField password=new JPasswordField(20);
    private static String STATUS="";
    private static final String INFO_USERNAME="请输入用户名";
    private static final String INFO_USERPSD="请输入密码";
    private static final char defaultChar='*';
    public static JComboBox status = new JComboBox();
    private JLabel jl = new JLabel("图书管理系统");
    private ImageIcon img = new ImageIcon("/Users/tuqikang/Desktop/study-try/spring-demo-action/src/main/java/cn/tqktqk/springdemo/timgx.jpeg");

    public void  init(){

        setBounds(735,350,260,330);
        setLayout(new BorderLayout());


        JPanel p1 = new JPanel();


        jl.setIcon(img);        // 为标签设置图片
        jl.setHorizontalAlignment(SwingConstants.CENTER);    // 定义图标的长和宽
        jl.setOpaque(true);


        status.addItem("请选择你的身份");
        status.addItem("学生");
        status.addItem("老师");
        status.addItem("admin");

        add(jl,BorderLayout.CENTER);
        add(p1,BorderLayout.SOUTH);
        add(new JLabel(),BorderLayout.EAST);
        add(new JLabel(),BorderLayout.WEST);

        p1.setLayout(new BorderLayout());
        JPanel p11 = new JPanel();
        p11.setLayout(new GridLayout(5,1));
        p1.add(p11,BorderLayout.CENTER);

        username.setForeground(Color.LIGHT_GRAY);// 设置密码框的文字颜色
        password.setForeground(Color.LIGHT_GRAY);// 设置密码框的文字颜色
        username.setText(INFO_USERNAME);
        password.setText(INFO_USERPSD);
        password.setEchoChar('\0');// \0 比较特殊, 密码框的文字会明文显示
        p11.add(username);
        p11.add(password);
        username.addFocusListener(new UserListener());
        password.addFocusListener(new PassListener());

        p11.add(status);


        JButton login = new JButton("login");
        p11.add(login);
        p11.add(new JLabel());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        status.addItemListener(p->{
            STATUS=(String)p.getItem();
        });

        login.addActionListener(p->{
            String userPassword=String.valueOf(password.getPassword());
            UserLoginResult userLoginResult = userLoginService.login(username.getText(),userPassword);
            Integer role = RoleEnum.getCodeByMsg(STATUS);
            if (!(Integer.compare(userLoginResult.getRole(),role)==0)){
                JOptionPane.showMessageDialog(null, "请选择你正确的身份", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }else {
                JOptionPane.showMessageDialog(null,"成功","success",JOptionPane.PLAIN_MESSAGE);
            }
        });
    }


    private class UserListener implements FocusListener{

        @Override
        public void focusGained(FocusEvent focusEvent) {
            if (username.getText().equals(INFO_USERNAME)) {// 如果是提示文字 ,就清空提示文字
                username.setText("");
                username.setForeground(Color.PINK);
            }
        }

        @Override
        public void focusLost(FocusEvent focusEvent) {
            if (username.getText().trim().equals("")){
                username.setText(INFO_USERNAME);
                username.setForeground(Color.LIGHT_GRAY);
            }
        }
    }

    private class PassListener implements FocusListener{

        @Override
        public void focusGained(FocusEvent focusEvent) {
            String pswd = String.valueOf(password.getPassword());
            System.out.println(pswd);
            if (pswd.equals(INFO_USERPSD)){
                password.setText("");
                password.setEchoChar(defaultChar);
                password.setForeground(Color.PINK);
            }
        }

        @Override
        public void focusLost(FocusEvent focusEvent) {
            String pswd = new String(password.getPassword()).trim();
            if (pswd.equals("")) {// 如果没有输入密码. 就用明文 提示用户输入密码
                password.setEchoChar('\0');
                password.setText(INFO_USERPSD);
                password.setForeground(Color.LIGHT_GRAY);
            }
        }
    }

}
