package ${topPackage}.controller;

import ${topPackage}.entity.${EntityName};
import ${topPackage}.service.${ClassName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/${MappingName}")
public class ${ClassName}Controller {

	@Autowired
	private ${ClassName}Service ${className}Service;

	@RequestMapping("listAll")
    public List<${EntityName}>  getAll${MappingName}(){
        return ${className}Service.listAll();
    }

	@RequestMapping("listSelective")
    public List<${EntityName}>  getAll${MappingName}(${EntityName} obj){
        return ${className}Service.listBySelective(obj);
    }

	@RequestMapping("get")
    public ${EntityName} get${MappingName}ByPK(${PKType} ${PKName}){
        return ${className}Service.selectByPrimaryKey(${PKName});
    }

	@RequestMapping("save")
    public ${EntityName} save${MappingName}(${EntityName} obj){
		${className}Service.insert(obj);
        return  obj;
    }
	@RequestMapping("delete")
    public int delete${MappingName}(${PKType} ${PKName}){
        return  ${className}Service.deleteByPrimaryKey(${PKName});
    }
	@RequestMapping("updateUser")
    public  ${EntityName} update${MappingName}(${EntityName} obj){
		${className}Service.updateByPrimaryKey(obj);
        return  obj;
    }
}
