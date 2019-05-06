package tech.aistar.dao.impl;

import tech.aistar.dao.IStudentDao;
import tech.aistar.entity.Gender;
import tech.aistar.entity.Student;
import tech.aistar.jdbc.util.JdbcTemplate;

import java.sql.*;
import java.util.List;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:学生dao实现类
 * @date 2019/4/28 0028
 */
@SuppressWarnings("all")
public class StudentDaoImpl implements IStudentDao{
    @Override
    public void save(Student s) {
        Connection conn = null;
        Statement st = null;
        //1. 加载驱动 - JDBC4.0可以省略不写
        //2. 直接获取连接
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aistar?useSSL=false&useUnicode=false&characterEncoding=utf8","root","root");

            //3. 创建语句对象
            st = conn.createStatement();

            //4.发送sql语句
            //insert into db_student(sno,sname,birthay,gender) values('1004','successs','2018-04-28','F');
            String birth = String.format("%tF",s.getBirthday());//yyyy-MM-dd
            String sql = "insert into db_student(sno,sname,birthday,gender) values('"+s.getSno()+"','"+s.getSname()+"','"+birth+"','"+s.getGender()+"')";
            //String sql = "insert into db_student(sno,sname,birthday,gender) values('"+s.getSno()+"')";

            //st.executeUpdate(sql);
            int count = st.executeUpdate(sql);//返回的是受影响的行数.
            System.out.println("成功插入"+count+"行记录!");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5. 释放资源
            if(null!=st){
                try {
                    st.close();
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
    public Student getById(Integer id) {
        Connection conn = null;
        Statement st = null;

        ResultSet rs = null;

        Student s = null;

        //1. 加载驱动 - JDBC4.0可以省略不写
        //2. 直接获取连接
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aistar?useSSL=false&useUnicode=false&characterEncoding=utf8","root","root");

            //3. 创建语句对象
            st = conn.createStatement();

            //4.发送sql语句
            String sql = "select id,sno,sname,birthday,gender from db_student where id = "+id;

            rs = st.executeQuery(sql);

            //5.处理结果集
            if(rs.next()){//因为id是唯一的
                s = new Student();

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
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //6. 释放资源
            if(null!=rs){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(null!=st){
                try {
                    st.close();
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
        return s;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public void delById(Integer id) {
        JdbcTemplate.executeUpdate(conn -> {
            PreparedStatement pst = null;
            String sql="delete from db_student where id = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            return pst;
        });
    }

    @Override
    public void update(Student s) {

    }


    @Override
    public Student findBySname(String sname) {
            Connection conn = null;
            Statement st = null;

            ResultSet rs = null;

            Student s = null;

            //1. 加载驱动 - JDBC4.0可以省略不写
            //2. 直接获取连接
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aistar?useSSL=false&useUnicode=false&characterEncoding=utf8","root","root");

                //3. 创建语句对象
                st = conn.createStatement();

                //4.发送sql语句
                String sql = "select id,sno,sname,birthday,gender from db_student where sname = '"+sname+"'";

                rs = st.executeQuery(sql);

                //5.处理结果集
                if(rs.next()){//因为id是唯一的
                    s = new Student();
                    Integer sid = rs.getInt(1);
                    String sno = rs.getString(2);
                    sname = rs.getString(3);
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
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                //6. 释放资源
                if(null!=rs){
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if(null!=st){
                    try {
                        st.close();
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
            return s;
        }

    @Override
    public void saveList(List<Student> students) {

    }
}
