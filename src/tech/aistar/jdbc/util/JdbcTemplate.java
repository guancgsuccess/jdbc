package tech.aistar.jdbc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:jdbc模板
 * @date 2019/5/6 0006
 */
public class JdbcTemplate {

    /**
     * 执行的DQL语句.
     * @return
     */
    public static Object executeQuery(IPreparedStatementCallback cb,IResultSetCallback rcb){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Object obj = null;
        try {
            conn = JdbcUtil.getConnection();

            //细节操作...
            //参数 - conn
            pst = cb.createPrepareStatement(conn);
            //返回类型 -
            rs = pst.executeQuery();

            //处理结果集 - 细节操作
            obj = rcb.handlerResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtil.close(conn,pst,rs);
        }
        return obj;
    }


    /**
     * 执行的是DML操作
     */
    public static void executeUpdate(IPreparedStatementCallback cb){
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = JdbcUtil.getConnection();
            //pst = conn.prepareStatement(sql);
            //确定sql中可能含有参数?
            //使用接口...
            //细节操作...

            pst = cb.createPrepareStatement(conn);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtil.close(conn,pst,null);
        }
    }
}
