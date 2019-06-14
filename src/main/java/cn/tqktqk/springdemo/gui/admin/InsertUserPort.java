package cn.tqktqk.springdemo.gui.admin;

import cn.tqktqk.springdemo.dao.RoleMapper;
import cn.tqktqk.springdemo.dao.UserRoleMapper;
import cn.tqktqk.springdemo.dao.UsersMapper;
import cn.tqktqk.springdemo.exceptions.ServerException;
import cn.tqktqk.springdemo.model.entity.UsersEntity;
import cn.tqktqk.springdemo.model.enums.RoleEnum;
import cn.tqktqk.springdemo.model.result.UserLoginResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.awt.*;

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
 * @date ：Created in 2019-06-14 14:05
 * @description：添加用户接口
 * @modified By：
 * @version:
 */
@Component
public class InsertUserPort extends JFrame {

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    private JPanel jPanel1 = new JPanel();

    private JLabel label1 = new JLabel("用户名");
    private JTextField text1 = new JTextField(10);

    private JLabel label2 = new JLabel("姓名");
    private JTextField text2 = new JTextField(10);

    private JLabel label3 = new JLabel("性别");
    private JTextField text3 = new JTextField(10);

    private JLabel label4 = new JLabel("年龄");
    private JTextField text4 = new JTextField(10);

    private JLabel label5 = new JLabel("电话");
    private JTextField text5 = new JTextField(10);

    private JLabel label6 = new JLabel("邮箱");
    private JTextField text6 = new JTextField(10);

    private JButton ensure = new JButton("确定");
    private JButton cancel = new JButton("取消");

    JRadioButton studentRadio = new JRadioButton("学生");
    JRadioButton teacherRadio = new JRadioButton("老师");

    private ButtonGroup group;

    public void init() {
        setBounds(735, 350, 400, 400);
        setLayout(new GridLayout(1, 1));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        remove(jPanel1);
        autoPack();
        add(jPanel1);
        pack();

        cancel.addActionListener(p -> {
            flush();
            setVisible(false);
        });

        ensure.addActionListener(p -> {
            UsersEntity usersEntity = getByForm();
            valid(usersEntity);
            if (usersMapper.login(usersEntity.getUsername()) != null) {
                JOptionPane.showMessageDialog(null, "该用户已经存在", "错误", JOptionPane.ERROR_MESSAGE);
                throw new ServerException("该用户已经存在");
            }
            insert(usersEntity);
            int role;
            if (studentRadio.isSelected()) {
                role = RoleEnum.getCodeByMsg("学生");
            } else {
                role = RoleEnum.getCodeByMsg("老师");
            }
            UserLoginResult result = usersMapper.login(usersEntity.getUsername());
            userRoleMapper.insert(result.getUserId(), role);
            setVisible(false);
        });

        setVisible(true);

    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(UsersEntity usersEntity) {
        int count = usersMapper.insertUser(usersEntity);
        if (count != 1) {
            JOptionPane.showMessageDialog(null, "系统错误，请稍后重试", "错误", JOptionPane.ERROR_MESSAGE);
            throw new ServerException("系统错误");
        }
        JOptionPane.showMessageDialog(null, "插入成功", "成功", JOptionPane.PLAIN_MESSAGE);
    }

    public void autoPack() {
        jPanel1 = new JPanel(new GridLayout(4, 4));
        jPanel1.add(label1);
        jPanel1.add(text1);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel1.add(label2);
        jPanel1.add(text2);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel1.add(label3);
        jPanel1.add(text3);
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel1.add(label4);
        jPanel1.add(text4);
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel1.add(label5);
        jPanel1.add(text5);
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel1.add(label6);
        jPanel1.add(text6);
        label6.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel1.add(studentRadio);
        jPanel1.add(teacherRadio);
        group = new ButtonGroup();
        group.add(studentRadio);
        group.add(teacherRadio);
        jPanel1.add(ensure);
        jPanel1.add(cancel);
    }

    public void flush() {
        text1.setText("");
        text2.setText("");
        text3.setText("");
        text4.setText("");
        text5.setText("");
        text6.setText("");
    }

    public UsersEntity getByForm() {
        UsersEntity usersEntity = new UsersEntity();
        String username = text1.getText() == null ? "" : text1.getText();
        String nickname = text2.getText() == null ? "" : text2.getText();
        Integer gender = text3.getText() == null ? -1 : text3.getText().equals("男") ? 1 : 2;
        Integer age = text4.getText() == null ? -1 : Integer.valueOf(text4.getText());
        String phone = text5.getText() == null ? "" : text5.getText();
        String email = text6.getText() == null ? "" : text6.getText();

        usersEntity.setUsername(username);
        usersEntity.setNickname(nickname);
        usersEntity.setGender(gender);
        usersEntity.setAge(age);
        usersEntity.setEmail(email);
        usersEntity.setPhone(phone);

        return usersEntity;
    }

    public void valid(UsersEntity entity) {
        if (!studentRadio.isSelected() && !teacherRadio.isSelected()) {
            JOptionPane.showMessageDialog(null, "请选择添加用户的类型", "错误", JOptionPane.ERROR_MESSAGE);
            throw new ServerException("请选择添加用户的类型");
        }
        if (entity.getUsername().equals("")) {
            JOptionPane.showMessageDialog(null, "用户名不能为空", "错误", JOptionPane.ERROR_MESSAGE);
            throw new ServerException("用户名不能为空");
        }
        if (entity.getNickname().equals("")) {
            JOptionPane.showMessageDialog(null, "姓名不能为空", "错误", JOptionPane.ERROR_MESSAGE);
            throw new ServerException("姓名不能为空");
        }
        if (entity.getGender() == -1) {
            JOptionPane.showMessageDialog(null, "性别字段有问题,男or女", "错误", JOptionPane.ERROR_MESSAGE);
            throw new ServerException("性别字段有问题");
        }
        if (entity.getAge() == -1) {
            JOptionPane.showMessageDialog(null, "年龄不能为空", "错误", JOptionPane.ERROR_MESSAGE);
            throw new ServerException("年龄不能为空");
        }
        if (entity.getAge() < 0) {
            JOptionPane.showMessageDialog(null, "年龄不合法", "错误", JOptionPane.ERROR_MESSAGE);
            throw new ServerException("年龄不合法");
        }
        if (entity.getEmail().equals("")) {
            JOptionPane.showMessageDialog(null, "邮箱不能为空", "错误", JOptionPane.ERROR_MESSAGE);
            throw new ServerException("邮箱不能为空");
        }
        if (!entity.getEmail().matches(REGEX_EMAIL)) {
            JOptionPane.showMessageDialog(null, "邮箱不合法", "错误", JOptionPane.ERROR_MESSAGE);
            throw new ServerException("邮箱不合法");
        }
        if (entity.getPhone().equals("")) {
            JOptionPane.showMessageDialog(null, "手机号码不能为空", "错误", JOptionPane.ERROR_MESSAGE);
            throw new ServerException("手机号码不能为空");
        }
        if (!entity.getPhone().matches(REGEX_MOBILE)) {
            JOptionPane.showMessageDialog(null, "手机号码不合法", "错误", JOptionPane.ERROR_MESSAGE);
            throw new ServerException("手机号码不合法");
        }

    }
}
