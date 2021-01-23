package com.baidu.shop.base;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName TestSpringBootApplication
 * @Description: TODO
 * @Author fuguanglong
 * @Date 2020/12/28
 * @Version V1.0
 **/
@Data
@NoArgsConstructor
public class Result<T> {
    private Integer code;

    private String message;

    private T data;

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = (T)data;
    }
}
