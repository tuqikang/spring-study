package cn.tqktqk.springdemo.gui.admin.manage;

import cn.tqktqk.springdemo.dao.BooksMapper;
import cn.tqktqk.springdemo.exceptions.ServerException;
import cn.tqktqk.springdemo.gui.admin.BookManagePort;
import cn.tqktqk.springdemo.model.entity.BooksEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

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
 * @date ：Created in 2019-06-22 16:11
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class DeleteBookPort extends JFrame {

    @Autowired
    private BooksMapper booksMapper;

    @Autowired
    private BookManagePort bookManagePort;

    private JButton entry = new JButton("确定");

    private JButton cancel = new JButton("取消");

    private JLabel context = new JLabel("确定要删除这些图书吗？");

    private JPanel jPanel = new JPanel();

    @Transactional(rollbackFor = Exception.class)
    public void init(Map<Integer, BooksEntity> map, List<Integer> selectAll) {
        setBounds(800, 400, 200, 200);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        remove(jPanel);
        jPanel = new JPanel(new BorderLayout());
        add(jPanel);
        jPanel.add(context, BorderLayout.CENTER);
        JPanel jp = new JPanel(new GridLayout(1, 2));
        jPanel.add(jp, BorderLayout.SOUTH);

        JButton jb1 = new JButton("确定");
        JButton jb2 = new JButton("取消");
        jp.add(jb1);
        jp.add(jb2);
        setVisible(true);
        jb2.addActionListener(it -> {
            setVisible(false);
        });
        jb1.addActionListener(it -> {
            if (selectAll.isEmpty()) {
                JOptionPane.showMessageDialog(null, "未选择图书", "错误", JOptionPane.ERROR_MESSAGE);
                throw new ServerException("未选择图书");
            }
            for (int i : selectAll) {
                int amount = map.get(i).getAmount();
                int count = booksMapper.deleteById(i, amount);
                if (count != 1) {
                    JOptionPane.showMessageDialog(null, "该书仍有被借阅，无法删除", "错误", JOptionPane.ERROR_MESSAGE);
                    throw new ServerException("该书仍有被借阅，无法删除");
                }
            }
            JOptionPane.showMessageDialog(null, "删除成功", "成功", JOptionPane.PLAIN_MESSAGE);
            setVisible(false);
            bookManagePort.init(1);
        });
    }
}
