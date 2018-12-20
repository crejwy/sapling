package com.brave.authority.service.serviceImpl;

import com.brave.authority.service.BaseService;
import com.core.base.BaseEntity;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public  class AbstractBaseService<T extends BaseEntity, ID extends Serializable> implements BaseService<T, ID> {
    AbstractBaseService(){}
    public AbstractBaseService(JpaRepository respository){
        this.respository=respository;
    };
    protected JpaRepository respository;


    @Override
    public T findById(ID id) {
        Optional<T> opt= respository.findById(id);
        if(!opt.isPresent()){
            return  null;
        }else{
            return opt.get();
        }
    }

    @Override
    public List<T> queryList() {
        return respository.findAll();
    }

    @Override
    public void delete(ID id) {
        respository.deleteById(id);
    }

    @Override
    public T saveOrUpdate(T dto) {
        respository.save(dto);
        return dto;
    }
}
