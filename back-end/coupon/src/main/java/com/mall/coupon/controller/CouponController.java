package com.mall.coupon.controller;

import com.mall.common.bean.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupon")
public class CouponController {


    @GetMapping("/get")
    public ResponseResult<?> get(){
        return ResponseResult.builder().code(200).msg("获取成功").build();
    }
}
