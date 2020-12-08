package com.rapid.generator.task;

import com.rapid.generator.entity.ColumnInfo;
import freemarker.template.TemplateException;
import com.rapid.generator.task.base.*;
import com.rapid.generator.utils.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author GreedyStar
 * Date   2018/4/20
 */
public class DTOTask extends AbstractTask {

    /**
     * 1.单表生成  2.多表时生成子表实体
     */
    public DTOTask(String className, List<ColumnInfo> infos) {
        this(className, null, null, infos);
    }

    /**
     * 一对多关系生成主表实体
     */
    public DTOTask(String className, String parentClassName, String foreignKey, List<ColumnInfo> tableInfos) {
        this(className, parentClassName, foreignKey, null, tableInfos);
    }

    /**
     * 多对多关系生成主表实体
     */
    public DTOTask(String className, String parentClassName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfos) {
        super(className, parentClassName, foreignKey, parentForeignKey, tableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException {
        // 生成DTO填充数据
        Map<String, String> dtoData = new HashMap<>();
        dtoData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        dtoData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        dtoData.put("Author", ConfigUtil.getConfiguration().getAuthor());
        dtoData.put("Date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        dtoData.put("ClassName", className);
        dtoData.put("Remarks", tableInfos.get(0).getTableRemarks());
        if (!StringUtil.isBlank(parentForeignKey)) { // 多对多：主表实体
            dtoData.put("Properties", GeneratorUtil.generateEntityProperties(parentClassName, tableInfos));
            dtoData.put("Methods", GeneratorUtil.generateEntityMethods(parentClassName, tableInfos));
        } else if (!StringUtil.isBlank(foreignKey)) { // 多对一：主表实体
            dtoData.put("Properties", GeneratorUtil.generateEntityProperties(parentClassName, tableInfos, foreignKey));
            dtoData.put("Methods", GeneratorUtil.generateEntityMethods(parentClassName, tableInfos, foreignKey));
        } else { // 单表关系
            dtoData.put("Properties", GeneratorUtil.generateEntityProperties(tableInfos));
            dtoData.put("Methods", GeneratorUtil.generateEntityMethods(tableInfos));
        }
        String filePath = FileUtil.getSourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPackageName()) + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getDTO());
        String fileName = className + "DTO.java";
        // 生成DTO对象文件
        FileUtil.generateToJava(FreemarketConfigUtil.TYPE_DTO, dtoData, filePath, fileName);
    }
}
