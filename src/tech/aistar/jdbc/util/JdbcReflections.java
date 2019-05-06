package tech.aistar.jdbc.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/6 0006
 */
public class JdbcReflections {

    /**
     * 简单对象的查询所有的
     * @param c
     * @return
     */
    public static List queryAll(Class<?> c){
        List list = new ArrayList();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select * from "+ c.getSimpleName();//约定一要求类名和表名保持一致.
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            //获取所有的Fileds
            Field[] fields = c.getDeclaredFields();

            while(rs.next()){
                //构建出一个Object对象
                Object obj = c.newInstance();//反射创建对象

                //每个属性都要进行赋值 - 通过反射
                for(Field f:fields){
                    //私有属性设置成可访问的
                    f.setAccessible(true);
                    //反射通过属性进行设置值
                    //约定二 - 类属性名和表的列名要一样.
                    f.set(obj,rs.getObject(f.getName()));
                }
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn,pst,rs);
        }
        return list;
    }
}
