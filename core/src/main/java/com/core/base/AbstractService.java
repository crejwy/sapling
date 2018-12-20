package com.core.base;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public abstract  class AbstractService<T extends BaseEntity, ID extends Serializable> implements BaseService<T, ID> {
    private BaseMapper<T> baseMapper;
    public void setBaseMapper(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }
    @Override
    public int deleteByPrimaryKey(ID id) {
        return baseMapper.deleteByPrimaryKey(id);
    }
    @Override
    public int insertSelective(T pojo) {
        return baseMapper.insertSelective(pojo);
    }
    @Override
    public T selectByPrimaryKey(ID id) {
        return baseMapper.selectByPrimaryKey(id);
    }
    @Override
    public int updateByPrimaryKeySelective(T pojo) {
        pojo.setModifydt(new Date());
        return baseMapper.updateByPrimaryKeySelective(pojo);
    }
    @Override
    public int updateByPrimaryKey(T pojo) {
        pojo.setModifydt(new Date());
        return baseMapper.updateByPrimaryKey(pojo);
    }
    @Override
    public int insert(T pojo) {
        pojo.setCreatedt(new Date());
        return baseMapper.insert(pojo);
    }
    @Override
    public List<T> listAll(){
        return baseMapper.selectAll();
    }
    @Override
    public List<T> listBySelective(T pojo){
        return  baseMapper.select(pojo);
    }

}