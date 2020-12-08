package com.rapid.generator.utils;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

/**
 * Author GreedyStar
 * Date   2018/4/19
 */
public class FileUtil {

    /**
     * @param type     使用模板类型
     * @param data     填充数据
     * @param filePath 输出文件
     * @throws IOException
     * @throws TemplateException
     */
    public static void generateToJava(int type, Object data, String filePath, String fileName) throws IOException, TemplateException {
        String path = filePath + fileName; // 待生成的代码文件路径
        // 已存在的文件不予覆盖
        File file = new File(path);
        if (file.exists()) {
            path += ".generated";
            System.err.printf("%s already exit. Generating %s \n", fileName, path);
        } else {
            System.out.printf("Generating %s \n", path);
        }
        // 代码生成路径目录不存在则自动创建
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Template tpl = getTemplate(type); // 获取模板文件
        // 填充数据
        StringWriter writer = new StringWriter();
        tpl.process(data, writer);
        writer.flush();
        // 写入文件
        FileOutputStream fos = new FileOutputStream(path);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw, 1024);
        tpl.process(data, bw);
        writer.close();
        bw.close();
    }

    /**
     * 获取模板
     *
     * @param type 模板类型
     * @return
     * @throws IOException
     */
    private static Template getTemplate(int type) throws IOException {
        switch (type) {
            case FreemarketConfigUtil.TYPE_ENTITY:
                return FreemarketConfigUtil.getInstance().getTemplate("Entity.ftl");
            case FreemarketConfigUtil.TYPE_DTO:
                return FreemarketConfigUtil.getInstance().getTemplate("DTO.ftl");
            case FreemarketConfigUtil.TYPE_MAPPER:
                return FreemarketConfigUtil.getInstance().getTemplate("Mapper.ftl");
            case FreemarketConfigUtil.TYPE_SERVICE:
                return FreemarketConfigUtil.getInstance().getTemplate("Service.ftl");
            case FreemarketConfigUtil.TYPE_CONTROLLER:
                return FreemarketConfigUtil.getInstance().getTemplate("Controller.ftl");
            case FreemarketConfigUtil.TYPE_MAPPER_XML:
                return FreemarketConfigUtil.getInstance().getTemplate("MapperXml.ftl");
            case FreemarketConfigUtil.TYPE_INTERFACE:
                return FreemarketConfigUtil.getInstance().getTemplate("Interface.ftl");
            case FreemarketConfigUtil.TYPE_REQUEST:
                return FreemarketConfigUtil.getInstance().getTemplate("Request.ftl");
            case FreemarketConfigUtil.TYPE_REQUESTLIST:
                return FreemarketConfigUtil.getInstance().getTemplate("RequestList.ftl");
            case FreemarketConfigUtil.TYPE_RESPONSE:
                return FreemarketConfigUtil.getInstance().getTemplate("Response.ftl");
            case FreemarketConfigUtil.TYPE_RESPONSELIST:
                return FreemarketConfigUtil.getInstance().getTemplate("ResponseList.ftl");
            default:
                return null;
        }
    }

    private static String getBasicProjectPath() {
        String path = new File(FileUtil.class.getClassLoader().getResource("").getFile()).getPath() + File.separator;
        StringBuilder sb = new StringBuilder();
        sb.append(path.substring(0, path.indexOf("target"))).append("src").append(File.separator).append("main").append(File.separator);
        return sb.toString();
    }

    /**
     * 获取源码路径
     *
     * @return
     */
    public static String getSourcePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getBasicProjectPath()).append("java").append(File.separator);
        return sb.toString();
    }

    /**
     * 获取资源文件路径
     *
     * @return
     */
    public static String getResourcePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(getBasicProjectPath()).append("resources").append(File.separator);
        return sb.toString();
    }

}
