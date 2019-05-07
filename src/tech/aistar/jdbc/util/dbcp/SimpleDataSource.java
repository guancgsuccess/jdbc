package tech.aistar.jdbc.util.dbcp;

import org.apache.commons.dbcp.BasicDataSource;
import tech.aistar.jdbc.util.DBConfigUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/7 0007
 */
public class SimpleDataSource {

    private static SimpleDataSource instance = null;

    BasicDataSource dataSource = new BasicDataSource();

    public static SimpleDataSource getInstance(){
        if(instance == null){
            synchronized (SimpleDataSource.class){
                if(instance == null){
                    instance = new SimpleDataSource();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private SimpleDataSource(){
        //设置DB的基本参数
        dataSource.setUrl(DBConfigUtil.getValue("url"));
        dataSource.setUsername(DBConfigUtil.getValue("username"));
        dataSource.setPassword(DBConfigUtil.getValue("password"));

        //设置连接池参数
        //设置初始化连接数量
        dataSource.setInitialSize(Integer.parseInt(DBConfigUtil.getValue("initialSize")==null?"20":DBConfigUtil.getValue("initialSize")));

        //最大连接数
        dataSource.setMaxActive(Integer.parseInt(DBConfigUtil.getValue("maxActive")));

        //最小空闲数
        dataSource.setMinIdle(Integer.parseInt(DBConfigUtil.getValue("minIdle")));

        //最大空闲数
        dataSource.setMaxIdle(Integer.parseInt(DBConfigUtil.getValue("maxIdle")));
    }
}
