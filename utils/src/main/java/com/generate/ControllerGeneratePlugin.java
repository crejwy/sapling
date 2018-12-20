package com.generate;


import freemarker.core.ParseException;
import freemarker.template.*;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ControllerGeneratePlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }
    private String topPackage ;
    private String targetProject ;


    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        String javaMapperType = introspectedTable.getMyBatis3JavaMapperType();

        String javaClassName = javaMapperType.substring(javaMapperType.lastIndexOf('.') + 1, javaMapperType.length()).replace("Mapper", "");
        String javaEntityName=javaClassName;
        String javaRequestMapping=javaClassName;

        String pktype="";
        String pkname="";
        List<IntrospectedColumn> columns= introspectedTable.getPrimaryKeyColumns();
        if(columns.size()>0){
            pktype=columns.get(0).getFullyQualifiedJavaType().toString();
            pkname=columns.get(0).getJavaProperty();
        }
        if(javaClassName.startsWith("Dim")){
            javaRequestMapping=javaClassName.substring(3);
            javaClassName=javaRequestMapping.concat("Config");
        }else if(javaClassName.startsWith("Fact")){
            javaClassName=javaRequestMapping=javaClassName.substring(4);
        }

        Map<String, String> root = new HashMap<String, String>();
        root.put("topPackage", topPackage);
        root.put("ClassName", javaClassName);
        root.put("EntityName", javaEntityName);
        root.put("PKType", pktype);
        root.put("PKName", pkname);
        root.put("MappingName",  new StringBuilder().append(Character.toLowerCase(javaRequestMapping.charAt(0)))
                .append(javaRequestMapping.substring(1)).toString());
        root.put("className", new StringBuilder().append(Character.toLowerCase(javaClassName.charAt(0)))
                .append(javaClassName.substring(1)).toString());

        genController(targetProject, topPackage, javaClassName, root);
        return null;
    }


    @SuppressWarnings("deprecation")
    private void genController(String targetProject, String topPackage, String javaClassName,
                               Map<String, String> root) {
        String dirPath = targetProject + "/" + topPackage.replaceAll("\\.", "/") + "/controller/";
        String filePath = dirPath + javaClassName
                + "Controller.java";
        File dir = new File(dirPath);
        File file = new File(filePath);
        if (file.exists()) {
            System.err.println(file + " already exists, it was skipped.");
            return;
        } else {
            try {
                dir.mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(this.getClass(), "/generate");
        cfg.setObjectWrapper(new DefaultObjectWrapper());


        try {
            Template temp = cfg.getTemplate("EntityController.ftl");
            Writer out = new OutputStreamWriter(new FileOutputStream(file));
            temp.process(root, out);
            out.flush();
        } catch (TemplateNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedTemplateNameException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }


    }

    public void setProperties(Properties properties) {
        super.setProperties(properties);
        topPackage = this.properties.getProperty("topPackage");
        targetProject = this.properties.getProperty("targetProject");

    }

}