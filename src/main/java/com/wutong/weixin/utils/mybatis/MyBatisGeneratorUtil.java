package com.wutong.weixin.utils.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：wutong
 * @date：2018/4/24
 * MyBatis Generator
 */
public class MyBatisGeneratorUtil {

    public static void main(String[] args) {
        MyBatisGeneratorUtil.generator();
    }



    public static void generator(){

        List<String> warnings = new ArrayList<>();
        try {

            //导入配置表mybatis-generator.xml
            //C:\work\idea_work\web\web-commons\web-commons-generator\src\main\resources\mybatis-generator.xml
            File configFile = new File(MyBatisGeneratorUtil.class.getClassLoader().getResource("config/mybatis-generator.xml").toURI());
            //解析
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            //是否覆盖
            DefaultShellCallback callback = new DefaultShellCallback(true);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String warning : warnings){
            System.out.println(warning);
        }

    }
}
