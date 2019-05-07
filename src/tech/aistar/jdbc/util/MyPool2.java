package tech.aistar.jdbc.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:自定义连接池
 * @date 2019/5/7 0007
 */
public class MyPool2 {

    //初始化连接数
    int init_count = 3;

    //记录当前的连接数
    int curr_count = 0;

    //最大容量 - 连接池中的最大连接数
    int maxActive = 6;

    //存放连接的容器
    LinkedList<Connection> pool = new LinkedList<>();

    public MyPool2(){
        for (int i = 0; i < init_count; i++) {
            pool.add(createConnection());
        }
    }

    /**
     * 创建链接 - 使用的是JDK的动态代理.
     */
    public Connection createConnection(){
        //1. 获取本身的conn对象
        Connection proxy = null;
        try {
            Connection conn = JdbcUtil.getConnection();//DriverManager.getConnection()

            //获取连接的代理对象
            proxy = (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(), new Class[]{Connection.class}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Object result = null;
                    //判断是否调用的是close方法
                    String methodName = method.getName();
                    if("close".equals(methodName) && curr_count<=init_count){
                        System.out.println("放回连接池...");
                        curr_count--;
                        pool.addLast(conn);
                    }else if("close".equals(methodName) && curr_count>init_count){
                        System.out.println("销毁....");
                        //销毁的操作.
                        curr_count--;
                        if(null!=conn){
                            conn.close();
                        }
                    }else{
                        result = method.invoke(conn,args);
                    }
                    return result;
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proxy;
    }

    public Connection getConnection(){
            //判断池子中是否还有连接
            if (pool.size() > 0) {
                curr_count++;
                return pool.removeFirst();
            } else if (curr_count <= maxActive) {
                System.out.println("初始化容量使用完毕,进行扩容...");
                curr_count++;
                return createConnection();
            } else {
                throw new RuntimeException("sorry,连接池已经到达最大数量了!");
            }
    }
}
