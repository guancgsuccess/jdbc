package tech.aistar.dao;

import org.junit.Test;
import tech.aistar.dao.impl.OrderDaoImpl;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/6 0006
 */
public class TestOrderDao {
    private IOrderDao orderDao = new OrderDaoImpl();
    @Test
    public void testDelById(){
        orderDao.delById(1);
    }
}
