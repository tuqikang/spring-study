package test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@TableName("definition")
@ApiModel(value="DefinitionEntity对象", description="规则定义表")
public class DefinitionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色对象")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty(value = "借书上线")
    @TableField("upper_limit")
    private Integer upperLimit;

    @ApiModelProperty(value = "借书天限")
    @TableField("time_limit")
    private Integer timeLimit;

    @ApiModelProperty(value = "罚金")
    @TableField("forfeit")
    private Double forfeit;

    @ApiModelProperty(value = "修改时间")
    @TableField("update")
    private LocalDateTime update;


}
