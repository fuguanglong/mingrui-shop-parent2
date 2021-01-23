package com.baidu.shop.dto;

import com.baidu.shop.base.baseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName TestSpringBootApplication
 * @Description: TODO
 * @Author fuguanglong
 * @Date 2020/12/30
 * @Version V1.0
 **/
@Data
@ApiModel(value = "品牌DTO")
public class BrandDTO extends baseDTO {

    @ApiModelProperty(value = "品牌主键",example = "1")
    private Integer id;

    @ApiModelProperty(value = "品牌名称")
    private String name;

    @ApiModelProperty(value = "品牌图片")
    private String image;

    @ApiModelProperty(value = "品牌字母")
    private Character letter;

    @ApiModelProperty(value = "品牌分类")
    private String categories;
}
