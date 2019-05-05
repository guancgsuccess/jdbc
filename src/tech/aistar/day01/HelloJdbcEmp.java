package tech.aistar.day01;

import java.sql.*;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:查询s_emp表中的id,first_name,salary
 * @date 2019/4/28 0028
 */
public class HelloJdbcEmp {
    public static void main(String[] args) {
        Connection conn = null;

        Statement st = null;

        ResultSet rs = null;

        //1. 获取驱动 - 将驱动类加载进行内存
        try {
            //oracle.jdbc.driver.OracleDriver
            Class.forName("com.mysql.jdbc.Driver");

            //2. 获取连接
            //通过java.sql.DriverManger类中提供的static Connection getConnection(String url,String user,String password);
            //user,passoword - 登录db
            //url的格式 - 不同的数据库的url格式都是不一样的
            //2-1. 准备连接的参数
            String user = "root";
            String password = "root";
            //url的格式  主协议:子协议//主机名称:端口号/数据库名?参数...
            //url = "jdbc:oracle:thin:@localhost:1521:xe";
            String url = "jdbc:mysql://localhost:3306/aistar?useSSL=false&useUnicode=false&characterEncoding=utf8";
            conn = DriverManager.getConnection(url,user,password);//标准的连接方式.和数据库的交互比较频繁.

            //3.创建Statement对象 - 发送SQL - DB server
            //3-1. 准备sql语句
            String sql = "select id,first_name,salary from s_emp";

            //3-2.创建语句对象
            st = conn.createStatement();

            //4. 获取结果集对象 - 执行的是DQL语句,需要处理结果集的
            rs = st.executeQuery(sql);

            //5. 处理结果集
            //认识一下ResultSet结果集是一个什么东西?
            //它保存的不是真实的查询之后的数据,而是保存的是指向查询结果的光标.默认是停留在第一行记录的上方.

            //获取光标的位置
            while(rs.next()){//如果下一行是无效行,那么则返回false
                //根据查询结果之后的列的名称 - 可读性强
                //int id = rs.getInt("id");
                //String first_name = rs.getString("first_name");
                //double salary = rs.getDouble("salary");


                //根据列的序号进行获取,列的序号从1开始,性能高
                int id = rs.getInt(1);
                String first_name = rs.getString(2);
                double salary = rs.getDouble(3);
                System.out.println(id+"\t"+first_name+"\t"+salary);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            //6. 释放资源
            //注意顺序rs  st  conn
            if(null != rs){
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
}
