package tech.aistar.jdbc.util;

import java.sql.*;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/5 0005
 */
public class JdbcUtil {

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = null;

        //比较传统的方式
        //弊端 - 在多线程的环境下 - 每个请求连接的时候都会涉及到connection对象的创建和销毁
        //造成了内存的浪费,比较耗资源.

        //连接池的概念 - 如何分配连接,如何监控连接,如何释放资源

        //连接池自己本身也是一个昂贵的资源 - 重量级的对象 - 创建和销毁的成本比较高
        //应该是一个数据库对应一个连接池.
        //一个数据库的连接池在整个应用应该是单例的.
        conn = DriverManager.getConnection(DBConfigUtil.getValue("url"),DBConfigUtil.getValue("username"),DBConfigUtil.getValue("password"));

        return conn;
    }

    /**
     * 释放资源
     * @param conn
     * @param st
     * @param rs
     */
    public static void close(Connection conn, Statement st, ResultSet rs){
        if(null!=rs){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(null!=st){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(null!=conn){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
