package com.rapid.generator.entity;

import java.io.Serializable;

/**
 * Author GreedyStar
 * Date   2018/9/7
 */
public class Configuration implements Serializable {
    /**
     * 代码作者
     */
    private String author;
    /**
     * 顶级包名
     */
    private String packageName;
    /**
     * 类型转换器全限定类名
     */
    private String convertor;
    /**
     * 代码生成路径
     */
    private Path path;
    /**
     * 数据库配置
     */
    private Db db;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPackageName() {
        return packageName == null ? "" : packageName + ".";
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getConvertor() {
        return convertor;
    }

    public void setConvertor(String convertor) {
        this.convertor = convertor;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Db getDb() {
        return db;
    }

    public void setDb(Db db) {
        this.db = db;
    }

    public static class Db {
        /**
         * 数据库URL
         */
        private String url;
        /**
         * 数据库用户名
         */
        private String username;
        /**
         * 数据库密码
         */
        private String password;

        public Db() {
        }

        public Db(String url, String username, String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class Path {
        /**
         * Controller代码包路径
         */
        private String controller;
        /**
         * Service或ServiceImpl代码包路径
         */
        private String service;
        /**
         * Service接口代码包路径
         */
        private String interf;
        /**
         * Dao代码包路径
         */
        private String mapper;
        /**
         * Entity代码包路径
         */
        private String entity;
        /**
         * Mapper映射文件路径
         */
        private String mapperXml;

        /**
         * dto代码包路径
         */
        private String dto;

        /**
         * request代码包路径
         */
        private String request;

        /**
         * response代码包路径
         */
        private String response;

        public Path() {
        }

        public Path(String controller, String service, String interf, String mapper, String entity, String mapperXml,String dto) {
            this.controller = controller;
            this.service = service;
            this.interf = interf;
            this.mapper = mapper;
            this.entity = entity;
            this.mapperXml = mapperXml;
            this.dto = dto;
        }

        public String getController() {
            return controller == null ? "" : controller;
        }

        public void setController(String controller) {
            this.controller = controller;
        }

        public String getService() {
            return service == null ? "" : service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getInterf() {
            return interf;
        }

        public void setInterf(String interf) {
            this.interf = interf;
        }

        public String getMapper() {
            return mapper == null ? "" : mapper;
        }

        public void setMapper(String mapper) {
            this.mapper = mapper;
        }

        public String getEntity() {
            return entity == null ? "" : entity;
        }

        public void setEntity(String entity) {
            this.entity = entity;
        }

        public String getRequest() {
            return request == null ? "" : request;
        }

        public void setRequest(String request) {
            this.request = request;
        }

        public String getResponse() {
            return response == null ? "" : response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        public String getMapperXml() {
            return mapperXml == null ? "" : mapperXml;
        }

        public void setMapperXml(String mapperXml) {
            this.mapperXml = mapperXml;
        }

        public String getDTO() {
            return dto == null ? "dto" : dto;
        }

        public void setDTO(String dto) {
            this.dto = dto;
        }

    }

}
