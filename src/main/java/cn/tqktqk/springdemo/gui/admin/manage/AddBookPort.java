package cn.tqktqk.springdemo.gui.admin.manage;

import cn.tqktqk.springdemo.dao.BooksMapper;
import cn.tqktqk.springdemo.exceptions.ServerException;
import cn.tqktqk.springdemo.gui.admin.BookManagePort;
import cn.tqktqk.springdemo.model.entity.BooksEntity;
import cn.tqktqk.springdemo.utils.ValidRegex;
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
 * @date ：Created in 2019-06-22 14:32
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class AddBookPort extends JFrame {

    @Autowired
    private BooksMapper booksMapper;
    @Autowired
    private BookManagePort bookManagePort;

    private JLabel name = new JLabel("书名");
    private JTextField nameText = new JTextField(10);

    private JLabel description = new JLabel("描述");
    private JTextField descriptionText = new JTextField(10);

    private JLabel amount = new JLabel("总量");
    private JTextField amountText = new JTextField(10);

    private JLabel price = new JLabel("价格");
    private JTextField priceText = new JTextField(10);

    private JButton entry = new JButton("确认");
    private JButton cancel = new JButton("取消");

    private JPanel jPanel = new JPanel();

    {
        addAllListener();
    }

    public void init() {
        setBounds(780, 350, 400, 400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        remove(jPanel);
        jPanel = new JPanel(new GridLayout(5, 2));
        jPanel.add(name);
        jPanel.add(nameText);
        jPanel.add(description);
        jPanel.add(descriptionText);
        jPanel.add(amount);
        jPanel.add(amountText);
        jPanel.add(price);
        jPanel.add(priceText);
        jPanel.add(entry);
        jPanel.add(cancel);
        add(jPanel);
        pack();
        setVisible(true);
    }

    public void addAllListener() {
        cancel.addActionListener(p -> setVisible(false));
        entry.addActionListener(p -> addBooks());
    }

    private void addBooks() {
        String name = nameText.getText();
        if (name == null || "".equals(name)) {
            JOptionPane.showMessageDialog(null, "书名不能为空", "错误", JOptionPane.ERROR_MESSAGE);
            throw new ServerException("书名不能为空");
        }
        String amount = amountText.getText();
        if (!amount.matches(ValidRegex.REGEX_POSITIVE)) {
            JOptionPane.showMessageDialog(null, "总数输入不正确", "错误", JOptionPane.ERROR_MESSAGE);
            throw new ServerException("总数输入不正确");
        }
        String price = priceText.getText();
        if (!price.matches(ValidRegex.REGEX_POSITIVE)) {
            JOptionPane.showMessageDialog(null, "金额输入不正确", "错误", JOptionPane.ERROR_MESSAGE);
            throw new ServerException("金额输入不正确");
        }
        int count = Integer.valueOf(amount);
        double money = Double.valueOf(price);
        BooksEntity entity = new BooksEntity();
        entity.setAmount(count);
        entity.setRest(count);
        entity.setName(name);
        entity.setPrice(money);
        entity.setDescription(descriptionText.getText());
        int success = booksMapper.insertBook(entity);
        if (success != 1) {
            JOptionPane.showMessageDialog(null, "添加失败,系统错误", "错误", JOptionPane.ERROR_MESSAGE);
            throw new ServerException("添加失败,系统错误");
        }
        JOptionPane.showMessageDialog(null, "添加图书成功", "成功", JOptionPane.PLAIN_MESSAGE);
        setVisible(false);
        bookManagePort.init(1);
        flush();
    }

    private void flush() {
        nameText.setText("");
        descriptionText.setText("");
        amountText.setText("");
        priceText.setText("");
    }
}
