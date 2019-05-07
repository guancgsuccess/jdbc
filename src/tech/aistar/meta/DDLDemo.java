package tech.aistar.meta;

import tech.aistar.jdbc.util.JdbcUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:读取.sql脚本文件并且执行
 * @date 2019/5/7 0007
 */
public class DDLDemo {
    public static void main(String[] args) {
        readSql("src/tech/aistar/meta/user.sql");
    }

    public static void readSql(String path){
        Connection conn = null;
        //原因:一个statement对象可以执行多次发送sql
        Statement st = null;
        try {
            conn = JdbcUtil.getConnection();
            st = conn.createStatement();
            //nio流...
            Path dir = Paths.get(path);
            List<String> lines = Files.readAllLines(dir);

            //定义一个StringBuilder,用来拼接完整的sql语句
            StringBuilder sqlBuilder = new StringBuilder();
            for (String sql:lines){
                sqlBuilder.append(sql);
                //判断是否为一条完整的
                if(sql.trim().endsWith(";")){
                    //让java抛出异常之后能够继续执行...
                    try {
                        st.execute(sqlBuilder.toString());
                    } catch (SQLException e) {
                        //e.printStackTrace();
                        if(sqlBuilder.toString().toLowerCase().startsWith("drop")){
                            System.out.println("sorry,table is not exists!");
                        }
                    }
                    //进行清空操作
                    sqlBuilder.delete(0,sqlBuilder.capacity());
                }
            }
            System.out.println("sql脚本执行成功!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
