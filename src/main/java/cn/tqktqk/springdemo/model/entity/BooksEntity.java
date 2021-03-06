package cn.tqktqk.springdemo.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 图书表
 * </p>
 *
 * @author tuqikang
 * @since 2019-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BooksEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String description;

    private Integer amount;

    private Integer rest;

    private Double price;

    private LocalDate createTime;

    //逻辑删除0未删除 1删除
    private Integer deleted;

    @Override
    public String toString() {
        return "书名:" + name + ",简介:" + description + ", 余量:" + rest + ", 总量:" + amount;
    }
}
