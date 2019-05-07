package tech.aistar.jdbc.util.dbcp;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/7 0007
 */
public class TestBingFaSimpleDataSource {
    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SimpleDataSource dataSource = SimpleDataSource.getInstance();
                    try {
                        Connection conn = dataSource.getConnection();
                        System.out.println(Thread.currentThread()+":"+conn+"->"+dataSource.dataSource);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }
}
