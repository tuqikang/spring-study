package cn.tqktqk.springdemo.gui.admin;

import cn.tqktqk.springdemo.dao.BooksMapper;
import cn.tqktqk.springdemo.dao.UserBookMapper;
import cn.tqktqk.springdemo.gui.admin.manage.AddBookPort;
import cn.tqktqk.springdemo.gui.admin.manage.DeleteBookPort;
import cn.tqktqk.springdemo.model.entity.BooksEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
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
 * @date ：Created in 2019-06-22 13:23
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class BookManagePort extends JFrame {

    @Autowired
    private BooksMapper booksMapper;

    @Autowired
    private UserBookMapper userBookMapper;

    @Autowired
    private AddBookPort addBookPort;

    @Autowired
    private DeleteBookPort deleteBookPort;

    private JCheckBox[] jCheckBox;

    private JButton search = new JButton("搜索");

    private JTextField index = new JTextField(10);

    private JButton insert = new JButton("新增");

    private JButton delete = new JButton("删除");

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

    private Map<Integer, BooksEntity> map;

    {
        addAllListener();
    }

    public void init(int pn) {
        map = null;
        this.pn = pn;
        autoPack();
        PageHelper.startPage(pn, 10);
        List<BooksEntity> entityList = booksMapper.selectList(index.getText());
        PageInfo<BooksEntity> pageInfo = new PageInfo<>(entityList);
        pages = pageInfo.getPages();
        sum = new JLabel("总页码:" + pages);
        flootNorth.add(sum);
        map = new HashMap<>(16);
        for (BooksEntity b : entityList) {
            map.put(b.getId(), b);
        }
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
        insert.addActionListener(p -> addBookPort.init());
        delete.addActionListener(p -> deleteBookPort.init(map, getSelectAll()));
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
        index.setText("");
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
        flootSouth.add(insert);
        flootSouth.add(delete);
        floot.add(flootNorth);
        floot.add(flootSouth);
        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(floot, BorderLayout.SOUTH);
        add(content, BorderLayout.CENTER);
    }
}
