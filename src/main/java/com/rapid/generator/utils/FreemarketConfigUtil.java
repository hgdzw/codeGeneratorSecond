package com.rapid.generator.utils;

import freemarker.template.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class FreemarketConfigUtil {
    private static String path = new File(FreemarketConfigUtil.class.getClassLoader().getResource("ftls").getFile()).getPath();
    public final static int TYPE_ENTITY = 0;
    public final static int TYPE_MAPPER = 1;
    public final static int TYPE_SERVICE = 2;
    public final static int TYPE_CONTROLLER = 3;
    public final static int TYPE_MAPPER_XML = 4;
    public final static int TYPE_INTERFACE = 5;
    public final static int TYPE_DTO = 6;
    public final static int TYPE_REQUEST = 7;
    public final static int TYPE_REQUESTLIST = 8;
    public final static int TYPE_RESPONSE = 9;
    public final static int TYPE_RESPONSELIST = 10;
    private static Configuration configuration;

    public static Configuration getInstance() {
        if (null == configuration) {
            synchronized (FreemarketConfigUtil.class) {
                if (null == configuration) {
                    configuration = new Configuration(Configuration.VERSION_2_3_23);
                    try {
                        if (path.contains("jar")) {
                            configuration.setClassForTemplateLoading(FreemarketConfigUtil.class, "/ftls");
                        } else {
                            configuration.setDirectoryForTemplateLoading(new File(path));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    configuration.setEncoding(Locale.CHINA, "utf-8");
                }
            }
        }
        return configuration;
    }
}
