package tech.aistar.dao.page.impl;

import tech.aistar.dao.page.IStudentPageDao;
import tech.aistar.entity.Gender;
import tech.aistar.entity.Student;
import tech.aistar.jdbc.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/5 0005
 */
public class StudentDaoPageImpl implements IStudentPageDao{
    @Override
    public List<Student> findByPage(Integer pageNow, Integer pageSize) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        List<Student> list = new ArrayList<>();

        try {
            conn = JdbcUtil.getConnection();

            String sql="select * from db_student limit ?,?";
            pst = conn.prepareStatement(sql);

            //发送参数
            pst.setInt(1,(pageNow-1)*pageSize);
            pst.setInt(2,pageSize);

            rs = pst.executeQuery();

            while(rs.next()){
                Student s = new Student();

                Integer sid = rs.getInt(1);
                String sno = rs.getString(2);
                String sname = rs.getString(3);
                //java.util.Date
                Date birthday = rs.getDate(4);//java.sql.Date
                //getString可以用来接收任意类型的.
                String gender_str = rs.getString(5);

                //将数据封装到对象中
                s.setId(sid);
                s.setSno(sno);
                s.setSname(sname);
                s.setBirthday(birthday);

                //枚举类型 - 字符串转换成枚举类型Gender
                Gender gender = Enum.valueOf(Gender.class,gender_str);//字符串gender_str在Gender枚举中一定要存在一个枚举常量的名称和它对应
                s.setGender(gender);

                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,pst,rs);
        }
        return list;
    }

    @Override
    public int getRows() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConnection();

            String sql="select count(*) from db_student";
            pst = conn.prepareStatement(sql);

            rs = pst.executeQuery();

            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,pst,rs);
        }
        return 0;
    }
}
