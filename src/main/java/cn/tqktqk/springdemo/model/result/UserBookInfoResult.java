package cn.tqktqk.springdemo.model.result;

import cn.tqktqk.springdemo.model.enums.BookStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
 * @date ：Created in 2019-06-12 10:59
 * @description：
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBookInfoResult {

    private Integer id;

    private Integer userId;

    private Integer bookId;

    private Integer status;

    private String bookName;

    private LocalDate createTime;

    private LocalDate expireTime;

    public String getStatus() {
        return BookStatusEnum.getMsgBuCode(this.status);
    }

    @Override
    public String toString() {
        String info = "书名:" + bookName + ",开始时间" + createTime + ",到期时间" + expireTime;
        LocalDate now = LocalDate.now();
        if (now.isAfter(expireTime)) {
            info += ",已经超过租阅时间";
        }
        return info;
    }
}
