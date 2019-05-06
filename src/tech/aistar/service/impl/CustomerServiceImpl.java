package tech.aistar.service.impl;

import tech.aistar.dao.ICustomerDao;
import tech.aistar.dao.IOrderDao;
import tech.aistar.dao.impl.CustomerDaoImpl;
import tech.aistar.dao.impl.OrderDaoImpl;
import tech.aistar.entity.Customer;
import tech.aistar.entity.Order;
import tech.aistar.service.ICustomerSerice;

import java.util.List;
import java.util.Set;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/6 0006
 */
public class CustomerServiceImpl implements ICustomerSerice{
    private ICustomerDao customerDao = new CustomerDaoImpl();

    private IOrderDao orderDao = new OrderDaoImpl();

    /**
     * 仅仅只要加载所有的客户信息,不需要级联加载出该客户的订单信息.
     * @return
     */
    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAll();
    }

    /**
     * 级联
     * @return
     */
    @Override
    public List<Customer> getCustomerAndOrders() {
        List<Customer> customers = customerDao.getAll();//干净的客户

        //遍历customers;
        for (Customer c:customers){
            //获取当前客户的订单的信息
            Set<Order> orders = orderDao.findByCid(c.getId());
            if(orders.size()>0){
                c.setOrders(orders);
            }
        }
        return customers;
    }
}
