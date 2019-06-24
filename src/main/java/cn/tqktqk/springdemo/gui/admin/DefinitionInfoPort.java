package cn.tqktqk.springdemo.gui.admin;

import cn.tqktqk.springdemo.dao.DefinitionMapper;
import cn.tqktqk.springdemo.exceptions.ServerException;
import cn.tqktqk.springdemo.model.entity.DefinitionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
 * @date ：Created in 2019-06-22 12:32
 * @description：规章信息端口
 * @modified By：
 * @version: 0.0.1
 */
@Component
public class DefinitionInfoPort extends JFrame {

    @Autowired
    private DefinitionMapper definitionMapper;

    private Show show = new Show();

    private JButton student = new JButton("学生");

    private JButton teacher = new JButton("老师");

    {
        autoListener();
    }
    public void init() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(800, 400, 220, 220);
        setLayout(new GridLayout(2, 1));
        add(student);
        add(teacher);
        setVisible(true);
    }

    public void autoListener(){
        student.addActionListener(p -> {
            //数据库查找对应的规章信息
            DefinitionEntity entity = definitionMapper.selectByRole(1);
            if (entity == null) {
                JOptionPane.showMessageDialog(null, "无学生规章，请先去设置", "错误", JOptionPane.ERROR_MESSAGE);
                throw new ServerException("无学生规章");
            }
            show.display("学生", entity);
        });

        teacher.addActionListener(p -> {
            DefinitionEntity entity = definitionMapper.selectByRole(2);
            if (entity == null) {
                JOptionPane.showMessageDialog(null, "无老师规章，请先去设置", "错误", JOptionPane.ERROR_MESSAGE);
                throw new ServerException("无老师规章");
            }
            show.display("老师", entity);
        });
    }

    private static class Show extends JFrame {

        private JPanel jPanel = new JPanel();

        public Show() {

        }

        public void display(String role, DefinitionEntity definitionEntity) {
            remove(jPanel);
            jPanel = new JPanel();
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setBounds(825, 400, 160, 160);
            jPanel.setLayout(new GridLayout(5, 1));
            add(jPanel);
            JLabel name = new JLabel(role);
            JLabel label1 = new JLabel("数量上限:" + definitionEntity.getUpperLimit());
            JLabel label2 = new JLabel("天数上限:" + definitionEntity.getTimeLimit());
            JLabel label3 = new JLabel("超时罚款(元/天):" + definitionEntity.getForfeit());
            JLabel label4 = new JLabel("修改时间:" + definitionEntity.getUpdate());
            jPanel.add(name);
            jPanel.add(label1);
            jPanel.add(label2);
            jPanel.add(label3);
            jPanel.add(label4);
//            pack();
            setVisible(true);
        }
    }
}
