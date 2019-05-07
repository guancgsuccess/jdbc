package tech.aistar.client;

import tech.aistar.jdbc.util.JdbcUtil;

import java.sql.*;
import java.util.Scanner;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/7 0007
 */
public class ClientDemo {

    public static void main(String[] args) {
        createSql();
    }

    public static void createSql(){
        Scanner sc = new Scanner(System.in);
        System.out.print("mysql> ");
        //创建一个StringBuilder,用来拼接输入的sql语句
        StringBuilder builder = new StringBuilder();
        do {
            //获取sql输入
            String sql = sc.nextLine();

            builder.append(sql+" ");

            if(builder.toString().trim().endsWith(";")){
                System.out.println(builder);//最终的合法的sql语句.
                executeSql(builder.deleteCharAt(builder.length()-1).toString());
                createSql();
                break;
            }else{
                System.out.print("    -> ");
            }
        }while(true);
    }

    private static void executeSql(String sql) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            st = conn.createStatement();

            //boolean execute(String sql);//若DQL - true,若DML - false
            //System.out.println(st.execute(sql));
            boolean flag = st.execute(sql);

            if(flag){//DQL
                //获取结果集的第二种方式
                rs = st.getResultSet();
                //获取结果集的元数据
                ResultSetMetaData rsmd = rs.getMetaData();
                //获取列的个数
                int cols = rsmd.getColumnCount();

                //输出结果集的标题
                for (int i = 1; i <= cols ; i++) {
                    System.out.printf("%-15s",rsmd.getColumnName(i));
                }
                System.out.println();

                //正文的内容
                while(rs.next()){
                    for (int i = 1; i <=cols ; i++) {
                        System.out.printf("%-15s",rs.getString(i));
                    }
                    System.out.println();
                }
            }else{
                //DML
                //只要DML执行了execute = executeUpdate
                //获取受影响的行数即可.
                int count = st.getUpdateCount();

                System.out.println("受影响行数:"+count);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
    }
}
