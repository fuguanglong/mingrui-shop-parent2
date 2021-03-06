package com.baidu.shop.dto;

import com.baidu.shop.base.baseDTO;
import com.baidu.shop.validate.group.MingruiOperation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName TestSpringBootApplication
 * @Description: TODO
 * @Author fuguanglong
 * @Date 2021/1/20
 * @Version V1.0
 **/
@Data
@ApiModel(value = "规格组DTO")
public class SpecParamDTO extends baseDTO {
    @ApiModelProperty(value = "规格组id",example = "1")
    @NotNull(message = "id不能为空",groups = {MingruiOperation.update.class})
    private Integer id;

    @ApiModelProperty(value = "分类id",example = "1")
    private Integer cid;

    @ApiModelProperty(value = "规格组id",example = "1")
    private Integer groupId;

    @ApiModelProperty(value = "规格参数名称")
    private String name;

    @ApiModelProperty(value = "是否是数字类型参数",example = "0")
    @NotNull(message = "是否是数字类型参数不能为空",groups = {MingruiOperation.Add.class,MingruiOperation.update.class})
    private Boolean numeric;

    @ApiModelProperty(value = "数字类型参数的单位，非数字类型可以为空")
    private String unit;

    @ApiModelProperty(value = "是否是sku通用属性",example = "0")
    @NotNull(message = "是否是sku通用属性不能为空",groups = {MingruiOperation.Add.class,MingruiOperation.update.class})
    private Boolean generic;

    @ApiModelProperty(value = "是否用于搜索过滤",example = "0")
    @NotNull(message = "是否用于搜索过滤不能为空",groups = {MingruiOperation.Add.class,MingruiOperation.update.class})
    private Boolean searching;

    @ApiModelProperty(value = "数值类型参数")
    private String segments;
}
