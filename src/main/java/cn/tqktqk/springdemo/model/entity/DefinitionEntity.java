package cn.tqktqk.springdemo.model.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 规则定义表
 * </p>
 *
 * @author tuqikang
 * @since 2019-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DefinitionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer roleId;

    private Integer upperLimit;

    private Integer timeLimit;

    private Double forfeit;

    private LocalDateTime update;


}
