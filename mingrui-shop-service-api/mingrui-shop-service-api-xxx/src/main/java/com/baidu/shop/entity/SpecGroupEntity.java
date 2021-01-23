package com.baidu.shop.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName TestSpringBootApplication
 * @Description: TODO
 * @Author fuguanglong
 * @Date 2021/1/18
 * @Version V1.0
 **/
@Data
@Table(name = "tb_spec_group")
public class SpecGroupEntity {
    @Id
    private Integer id;

    private Integer cid;

    private String name;

}
