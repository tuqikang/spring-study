package cn.tqktqk.springdemo.model.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author tuqikang
 * @since 2019-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String label;

    private String description;


}
