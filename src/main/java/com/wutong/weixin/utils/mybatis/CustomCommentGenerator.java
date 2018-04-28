package com.wutong.weixin.utils.mybatis;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

/**
 * @author ：wutong
 * @date：2018/4/24
 * 自定义注释生成
 */
public class CustomCommentGenerator implements CommentGenerator{

    private final static String AUTHOR = "wutong";
    private final static String EMAIL = "919964333@qq.com";


    /** The properties. */
    private Properties properties;

    /** The suppress date. */
    private boolean suppressDate;

    /** The suppress all comments. */
    private boolean suppressAllComments;

    /** The addition of table remark's comments.
     * If suppressAllComments is true, this option is ignored*/
    private boolean addRemarkComments;

    private SimpleDateFormat dateFormat;

    public CustomCommentGenerator() {
        super();
        properties = new Properties();
        suppressDate = false;
        suppressAllComments = false;
        addRemarkComments = false;
    }

    /**
     * 返回格式化后的时间字符串
     *
     * @return 时间字符串
     */
    private String getDateString() {
        if (suppressDate) {
            return null;
        } else if (dateFormat != null) {
            return dateFormat.format(new Date());
        } else {
            return new Date().toString();
        }
    }

    /**
     * 自定义注释
     *
     * @param javaElement the java element
     */
    private void addJavadocTag(JavaElement javaElement) {
        StringBuilder sb = new StringBuilder();

        sb.append(" * ");
        sb.append("@author：");
        sb.append(AUTHOR);
        javaElement.addJavaDocLine(sb.toString());


        String s = getDateString();
        if (s != null) {
            sb.setLength(0);
            sb.append(" * ");
            sb.append("@date：");
            sb.append(s);
            javaElement.addJavaDocLine(sb.toString());
        }

        sb.setLength(0);
        sb.append(" * ");
        sb.append("@email：");
        sb.append(EMAIL);
        javaElement.addJavaDocLine(sb.toString());
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        suppressDate = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));
        suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
        addRemarkComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));
        String dateFormatString = properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_DATE_FORMAT);
        if (StringUtility.stringHasValue(dateFormatString)) {
            dateFormat = new SimpleDateFormat(dateFormatString);
        }else{
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

        if (suppressAllComments) {
            return;
        }
        field.addJavaDocLine("/**");

        String remarks = introspectedColumn.getRemarks();
        if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                field.addJavaDocLine(" * " + remarkLine);
            }
        }
        field.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

//        if (suppressAllComments) {
//            return;
//        }
//        field.addJavaDocLine("/**");
//        field.addJavaDocLine(" */");
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        if (suppressAllComments  || !addRemarkComments) {
            return;
        }
        topLevelClass.addJavaDocLine("/**");
        addJavadocTag(topLevelClass);
        topLevelClass.addJavaDocLine(" * ");
        topLevelClass.addJavaDocLine(" */");
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        innerClass.addJavaDocLine("/**");
        addJavadocTag(innerClass);
        innerClass.addJavaDocLine(" */");
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        if (suppressAllComments) {
            return;
        }
        innerClass.addJavaDocLine("/**");
        addJavadocTag(innerClass);
        innerClass.addJavaDocLine(" */");
    }

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {

        if (suppressAllComments) {
            return;
        }
        innerEnum.addJavaDocLine("/**");
        innerEnum.addJavaDocLine(" */");
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

        if (suppressAllComments) {
            return;
        }
        method.addJavaDocLine("/**");

        String remarks = introspectedColumn.getRemarks();
        if (addRemarkComments && StringUtility.stringHasValue(remarks)) {

            StringBuilder sb = new StringBuilder();
            sb.append(" * 获取 ");
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            String remark = remarkLines[0];
            sb.append(remark);
            method.addJavaDocLine(sb.toString());

            method.addJavaDocLine(" *");

            String methodName = method.getName();
            sb.setLength(0);
            sb.append(" * @return ");
            //sb.append(introspectedColumn.getActualColumnName());
            //sb.append(" ");
            sb.append(remark);
            method.addJavaDocLine(sb.toString());
        }

        method.addJavaDocLine(" */");
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

        if (suppressAllComments) {
            return;
        }
        method.addJavaDocLine("/**");

        String remarks = introspectedColumn.getRemarks();

        if (addRemarkComments && StringUtility.stringHasValue(remarks)) {

            StringBuilder sb = new StringBuilder();
            sb.append(" * 设置 ");
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            String remark = remarkLines[0];
            sb.append(remark);
            method.addJavaDocLine(sb.toString());

            method.addJavaDocLine(" *");

            Parameter parameter = method.getParameters().get(0);
            sb.setLength(0);
            sb.append(" * @param ");
            sb.append(parameter.getName());
            sb.append(" ");
            sb.append(remark);
            method.addJavaDocLine(sb.toString());
        }

        method.addJavaDocLine(" */");
    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        // 默认不添加文件级别的注释
    }

    @Override
    public void addComment(XmlElement xmlElement) {
        // 默认不添加XML文档节点级别的注释
    }

    @Override
    public void addRootComment(XmlElement rootElement) {
        // 默认不添加XML文档级别的注释
    }
}
