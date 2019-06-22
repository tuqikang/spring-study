package cn.tqktqk.springdemo.gui.admin;

import cn.tqktqk.springdemo.model.result.UserLoginResult;
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
 * @date ：Created in 2019-06-12 15:03
 * @description：管理员菜单
 * @modified By：
 * @version:
 */
@Component
public class AdminMenuPort extends JFrame {

    @Autowired
    private InsertUserPort insertUserPort;

    @Autowired
    private DefinitionPort definitionPort;

    @Autowired
    private DefinitionInfoPort definitionInfoPort;

    @Autowired
    private BookManagePort bookManagePort;

    public void design(UserLoginResult loginResul) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(735, 350, 330, 330);
        setLayout(new BorderLayout());

        JPanel topJpanel = new JPanel(new GridLayout(1, 2));
        JLabel nicknameJLabel = new JLabel("您好:" + loginResul.getNickname());
        nicknameJLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        topJpanel.add(new Label());
        topJpanel.add(nicknameJLabel);
        add(topJpanel, BorderLayout.NORTH);

        JPanel centerJpanel = new JPanel(new BorderLayout());
        JPanel centerOfCenter = new JPanel(new GridLayout(4, 1));
        centerJpanel.add(centerOfCenter, BorderLayout.CENTER);
        JButton inserteUserButton = new JButton("添加用户");
        JButton definitionButton = new JButton("规章管理");
        JButton definitionInfoButton = new JButton("规章查看");
        JButton bookManageButton = new JButton("图书管理");
        add(centerJpanel, BorderLayout.CENTER);
        centerOfCenter.add(inserteUserButton);
        centerOfCenter.add(definitionInfoButton);
        centerOfCenter.add(definitionButton);
        centerOfCenter.add(bookManageButton);

        inserteUserButton.addActionListener(p -> insertUserPort.init());

        definitionButton.addActionListener(p -> definitionPort.init());

        definitionInfoButton.addActionListener(p -> definitionInfoPort.init());

        bookManageButton.addActionListener(p -> bookManagePort.init(1));
        setVisible(true);
    }
}
