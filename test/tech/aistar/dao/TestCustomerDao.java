package tech.aistar.dao;

import org.junit.Test;
import tech.aistar.dao.impl.CustomerDaoImpl;
import tech.aistar.entity.Customer;
import tech.aistar.entity.Order;

import java.util.List;
import java.util.Set;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/6 0006
 */
public class TestCustomerDao {
    private ICustomerDao customerDao = new CustomerDaoImpl();

    @Test
    public void testFindCascade(){
        List<Customer> customerList = customerDao.findAllCascade();

        for (Customer c:customerList){
            System.out.println(c);

            Set<Order> orderSet = c.getOrders();

            if(null!=orderSet && orderSet.size()>0){
                System.out.println("订单信息如下:>");
                for (Order o:orderSet){
                    System.out.println("\t"+o);
                }
            }else{
                System.out.println("该客户没有订单信息!");
            }
        }
    }

    @Test
    public void testFindTwoSql(){
        List<Customer> customerList = customerDao.findAllByTwoSql();

        for (Customer c:customerList){
            System.out.println(c);

            Set<Order> orderSet = c.getOrders();

            if(orderSet.size()>0){
                System.out.println("订单信息如下:>");
                for (Order o:orderSet){
                    System.out.println("\t"+o);
                }
            }else{
                System.out.println("该客户没有订单信息!");
            }
        }
    }
}
