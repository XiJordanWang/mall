package com.mall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.product.bean.PmsCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PmsCategoryDao extends BaseMapper<PmsCategory> {
}
