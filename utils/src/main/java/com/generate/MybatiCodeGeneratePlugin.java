package com.generate;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MybatiCodeGeneratePlugin {
    public void generator() throws Exception{

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //指定 逆向工程配置文件
        File configFile = new File("utils/src/main/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
//        解决mapper下的xml文件追加生成造成报错的问题
//        1.在生成之前先清除mapper下的xml文件
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration= config.getContext("daoContext").getSqlMapGeneratorConfiguration();
        String dirPath = sqlMapGeneratorConfiguration.getTargetProject() + "/"
                + sqlMapGeneratorConfiguration.getTargetPackage()
                .replaceAll("\\.", "/");
        File dir = new File(dirPath);
        File files[]=  dir.listFiles();
        for(File file :files){
            file.delete();
        }


        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);

    }

}
