package com.rapid.generator.invoker;

import com.rapid.generator.invoker.base.*;
import com.rapid.generator.utils.*;

/**
 * Author GreedyStar
 * Date   2018/9/5
 */
public class One2ManyInvoker extends AbstractInvoker {

    @Override
    protected void getTableInfos() throws Exception {
        tableInfos = connectionUtil.getMetaData(tableName);
        parentTableInfos = connectionUtil.getMetaData(parentTableName);
    }

    @Override
    protected void initTasks() {
        taskQueue.initOne2ManyTasks(tableName, className, parentTableName, parentClassName, foreignKey, tableInfos, parentTableInfos);
    }

    public static class Builder extends AbstractBuilder {
        private One2ManyInvoker invoker = new One2ManyInvoker();

        public Builder setTableName(String tableName) {
            invoker.setTableName(tableName);
            return this;
        }

        public Builder setClassName(String className) {
            invoker.setClassName(className);
            return this;
        }

        public Builder setParentTableName(String parentTableName) {
            invoker.setParentTableName(parentTableName);
            return this;
        }

        public Builder setParentClassName(String parentClassName) {
            invoker.setParentClassName(parentClassName);
            return this;
        }

        public Builder setForeignKey(String foreignKey) {
            invoker.setForeignKey(foreignKey);
            return this;
        }

        @Override
        public AnaTask build() {
            if (!isParamtersValid()) {
                return null;
            }
            return invoker;
        }

        @Override
        public void checkBeforeBuild() throws Exception {
            if (StringUtil.isBlank(invoker.getTableName())) {
                throw new Exception("Expect table's name, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getParentTableName())) {
                throw new Exception("Expect parent table's name, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getForeignKey())) {
                throw new Exception("Expect foreign key, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getClassName())) {
                invoker.setClassName(GeneratorUtil.generateClassName(invoker.getTableName()));
            }
            if (StringUtil.isBlank(invoker.getParentClassName())) {
                invoker.setParentClassName(GeneratorUtil.generateClassName(invoker.getParentTableName()));
            }
        }
    }
}
