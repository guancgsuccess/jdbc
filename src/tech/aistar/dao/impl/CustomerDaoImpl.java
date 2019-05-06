package tech.aistar.dao.impl;

import tech.aistar.dao.ICustomerDao;
import tech.aistar.entity.Customer;
import tech.aistar.entity.Order;
import tech.aistar.entity.OrderStatus;
import tech.aistar.jdbc.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/6 0006
 */
public class CustomerDaoImpl implements ICustomerDao{
    @Override
    public List<Customer> findAllCascade() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Customer> customers = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql="select c.id cid,c.cname,o.* from db_customer c left join db_orders o on c.id = o.cid";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            //创建一个Map集合,用来分类客户
            Map<Integer,Customer> maps = new HashMap<>();

            while(rs.next()){
                //获取客户的cid
                Integer cid = rs.getInt(1);
                Customer c = null;
                if(maps.containsKey(cid)){
                    c = maps.get(cid);
                }else{
                    c = new Customer();
                    //封装数据
                    c.setId(cid);
                    c.setCname(rs.getString(2));

                    if(rs.getString(4)!=null){
                        //给客户一个存储订单信息的容器
                        c.setOrders(new HashSet<>());
                    }

                    //将该客户放入到map中
                    maps.put(cid,c);
                }

                //判断ordno是否为null
                if(rs.getString(4) != null){
                    //封装订单信息.
                    Order o = new Order();
                    o.setId(rs.getInt(3));
                    o.setOrdNo(rs.getString(4));
                    o.setPrice(rs.getDouble(5));
                    o.setCreateDate(rs.getDate(6));
                    o.setOrderStatus(Enum.valueOf(OrderStatus.class,rs.getString(7)));

                    //将该订单放入到该客户的订单容器中
                    c.getOrders().add(o);
                }
            }
            return customers = new ArrayList<>(maps.values());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,pst,rs);
        }
        return customers;
    }
}
