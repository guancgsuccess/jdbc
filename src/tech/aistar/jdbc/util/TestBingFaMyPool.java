package tech.aistar.jdbc.util;


import java.sql.Connection;

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
                    Connection conn = pool .getConneciton();
                    System.out.println(Thread.currentThread().getName()+":"+conn+"->"+pool);
                    System.out.println("当前连接数:"+pool.curr_count);//2
                    System.out.println("连接池数量(剩余):"+ pool.pool.size());//1

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
