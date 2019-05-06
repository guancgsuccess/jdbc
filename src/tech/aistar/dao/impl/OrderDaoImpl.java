package tech.aistar.dao.impl;

import tech.aistar.dao.IOrderDao;
import tech.aistar.entity.Customer;
import tech.aistar.entity.Order;
import tech.aistar.entity.OrderStatus;
import tech.aistar.jdbc.util.JdbcTemplate;
import tech.aistar.jdbc.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/6 0006
 */
public class OrderDaoImpl implements IOrderDao{
    @Override
    public Set<Order> findByCid(Integer cid) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs_order = null;
        Set<Order> orders = new HashSet<>();

        try {
            conn = JdbcUtil.getConnection();
            String sql_order = "select * from db_orders where cid = ?";
            pst = conn.prepareStatement(sql_order);
                pst.setInt(1,cid);
                rs_order = pst.executeQuery();//订单的
                while(rs_order.next()){
                    Order o = new Order();
                    o.setId(rs_order.getInt(1));
                    o.setOrdNo(rs_order.getString(2));
                    o.setPrice(rs_order.getDouble(3));
                    o.setCreateDate(rs_order.getDate(4));
                    o.setOrderStatus(Enum.valueOf(OrderStatus.class,rs_order.getString(5)));
                    orders.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,pst,rs_order);
        }
        return orders;
    }

    @Override
    public void delById(Integer id) {
        JdbcTemplate.executeUpdate("delete from db_orders where id = ?",id);
    }
}
