package tech.aistar.dao;

import tech.aistar.entity.Customer;

import java.util.List;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/6 0006
 */
public interface ICustomerDao {

    /**
     * 查询所有的客户信息,如果客户存在订单信息,则需要加载出订单
     * 实际上开发中,尽量不要使用left join
     *
     * 第一种方式:采用一条sql语句
     * @return
     */
    List<Customer> findAllCascade();

    /**
     * 级联查询 - 需要两条sql语句
     * 一个PreparedStatement对象只能编译一个sql语句,如果希望继续使用,需要先关闭.
     * @return
     */
    List<Customer> findAllByTwoSql();

    /**
     * 只需要查询客户本身的信息,不要做级联.
     * @return
     */
    List<Customer> getAll();

}
