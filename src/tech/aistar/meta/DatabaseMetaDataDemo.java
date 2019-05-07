package tech.aistar.meta;

import tech.aistar.jdbc.util.JdbcUtil;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:就是用来判断连接的是哪个数据库
 * @date 2019/5/7 0007
 */
public class DatabaseMetaDataDemo {
    public static void main(String[] args) throws SQLException {
        Connection conn = JdbcUtil.getConnection();

        DatabaseMetaData dbmd = conn.getMetaData();

        System.out.println(dbmd.getURL());
        System.out.println(dbmd.getUserName());
        System.out.println(dbmd.getDriverName());

        System.out.println(dbmd.getDriverVersion());
    }
}
