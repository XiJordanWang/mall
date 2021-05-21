package com.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall.product.bean.PmsCategory;
import com.mall.product.dao.PmsCategoryDao;
import com.mall.product.dto.CategoryAddDto;
import com.mall.product.service.PmsCategoryService;
import org.springframework.beans.BeanUtils;
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

    @Override
    public Boolean canDel(String id) {
        QueryWrapper<PmsCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_cid", id);
        List<PmsCategory> categoryList = pmsCategoryDao.selectList(wrapper);
        return categoryList == null || categoryList.size() == 0;
    }

    @Override
    public void add(CategoryAddDto dto) {
        PmsCategory category = new PmsCategory();
        BeanUtils.copyProperties(dto, category);
        pmsCategoryDao.insert(category);
    }

    @Override
    public void update(PmsCategory category) {
        pmsCategoryDao.updateById(category);
    }

    @Override
    public void del(String id) {
        pmsCategoryDao.deleteById(id);
    }

    private List<PmsCategory> getTreeList(List<PmsCategory> categoryList) {
        return categoryList
                .stream()
                .filter(category -> category.getParentId() == 0)
                .map(category -> {
                    category.setChild(this.getChildList(category, categoryList));
                    return category;
                })
                .collect(Collectors.toList());
    }

    private List<PmsCategory> getChildList(PmsCategory root, List<PmsCategory> categoryList) {
        return categoryList
                .stream()
                .filter(category -> category.getParentId().equals(root.getId()))
                .map(category -> {
                    category.setChild(this.getChildList(category, categoryList));
                    return category;
                })
                .collect(Collectors.toList());
    }
}
