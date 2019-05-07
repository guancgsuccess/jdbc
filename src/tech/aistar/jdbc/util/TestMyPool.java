package tech.aistar.jdbc.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/7 0007
 */
public class TestMyPool {
    public static void main(String[] args) throws SQLException {
        MyPool2 pool = new MyPool2();

        Connection conn1 = pool.getConnection();
        Connection conn2 = pool.getConnection();
        Connection conn3 = pool.getConnection();
        Connection conn4 = pool.getConnection();

        System.out.println("当前连接数:"+pool.curr_count);//2
        System.out.println("连接池数量(剩余):"+ pool.pool.size());//1

        //此处并不是真正的去做连接的关闭操作
        //也许做的是将用完的连接放回到连接池中吧.
        conn1.close();
        conn2.close();
        conn3.close();
        conn4.close();

        System.out.println("当前连接数:"+pool.curr_count);
        System.out.println("连接池数量(剩余):"+ pool.pool.size());

    }
}
