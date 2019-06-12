package cn.tqktqk.springdemo.gui;

import cn.tqktqk.springdemo.dao.BooksMapper;
import cn.tqktqk.springdemo.dao.DefinitionMapper;
import cn.tqktqk.springdemo.dao.UserBookMapper;
import cn.tqktqk.springdemo.exceptions.ServerException;
import cn.tqktqk.springdemo.model.entity.DefinitionEntity;
import cn.tqktqk.springdemo.model.enums.BookStatusEnum;
import cn.tqktqk.springdemo.model.result.UserBookInfoResult;
import cn.tqktqk.springdemo.model.result.UserLoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
 * @date ：Created in 2019-06-12 10:54
 * @description：还书gui
 * @modified By：
 * @version:
 */
@Component
public class ReturnBookPort extends JFrame {

    @Autowired
    private UserBookMapper userBookMapper;

    @Autowired
    private BooksMapper booksMapper;

    @Autowired
    private DefinitionMapper definitionMapper;

    private JCheckBox[] jCheckBox;

    private JPanel jpnel1 = new JPanel();

    @Transactional(rollbackFor = Exception.class)
    public void init(UserLoginResult userLoginResult) {
        List<UserBookInfoResult> userBookInfoResults = userBookMapper.bookInfoByUserAndStatus(userLoginResult.getUserId(), BookStatusEnum.未归还.getStatusId());
        remove(jpnel1);
        jpnel1 = new JPanel(new GridLayout(userBookInfoResults.size() + 1, 1));
        int sizeHight = userBookInfoResults.size() + 1;
        jpnel1.setBounds(735, 350, 330, sizeHight * 40);
        setBounds(735, 350, 330, sizeHight * 40);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(1, 1));
        add(jpnel1);
        jCheckBox = new JCheckBox[userBookInfoResults.size()];
        for (int i = 0; i < userBookInfoResults.size(); i++) {
            JPanel j1 = new JPanel(new BorderLayout());
            jCheckBox[i] = new JCheckBox("" + userBookInfoResults.get(i).getId());
            jCheckBox[i].setSize(30, 20);
            j1.add(jCheckBox[i], BorderLayout.WEST);
            JLabel jl1 = new JLabel(userBookInfoResults.get(i).toString());
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
        setVisible(true);
        pack();
        jb2.addActionListener(p -> setVisible(false));
        jb1.addActionListener(p -> {
            List<Integer> ids = new ArrayList<>();
            for (JCheckBox jbx : jCheckBox) {
                if (jbx.isSelected()) {
                    ids.add(Integer.valueOf(jbx.getText()));
                }
            }
            if (ids.isEmpty()) {
                JOptionPane.showMessageDialog(null, "请选择至少一本书", "错误", JOptionPane.ERROR_MESSAGE);
                throw new ServerException("没有选择任何一本书");
            } else {
                List<UserBookInfoResult> results = new ArrayList<>(10);
                for (UserBookInfoResult var1 : userBookInfoResults) {
                    for (int var2 : ids) {
                        if (var1.getId() == var2) {
                            results.add(var1);
                            break;
                        }
                    }
                }
                boolean flag = false;
                LocalDate now = LocalDate.now();
                StringBuilder sb = new StringBuilder("还书成功\n");
                BigDecimal sum = new BigDecimal("0");
                DefinitionEntity definition = definitionMapper.selectByRole(userLoginResult.getRole());
                for (UserBookInfoResult var1 : results) {
                    long days = ChronoUnit.DAYS.between(var1.getExpireTime(), now);
                    if (days > 0) {
                        flag = true;
                        BigDecimal b1 = new BigDecimal(days);
                        BigDecimal b2 = new BigDecimal(definition.getForfeit());
                        sum = sum.add(b1.multiply(b2));
                        sb.append(var1.getId()).append(var1.getBookName()).append("该书超过过期时间").append("超过").
                                append(days).append("天\n").append("缴纳罚金:").append(b1.multiply(b2).doubleValue()).append("\n");
                        int count = userBookMapper.updateStatusById(var1.getId(), BookStatusEnum.未按时归还.getStatusId());
                    } else {
                        int count = userBookMapper.updateStatusById(var1.getId(), BookStatusEnum.已按时归还.getStatusId());
                    }
                    int count = booksMapper.incrRestCount(var1.getBookId());
                }
                if (flag) {
                    sb.append("总罚金:").append(sum.doubleValue()).append("元");
                }
                JOptionPane.showMessageDialog(null, sb.toString(), "还书成功", JOptionPane.PLAIN_MESSAGE);
                setVisible(false);
            }
        });
    }


}
