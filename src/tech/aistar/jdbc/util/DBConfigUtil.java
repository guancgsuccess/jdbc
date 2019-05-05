package tech.aistar.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:读取配置文件
 * @date 2019/5/5 0005
 */
public class DBConfigUtil {

    private static Properties prop = new Properties();

    //通过静态代码块加载资源
    static{
        //获取配置文件的字节输入流
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/dbConfig.properties");
        //加载
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key){
        return key == null ? null:prop.getProperty(key);
    }
}
