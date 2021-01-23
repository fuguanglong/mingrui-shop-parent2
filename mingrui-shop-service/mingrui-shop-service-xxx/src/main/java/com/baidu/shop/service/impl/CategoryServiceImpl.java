package com.baidu.shop.service.impl;

import com.baidu.shop.ObjectUtil.ObjectUtil;
import com.baidu.shop.base.BaseApiServer;
import com.baidu.shop.base.Result;
import com.baidu.shop.entity.CategoryEntity;
import com.baidu.shop.mapper.CategoryMapper;
import com.baidu.shop.server.CategoryService;
import com.baidu.shop.status.HTTPStatus;
import com.google.gson.JsonObject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TestSpringBootApplication
 * @Description: TODO
 * @Author fuguanglong
 * @Date 2020/12/28
 * @Version V1.0
 **/
@RestController
public class CategoryServiceImpl extends BaseApiServer implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Result<List<CategoryEntity>> getCategoryByPid(Integer pid) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setParentId(pid);
        List<CategoryEntity> select = categoryMapper.select(categoryEntity);

        return this.setResultSuccess(select);
    }

    @Transactional
    @Override
    public Result<JsonObject> delCategory(Integer id) {
        if(ObjectUtil.isNull(id) || id <= 0) return this.setResultError("id不合法");
        CategoryEntity categoryEntity = categoryMapper.selectByPrimaryKey(id);
        if(ObjectUtil.isNull(categoryEntity)) return this.setResultError("数据不存在");
        if(categoryEntity.getParentId() == 1) return this.setResultError("当前节点为父节点");
        //查询当亲节点的父节点下是否还有其他子节点
        Example example = new Example(CategoryEntity.class);
        example.createCriteria().andEqualTo("parentId",categoryEntity.getParentId());
        //将查询到的所有子节点返回到一个集合
        List<CategoryEntity> categoryEntities = categoryMapper.selectByExample(example);
        //如果小于等于1当前父节点下就没有子节点,将当前父节点改为子节点
        if(categoryEntities.size() <= 1){
            CategoryEntity categoryEntity1 = new CategoryEntity();
            categoryEntity1.setIsParent(0);
            categoryEntity1.setId(categoryEntity.getParentId());

            categoryMapper.updateByPrimaryKeySelective(categoryEntity1);
        }
        categoryMapper.deleteByPrimaryKey(id);
        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JsonObject> addCategory(CategoryEntity categoryEntity) {
        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(categoryEntity.getParentId());
        categoryEntity1.setIsParent(1);
        categoryMapper.updateByPrimaryKeySelective(categoryEntity1);

        categoryMapper.insertSelective(categoryEntity);
        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JsonObject> editCategory(CategoryEntity categoryEntity) {
        categoryMapper.updateByPrimaryKeySelective(categoryEntity);
        return this.setResultSuccess();
    }

    @Override
    public Result<List<CategoryEntity>> getByBrand(Integer brandId) {
        List<CategoryEntity> categoryByBrandId = categoryMapper.getCategoryByBrandId(brandId);
        return this.setResultSuccess(categoryByBrandId);
    }


}
