package com.mall.product.service.impl;

import com.mall.product.bean.PmsCategory;
import com.mall.product.dao.PmsCategoryDao;
import com.mall.product.service.PmsCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PmsCategoryServiceImpl implements PmsCategoryService {

    @Resource
    private PmsCategoryDao pmsCategoryDao;

    @Override
    public List<PmsCategory> treeList() {
        return this.getTreeList(pmsCategoryDao.selectList(null));
    }

    private List<PmsCategory> getTreeList(List<PmsCategory> categoryList){
        return categoryList
                .stream()
                .filter(category -> category.getParentCid() == 0)
                .map(category ->{
                    this.getChildList(category,categoryList);
                    return category;
                })
                .collect(Collectors.toList());
    }

    private List<PmsCategory> getChildList(PmsCategory root,List<PmsCategory> categoryList){
        return categoryList
                .stream()
                .filter(category -> category.getParentCid().equals(root.getCatId()))
                .map(category->{
                    root.setChild(this.getChildList(category, categoryList));
                    return category;
                })
                .collect(Collectors.toList());
    }
}
