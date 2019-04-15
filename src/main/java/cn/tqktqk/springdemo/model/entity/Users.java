package cn.tqktqk.springdemo.model.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author tuqikang
 * @since 2019-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 最后操作时间
     */
    private LocalDateTime lastVisit;

    /**
     * 最后登录ip
     */
    private String lastIp;

    @Override
    public String toString() {
        return "Users{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastVisit=" + lastVisit +
                ", lastIp='" + lastIp + '\'' +
                '}';
    }
}
