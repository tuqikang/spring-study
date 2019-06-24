package cn.tqktqk.springdemo.gui;

import cn.tqktqk.springdemo.dao.BooksMapper;
import cn.tqktqk.springdemo.dao.DefinitionMapper;
import cn.tqktqk.springdemo.dao.UserBookMapper;
import cn.tqktqk.springdemo.exceptions.ServerException;
import cn.tqktqk.springdemo.model.entity.BooksEntity;
import cn.tqktqk.springdemo.model.entity.DefinitionEntity;
import cn.tqktqk.springdemo.model.result.UserLoginResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
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
 * @date ：Created in 2019-06-22 20:03
 * @description：新借书接口
 * @modified By：
 * @version: 0.0.1
 */
@Component
public class LendBookPort extends JFrame{

    @Autowired
    private BooksMapper booksMapper;

    @Autowired
    private UserBookMapper userBookMapper;

    @Autowired
    private DefinitionMapper definitionMapper;

    private JCheckBox[] jCheckBox;

    private JButton search = new JButton("搜索");

    private JTextField index = new JTextField(10);

    private JButton lend = new JButton("借书");

    private JButton cancel = new JButton("取消");

    private JButton first = new JButton("首页");

    private JButton last = new JButton("尾页");

    private JButton next = new JButton("下页");

    private JButton prev = new JButton("上页");

    private int pages;

    private JLabel sum;

    private int pn = 1;

    private JPanel content = new JPanel();

    private JPanel top = new JPanel();

    private JPanel floot = new JPanel();

    private JPanel flootNorth = new JPanel();

    private JPanel flootSouth = new JPanel();

    private UserLoginResult userInfo;

    {
        addAllListener();
    }

    public void setUserInfo(UserLoginResult userInfo) {
        this.userInfo = userInfo;
    }

    public void init(int pn) {
        this.init(pn, index.getText());
    }

    public void init(int pn, String flag) {
        this.pn = pn;
        autoPack();
        PageHelper.startPage(pn, 10);
        List<BooksEntity> entityList = booksMapper.selectList(flag);
        PageInfo<BooksEntity> pageInfo = new PageInfo<>(entityList);
        pages = pageInfo.getPages();
        sum = new JLabel("总页码:" + pages);
        flootNorth.add(sum);
        jCheckBox = new JCheckBox[entityList.size()];
        for (int i = 0; i < entityList.size(); i++) {
            JPanel j1 = new JPanel(new BorderLayout());
            jCheckBox[i] = new JCheckBox("" + entityList.get(i).getId());
            j1.add(jCheckBox[i], BorderLayout.WEST);
            JLabel jl1 = new JLabel(entityList.get(i).toString());
            j1.add(jl1, BorderLayout.CENTER);
            content.add(j1);
        }
        setVisible(true);
    }

    public void addAllListener() {
        cancel.addActionListener(p->{
            setVisible(false);
        });
        first.addActionListener(p -> {
            init(1);
        });
        last.addActionListener(p -> {
            init(pages);
        });
        prev.addActionListener(p -> {
            int prev = pn - 1;
            if (prev < 1) {
                prev = 1;
            }
            init(prev);
        });
        next.addActionListener(p -> {
            int next = pn + 1;
            if (next > pages) {
                next = pages;
            }
            init(next);
        });
        search.addActionListener(p -> {
            init(1);
        });
        lend.addActionListener(p->{
            {
                List<Integer> bookIds = getSelectAll();
                if (bookIds.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请选择至少一本书", "错误", JOptionPane.ERROR_MESSAGE);
                    throw new ServerException("没有选择任何一本书");
                } else {
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
                    init(pn);
                }
            }
        });
    }


    private List<Integer> getSelectAll() {
        List<Integer> list = new ArrayList<>(10);
        for (JCheckBox b : jCheckBox) {
            if (b.isSelected()) {
                list.add(Integer.valueOf(b.getText()));
            }
        }
        return list;
    }

    private void autoPack() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(800, 350, 400, 500);
        remove(content);
        remove(top);
        remove(floot);
        content = new JPanel(new GridLayout(10, 1));
        top = new JPanel(new BorderLayout());
        top.add(index, BorderLayout.CENTER);
        top.add(search, BorderLayout.EAST);
        floot = new JPanel(new GridLayout(2, 1));
        flootSouth = new JPanel(new GridLayout(1, 2));
        flootNorth = new JPanel(new GridLayout(1, 6));
        flootNorth.add(first);
        flootNorth.add(prev);
        flootNorth.add(new JLabel("当前页:" + pn));
        flootNorth.add(next);
        flootNorth.add(last);
        flootSouth.add(lend);
        flootSouth.add(cancel);
        floot.add(flootNorth);
        floot.add(flootSouth);
        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(floot, BorderLayout.SOUTH);
        add(content, BorderLayout.CENTER);
    }
}
