package tech.aistar.meta;

import tech.aistar.jdbc.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:可滚动可更新的结果集
 * @date 2019/5/7 0007
 */
public class ResultSetDemo {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConnection();

            //可滚动,可更新
            pst = conn.prepareStatement("select * from db_student",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            rs = pst.executeQuery();

            //绝对定位
            rs.absolute(5);

            //相对定位
            rs.relative(-2);

            //定位到最后一行
            rs.last();

            rs.beforeFirst();

            //获取行编号
            System.out.println(rs.getRow());

            //删除第三行记录
            rs.absolute(3);
            rs.deleteRow();//删除

            rs.absolute(2);
            rs.updateString(3,"小霞");
            //确认更新
            rs.updateRow();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,pst,rs);
        }


    }
}
