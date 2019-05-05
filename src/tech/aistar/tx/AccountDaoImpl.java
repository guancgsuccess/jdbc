package tech.aistar.tx;

import tech.aistar.jdbc.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/5 0005
 */
public class AccountDaoImpl implements IAccountDao{
    @Override
    public void transfer(String srcno, String targetno, double money) {
        Connection conn = null;

        PreparedStatement pst = null;

        try {
            conn = JdbcUtil.getConnection();

            //设置手动提交方式
            conn.setAutoCommit(false);

            String sql = "update t_account set balance = balance - ? where accno = ?";

            pst = conn.prepareStatement(sql);

            //发送参数
            pst.setDouble(1,money);
            pst.setString(2,srcno);

            pst.executeUpdate();

            System.out.println(10/0);

            pst.setDouble(1,-money);
            pst.setString(2,targetno);
            pst.executeUpdate();

            conn.commit();

        } catch (Exception e) {
            if(null!=conn){
                try {
                    conn.rollback();//回滚事务
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally{
            JdbcUtil.close(conn,pst,null);
        }
    }

    public static void main(String[] args) {
        new AccountDaoImpl().transfer("1009","1008",50.0);
    }
}
