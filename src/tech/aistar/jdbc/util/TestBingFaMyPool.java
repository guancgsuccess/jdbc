package tech.aistar.jdbc.util;


import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.RandomAccess;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/7 0007
 */
public class TestBingFaMyPool {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    MyPool pool = MyPool.getInstance();
                    System.out.println(Thread.currentThread().getName()+":"+pool.getConnection()+"->"+pool);
                }
            }).start();
        }
    }
}
