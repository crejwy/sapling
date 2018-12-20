package com.brave.authority.service;

import java.util.List;

public interface BaseService<T,ID> {
    T findById(ID id);
    List<T> queryList();
    void delete(ID id);
    T saveOrUpdate(T dto);
}
