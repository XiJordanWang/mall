package com.mall.product.dto;

import lombok.Data;

@Data
public class CategoryAddDto {
    /**
     * 分类名称
     */
    private String name;

    /**
     * 父分类id
     */
    private Long parentCid;

    /**
     * 层级
     */
    private Integer catLevel;
}
