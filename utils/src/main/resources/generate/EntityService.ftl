package ${topPackage}.service;

import com.core.base.AbstractService;
import ${topPackage}.entity.${EntityName};
import ${topPackage}.mapper.${EntityName}Mapper;
import ${topPackage}.service.serviceImpl.${ClassName}ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ${ClassName}Service extends AbstractService<${EntityName},${PKType}> implements ${ClassName}ServiceImpl {

	@Autowired
	private ${EntityName}Mapper ${className}Mapper;

    @Autowired
    public void setBaseMapper(){
        super.setBaseMapper(${className}Mapper);
    }

}
