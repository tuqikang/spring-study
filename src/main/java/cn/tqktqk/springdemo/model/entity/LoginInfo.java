package cn.tqktqk.springdemo.model.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author tuqikang
 * @since 2019-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userId;

    private String ip;

    private LocalDateTime loginDatetime;


}
