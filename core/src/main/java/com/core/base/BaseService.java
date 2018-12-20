package com.core.base;


import java.io.Serializable;
import java.util.List;

public interface BaseService<T,ID extends Serializable> {
    void setBaseMapper();
    int deleteByPrimaryKey(ID id);
    int insert(T record);
    int insertSelective(T pojo);
    T selectByPrimaryKey(ID id);
    int updateByPrimaryKeySelective(T pojo);
    int updateByPrimaryKey(T pojo);
    List<T> listAll();
    List<T> listBySelective(T pojo);
}