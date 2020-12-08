package com.rapid.generator.task;

import com.rapid.generator.entity.*;
import freemarker.template.TemplateException;
import com.rapid.generator.task.base.*;
import com.rapid.generator.utils.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author GreedyStar
 * Date   2018/4/20
 */
public class MapperXmlTask extends AbstractTask {

    /**
     * 单表Mapper
     */
    public MapperXmlTask(String className, String tableName, List<ColumnInfo> infos) {
        this(tableName, className, null, null, null, infos, null);
    }

    /**
     * 一对多Mapper
     */
    public MapperXmlTask(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        this(tableName, className, parentTableName, parentClassName, foreignKey, null, null, tableInfos, parentTableInfos);
    }

    /**
     * 多对多Mapper
     */
    public MapperXmlTask(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, String parentForeignKey, String relationalTableName, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        super(tableName, className, parentTableName, parentClassName, foreignKey, parentForeignKey, relationalTableName, tableInfos, parentTableInfos);
    }

    @Override
    public void run() throws IOException, TemplateException
    {
        // 生成Mapper填充数据
        Map<String, String> mapperData = new HashMap<>();
        mapperData.put("PackageName", ConfigUtil.getConfiguration().getPackageName() + "." + ConfigUtil.getConfiguration().getPath().getMapper());
        mapperData.put("BasePackageName", ConfigUtil.getConfiguration().getPackageName());
        mapperData.put("DaoPackageName", ConfigUtil.getConfiguration().getPath().getMapper());
        mapperData.put("EntityPackageName", ConfigUtil.getConfiguration().getPath().getEntity());
        mapperData.put("ClassName", className);
        mapperData.put("EntityName", StringUtil.firstToLowerCase(className));
        mapperData.put("TableName", tableName);
        mapperData.put("InsertProperties", GeneratorUtil.generateMapperInsertProperties(tableInfos));
        mapperData.put("PrimaryKey", getPrimaryKeyColumnInfo(tableInfos).getColumnName());
        mapperData.put("WhereId", "#{" + getPrimaryKeyColumnInfo(tableInfos).getPropertyName() + "}");
        mapperData.put("Id", "#{id}");
        if (!StringUtil.isBlank(parentForeignKey)) { // 多对多
            mapperData.put("ColumnMap", GeneratorUtil.generateMapperColumnMap(tableName, parentTableName, tableInfos, parentTableInfos, StringUtil.firstToLowerCase(parentClassName)));
            mapperData.put("ResultMap", GeneratorUtil.generateMapperResultMap(tableInfos));
            mapperData.put("Association", "");
            mapperData.put("Collection", GeneratorUtil.generateMapperCollection(parentTableInfos, parentClassName, ConfigUtil.getConfiguration().getPackageName() + ConfigUtil.getConfiguration().getPath().getEntity()));
            mapperData.put("InsertBatchValues", GeneratorUtil.generateMapperInsertBatchValues(tableInfos, StringUtil.firstToLowerCase(className)));
            mapperData.put("InsertValues", GeneratorUtil.generateMapperInsertValues(tableInfos));
            mapperData.put("UpdateProperties", GeneratorUtil.generateMapperUpdateProperties(tableInfos));
            mapperData.put("SelectProperties", GeneratorUtil.generateMapperSelectProperties(tableInfos));
            mapperData.put("Joins", GeneratorUtil.generateMapperJoins(tableName, parentTableName, relationalTableName, foreignKey, parentForeignKey, getPrimaryKeyColumnInfo(tableInfos).getColumnName(), getPrimaryKeyColumnInfo(parentTableInfos).getColumnName()));
        } else if (!StringUtil.isBlank(foreignKey)) { // 一对多
            mapperData.put("ColumnMap", GeneratorUtil.generateMapperColumnMap(tableName, parentTableName, tableInfos, parentTableInfos, StringUtil.firstToLowerCase(parentClassName), foreignKey));
            mapperData.put("ResultMap", GeneratorUtil.generateMapperResultMap(tableInfos, foreignKey));
            mapperData.put("Association", GeneratorUtil.generateMapperAssociation(parentTableInfos, parentClassName, ConfigUtil.getConfiguration().getPackageName() + ConfigUtil.getConfiguration().getPath().getEntity()));
            mapperData.put("Collection", "");
            mapperData.put("InsertBatchValues", GeneratorUtil.generateMapperInsertBatchValues(tableInfos, StringUtil.firstToLowerCase(className), StringUtil.firstToLowerCase(parentClassName), foreignKey, getPrimaryKeyColumnInfo(parentTableInfos).getPropertyName()));
            mapperData.put("InsertValues", GeneratorUtil.generateMapperInsertValues(tableInfos, StringUtil.firstToLowerCase(parentClassName), foreignKey, getPrimaryKeyColumnInfo(parentTableInfos).getPropertyName()));
            mapperData.put("UpdateProperties", GeneratorUtil.generateMapperUpdateProperties(tableInfos, StringUtil.firstToLowerCase(parentClassName), foreignKey, getPrimaryKeyColumnInfo(parentTableInfos).getPropertyName()));
            mapperData.put("SelectProperties", GeneratorUtil.generateMapperUpdateProperties(tableInfos, StringUtil.firstToLowerCase(parentClassName), foreignKey, getPrimaryKeyColumnInfo(parentTableInfos).getPropertyName()));
            mapperData.put("Joins", GeneratorUtil.generateMapperJoins(tableName, parentTableName, foreignKey, getPrimaryKeyColumnInfo(parentTableInfos).getColumnName()));
        } else { // 单表
            mapperData.put("ColumnMap", GeneratorUtil.generateMapperColumnMap(tableName, tableInfos));
            mapperData.put("ResultMap", GeneratorUtil.generateMapperResultMap(tableInfos));
            mapperData.put("Association", "");
            mapperData.put("Collection", "");
            mapperData.put("InsertBatchValues", GeneratorUtil.generateMapperInsertBatchValues(tableInfos, StringUtil.firstToLowerCase(className)));
            mapperData.put("InsertValues", GeneratorUtil.generateMapperInsertValues(tableInfos));
            mapperData.put("UpdateProperties", GeneratorUtil.generateMapperUpdateProperties(tableInfos));
            mapperData.put("SelectProperties", GeneratorUtil.generateMapperSelectProperties(tableInfos));
            mapperData.put("Joins", "");
        }
        String filePath = FileUtil.getResourcePath() + StringUtil.package2Path(ConfigUtil.getConfiguration().getPath().getMapperXml());
        String fileName = className + "Mapper.xml";
        // 生成MapperXml文件
        FileUtil.generateToJava(FreemarketConfigUtil.TYPE_MAPPER_XML, mapperData, filePath, fileName);
    }
}
