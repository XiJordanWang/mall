package com.mall.product.controller;

import com.mall.common.bean.ResponseResult;
import com.mall.product.dto.CategoryAddDto;
import com.mall.product.service.PmsCategoryService;
import org.springframework.web.bind.annotation.*;

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
    public ResponseResult<?> treeList() {
        return ResponseResult
                .builder()
                .code(200)
                .msg("success")
                .data(pmsCategoryService.treeList())
                .build();
    }

    /**
     * 查询是否有子节点
     *
     * @param id
     * @return
     */
    @GetMapping("/canDel/{id}")
    public ResponseResult<?> canDel(@PathVariable("id") String id) {
        return ResponseResult
                .builder()
                .code(200)
                .msg("success")
                .data(pmsCategoryService.canDel(id))
                .build();
    }

    /**
     * 添加
     *
     * @param dto
     * @return
     */
    @PostMapping("/add")
    public ResponseResult<?> add(@RequestBody CategoryAddDto dto) {
        pmsCategoryService.add(dto);
        return ResponseResult
                .builder()
                .code(200)
                .msg("success")
                .build();
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    public ResponseResult<?> del(@PathVariable("id") String id) {
        pmsCategoryService.del(id);
        return ResponseResult
                .builder()
                .code(200)
                .msg("success")
                .build();
    }
}
