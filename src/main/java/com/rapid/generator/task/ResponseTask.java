package com.rapid.generator.task;

import com.rapid.generator.entity.ColumnInfo;
import com.rapid.generator.task.base.AbstractTask;
import com.rapid.generator.utils.*;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname ResponseTask
 * @Description 返回实体
 * @Date 2020/12/5 17:13
 * @Created by dongzhiwei
 */
public class ResponseTask extends AbstractTask {


    /**
     * 1.单表生成  2.多表时生成子表实体
     */
    public ResponseTask(String className, List<ColumnInfo> infos) {
        this(className, null, null, infos);
    }

    /**
     * 一对多关系生成主表实体
     */
    public ResponseTask(String className, String parentClassName, String foreignKey, List<ColumnInfo> tableInfos) {
        this(className, parentClassName, foreignKey, null, tableInfos);
    }

    /**
     * 多对多关系生成主表实体
     */
    public ResponseTask(String className, String parentClassName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfos) {
        super(className, parentClassName, foreignKey, parentForeignKey, tableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成Entity填充数据
        Map<String, String> entityData = new HashMap<>();
        entityData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        entityData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getResponse());
        entityData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        entityData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        entityData.put("ClassName", className);
        entityData.put("Remarks", tableInfos.get(0).getTableRemarks());
        if (!StringUtil.isBlank(parentForeignKey)) { // 多对多：主表实体
            entityData.put("Properties", GeneratorUtil.generateEntityProperties(parentClassName, tableInfos));
            entityData.put("Methods", GeneratorUtil.generateEntityMethods(parentClassName, tableInfos));
        } else if (!StringUtil.isBlank(foreignKey)) { // 多对一：主表实体
            entityData.put("Properties", GeneratorUtil.generateEntityProperties(parentClassName, tableInfos, foreignKey));
            entityData.put("Methods", GeneratorUtil.generateEntityMethods(parentClassName, tableInfos, foreignKey));
        } else { // 单表关系
            entityData.put("Properties", GeneratorUtil.generateEntityProperties(tableInfos));
            entityData.put("Methods", GeneratorUtil.generateEntityMethods(tableInfos));
        }
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getResponse());
        String fileName = className + "Response.java";
        // 生成Request文件
        FileUtil.generateToJava(FreemarketConfigUtil.TYPE_RESPONSE, entityData, filePath, fileName);
    }

}
