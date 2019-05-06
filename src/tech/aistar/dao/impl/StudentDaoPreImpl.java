package tech.aistar.dao.impl;

import tech.aistar.dao.IStudentDao;
import tech.aistar.entity.Student;
import tech.aistar.jdbc.util.JdbcTemplate;
import tech.aistar.jdbc.util.JdbcUtil;

import java.sql.*;
import java.util.List;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/5 0005
 */
public class StudentDaoPreImpl implements IStudentDao{
    @Override
    public void save(Student s) {
        Connection conn = null;
        PreparedStatement pst = null;

        ResultSet rskeys = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/aistar?useSSL=false&useUnicode=false&CharacterEncoding=utf8","root","root");

            String sql = "insert into db_student values(null,?,?,?,?)";

            //创建预编译语句对象
            //pst = conn.prepareStatement(sql);//提前将sql语句发送到db进行编译

            //如果需要构建一个返回刚刚插入的主键值的pst对象
            pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            //发送参数
            //序号从1开始,注意数据库中的数据类型
            pst.setString(1,s.getSno());

            pst.setString(2,s.getSname());
            pst.setDate(3,new java.sql.Date(s.getBirthday().getTime()));
            pst.setString(4,String.valueOf(s.getGender()));

            pst.executeUpdate();

            //获取自增长的key
            rskeys = pst.getGeneratedKeys();
            //向下移动一行
            rskeys.next();
            int pks = rskeys.getInt(1);
            System.out.println("pks:"+pks);
           // rskeys.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,pst,rskeys);
        }
//            if(null!=pst){
//                try {
//                    pst.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            if(null!=conn){
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
       }

    @Override
    public Student getById(Integer id) {
        return (Student) JdbcTemplate.executeQuery(conn -> {
            String sql = "select * from db_student where id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            return pst;
        },rs -> {
            Student s = null;
            while(rs.next()){
                s = new Student();
                s.setId(rs.getInt(1));
                s.setSname(rs.getString(2));
            }
            return s;
        });
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public void delById(Integer id) {
        Connection conn = null;
        PreparedStatement pst = null;


        //1.获取连接
        try {
            conn = JdbcUtil.getConnection();

            String sql = "delete from db_student where id = ?";

            pst = conn.prepareStatement(sql);

            pst.setInt(1,id);

            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtil.close(conn,pst,null);
        }
    }

    @Override
    public void update(Student s) {
            Connection conn = null;
            PreparedStatement pst = null;

            try {
                conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/aistar?useSSL=false&useUnicode=false&CharacterEncoding=utf8","root","root");

                String sql = "update db_student set sname = ?,birthday=?,gender=? where id = ?";

                //创建预编译语句对象
                pst = conn.prepareStatement(sql);//提前将sql语句发送到db进行编译

                //发送参数
                //序号从1开始,注意数据库中的数据类型
                pst.setString(1,s.getSname());
                pst.setDate(2,new java.sql.Date(s.getBirthday().getTime()));
                pst.setString(3,String.valueOf(s.getGender()));
                pst.setInt(4,s.getId());

                pst.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if(null!=pst){
                    try {
                        pst.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if(null!=conn){
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    @Override
    public Student findBySname(String sname) {
        return null;
    }

    @Override
    public void saveList(List<Student> students) {
        Connection conn = null;

        PreparedStatement pst = null;

        try {
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into db_student values(null,?,?,?,?)";

            pst = conn.prepareStatement(sql);

            int count = 0;//记录参数的组的数量

            if(null!=students && students.size()>0){
                for (Student s:students){
                    pst.setString(1,s.getSno());
                    pst.setString(2,s.getSname());
                    pst.setDate(3,new java.sql.Date(s.getBirthday().getTime()));
                    pst.setString(4,String.valueOf(s.getGender()));

                    pst.addBatch();// 将给定的 SQL 命令添加到此 PreparedStatement 对象的当前命令列表中。
                    //pst.executeUpdate();
                    count++;

                    if(count==1000){
                        pst.executeBatch();
                        pst.clearBatch();
                        count = 0;
                    }
                }
            }
            if(count!=0){
                pst.executeBatch();
                pst.clearBatch();
            }
            conn.commit();
        } catch (SQLException e) {
            if(null!=conn){
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn,pst,null);
        }
    }
}
