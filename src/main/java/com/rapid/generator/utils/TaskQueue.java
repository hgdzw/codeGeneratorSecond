package com.rapid.generator.utils;

import com.rapid.generator.entity.*;
import com.rapid.generator.task.*;
import com.rapid.generator.task.base.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Author GreedyStar
 * Date   2018-11-27
 */
public class TaskQueue {

    private LinkedList<AbstractTask> taskQueue = new LinkedList<>();

    /**
     * 初始化共性任务，包括Controller、ServiceImpl、Service、Dao任务
     *
     * @param className 类名
     */
    private void initCommonTasks(String className) {
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getController())) {
            taskQueue.add(new ControllerTask(className));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getService())) {
            taskQueue.add(new ServiceTask(className));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getInterf())) {
            taskQueue.add(new InterfaceTask(className));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getMapper())) {
            taskQueue.add(new MapperTask(className));
        }
    }

    /**
     * 初始化单表生成任务，包括Entity、Mapper、DTO任务
     *
     * @param className  类名
     * @param tableName  表名
     * @param tableInfos 表元数据
     */
    public void initSingleTasks(String className, String tableName, List<ColumnInfo> tableInfos) {
        initCommonTasks(className);
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getEntity())) {
            taskQueue.add(new EntityTask(className, tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getDTO())) {
            taskQueue.add(new DTOTask(className, tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getMapper())) {
            taskQueue.add(new MapperXmlTask(className, tableName, tableInfos));
        }
        //request  response
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getMapper())) {
            taskQueue.add(new RequestTask(className, tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getMapper())) {
            taskQueue.add(new RequestListTask(className,  tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getMapper())) {
            taskQueue.add(new ResponseTask(className, tableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getMapper())) {
            taskQueue.add(new ResponseListTask(className, tableInfos));
        }


    }

    /**
     * 初始化单表生成任务，包括Entity、Mapper任务
     *
     * @param tableName        主表名
     * @param className        主类名
     * @param parentTableName  父表名
     * @param parentClassName  父类名
     * @param foreignKey       外键列
     * @param tableInfos       主表元数据
     * @param parentTableInfos 父表元数据
     */
    public void initOne2ManyTasks(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        initCommonTasks(className);
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getEntity())) {
            taskQueue.add(new EntityTask(className, parentClassName, foreignKey, tableInfos));
            taskQueue.add(new EntityTask(parentClassName, parentTableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getMapper())) {
            taskQueue.add(new MapperXmlTask(tableName, className, parentTableName, parentClassName, foreignKey, tableInfos, parentTableInfos));
        }
    }

    /**
     * 初始化单表生成任务，包括Entity、Mapper任务
     *
     * @param tableName           主表名
     * @param className           主类名
     * @param parentTableName     父表名
     * @param parentClassName     父类名
     * @param foreignKey          主表外键列
     * @param parentForeignKey    父表外键列
     * @param relationalTableName 关系表名
     * @param tableInfos          主表元数据
     * @param parentTableInfos    父表元数据
     */
    public void initMany2ManyTasks(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, String parentForeignKey, String relationalTableName, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        initCommonTasks(className);
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getEntity())) {
            taskQueue.add(new EntityTask(className, parentClassName, foreignKey, parentForeignKey, tableInfos));
            taskQueue.add(new EntityTask(parentClassName, parentTableInfos));
        }
        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPath().getMapper())) {
            taskQueue.add(new MapperXmlTask(tableName, className, parentTableName, parentClassName, foreignKey, parentForeignKey, relationalTableName, tableInfos, parentTableInfos));
        }
    }

    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }

    public AbstractTask poll() {
        return taskQueue.poll();
    }

}
