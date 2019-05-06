package tech.aistar.serice;

import org.junit.Test;
import tech.aistar.entity.Customer;
import tech.aistar.entity.Order;
import tech.aistar.service.ICustomerSerice;
import tech.aistar.service.impl.CustomerServiceImpl;

import java.util.List;
import java.util.Set;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/6 0006
 */
@SuppressWarnings("all")
public class TestCustomerService {

    private ICustomerSerice customerSerice = new CustomerServiceImpl();
    @Test
    public void testFindCustomerAndOrder(){
        List<Customer> customerList = customerSerice.getCustomerAndOrders();
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
}
