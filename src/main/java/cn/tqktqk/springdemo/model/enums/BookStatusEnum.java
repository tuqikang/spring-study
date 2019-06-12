package cn.tqktqk.springdemo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ___________ ________     ____  __.
 * \__    ___/ \_____  \   |    |/ _|
 * |    |     /  / \  \  |      <
 * |    |    /   \_/.  \ |    |  \
 * |____|    \_____\ \_/ |____|__ \
 *
 * @Author: tuqikang
 * @Date: 2019-05-09 16:34
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum BookStatusEnum {
    未归还(1, "未归还"),
    已按时归还(2, "已按时归还"),
    未按时归还(3,"未按时归还");

    private Integer statusId;

    private String statusName;

    public static Integer getCodeByMsg(String statusName) {
        BookStatusEnum[] values = BookStatusEnum.values();
        for (BookStatusEnum value : values) {
            if (value.statusName.equals(statusName)) {
                return value.statusId;
            }
        }
        return null;
    }
}
