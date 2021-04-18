package com.mall.common.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseResult<T> {

    private Integer code;

    private String msg;

    private T data;
}
