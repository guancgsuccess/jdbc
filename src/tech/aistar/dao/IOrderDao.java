package tech.aistar.dao;

import tech.aistar.entity.Order;

import java.util.Set;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/6 0006
 */
public interface IOrderDao {

    /**
     * 根据客户的cid来查询订单
     * @param cid
     * @return
     */
    Set<Order> findByCid(Integer cid);
}
