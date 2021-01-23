package com.baidu.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.ObjectUtil.ObjectUtil;
import com.baidu.shop.base.BaseApiServer;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SpecGroupDTO;
import com.baidu.shop.dto.SpecParamDTO;
import com.baidu.shop.entity.SpecGroupEntity;
import com.baidu.shop.entity.SpecParamEntity;
import com.baidu.shop.mapper.SpecGroupMapper;
import com.baidu.shop.mapper.SpecParamMapper;
import com.baidu.shop.server.SpecificationService;
import com.baidu.shop.utils.BaiduBeanUtil;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TestSpringBootApplication
 * @Description: TODO
 * @Author fuguanglong
 * @Date 2021/1/18
 * @Version V1.0
 **/
@RestController
public class SpecGroupServiceImpl extends BaseApiServer implements SpecificationService {

    @Resource
    private SpecGroupMapper specGroupMapper;

    @Resource
    private SpecParamMapper specParamMapper;

    @Override
    public Result<List<SpecGroupEntity>> listSpecGroup(SpecGroupDTO specificationDTO) {
        Example example = new Example(SpecGroupEntity.class);
        example.createCriteria().andEqualTo("cid",
                BaiduBeanUtil.copyProperties(specificationDTO,SpecGroupEntity.class).getCid());
        List<SpecGroupEntity> specificationEntities = specGroupMapper.selectByExample(example);
        return this.setResultSuccess(specificationEntities);
    }

    @Override
    public Result<JSONObject> saveSpecGroup(SpecGroupDTO specificationDTO) {
        specGroupMapper.insertSelective(BaiduBeanUtil.copyProperties(specificationDTO,SpecGroupEntity.class));
        return this.setResultSuccess();
    }

    @Override
    public Result<JSONObject> editSpecGroup(SpecGroupDTO specificationDTO) {
        specGroupMapper.updateByPrimaryKeySelective(BaiduBeanUtil.copyProperties(specificationDTO,SpecGroupEntity.class));
        return this.setResultSuccess();

    }

    @Override
    public Result<JSONObject> delSpecGroup(Integer id) {
//        Example example = new Example(SpecParamEntity.class);
//        example.createCriteria().andEqualTo("groupId",id);
//        List<SpecParamEntity> specParamEntities = specParamMapper.selectByExample(example);
//        if(specParamEntities.size() != 0) return this.setResultError("不能删除");

        specGroupMapper.deleteByPrimaryKey(id);
        return this.setResultSuccess();
    }

    @Override
    public Result<List<SpecParamEntity>> getSpecParam(SpecParamDTO specParamDTO) {
        SpecParamEntity specParamEntity = BaiduBeanUtil.copyProperties(specParamDTO, SpecParamEntity.class);
        Example example = new Example(SpecParamEntity.class);
        Example.Criteria criteria = example.createCriteria();

        if(ObjectUtil.isNotNull(specParamEntity.getGroupId()))
            criteria.andEqualTo("groupId",specParamEntity.getGroupId());
        if(ObjectUtil.isNotNull(specParamEntity.getCid()))
            criteria.andEqualTo("cid",specParamEntity.getCid());
        List<SpecParamEntity> specParamEntities = specParamMapper.selectByExample(example);
        return this.setResultSuccess(specParamEntities);
    }


}
