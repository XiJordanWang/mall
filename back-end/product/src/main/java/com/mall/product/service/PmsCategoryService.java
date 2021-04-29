package com.mall.product.service;

import com.mall.product.bean.PmsCategory;
import com.mall.product.dto.CategoryAddDto;

import java.util.List;

public interface PmsCategoryService {

    List<PmsCategory> treeList();

    Boolean canDel(String id);

    void add(CategoryAddDto dto);

    void update(PmsCategory category);

    void del(String id);
}
