package cn.tqktqk.springdemo.model.result;

import lombok.Data;

/**
 * ___________ ________     ____  __.
 * \__    ___/ \_____  \   |    |/ _|
 * |    |     /  / \  \  |      <
 * |    |    /   \_/.  \ |    |  \
 * |____|    \_____\ \_/ |____|__ \
 *
 * @Author: tuqikang
 * @Date: 2019-05-09 19:42
 */
@Data
public class SimpleCountResult {

    //状态1.未归还 2.已按时归还3.未按时归还
    private Integer status;

    //总数
    private Integer amount;
}
