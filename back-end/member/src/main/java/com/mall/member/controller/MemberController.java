package com.mall.member.controller;

import com.mall.common.bean.ResponseResult;
import com.mall.member.feign.CouponFeign;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private CouponFeign couponFeign;

    @RequestMapping("/get")
    public ResponseResult<?> get() {
        return couponFeign.get();
    }
}
