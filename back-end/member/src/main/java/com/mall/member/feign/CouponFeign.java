package com.mall.member.feign;

import com.mall.common.bean.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("mall-coupon")
public interface CouponFeign {

    @GetMapping("/coupon/get")
    ResponseResult<?> get();
}
