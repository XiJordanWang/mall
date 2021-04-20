package com.mall.product.controller;

import com.mall.common.bean.ResponseResult;
import com.mall.product.service.PmsCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/product/category")
public class PmsCategoryController {

    @Resource
    private PmsCategoryService pmsCategoryService;

    /**
     * 获取首页列表树
     *
     * @return 列表树
     */
    @GetMapping("/treeList")
    public ResponseResult<?> treeList(){
        return ResponseResult
                .builder()
                .code(200)
                .msg("success")
                .data(pmsCategoryService.treeList())
                .build();
    }
}
