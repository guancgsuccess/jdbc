package tech.aistar.dao;

import org.junit.Test;
import tech.aistar.dao.impl.StudentDaoImpl;
import tech.aistar.entity.Gender;
import tech.aistar.entity.Student;

import java.util.Date;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:单元测试类
 * @date 2019/4/28 0028
 */
public class TestStudentDao {

    IStudentDao studentDao = new StudentDaoImpl();

    /**
     * 测试保存单个学生对象
     */
    @Test
    public void testSave(){
        //1. 模拟数据
        Student s1 = new Student("1001","王静静",new Date(), Gender.F);
        Student s2 = new Student("1002","tom",new Date(), Gender.M);

        studentDao.save(s1);
        studentDao.save(s2);
    }

    /**
     * 测试查询所有的学生信息
     */
    @Test
    public void testFindAll(){
        studentDao.findAll().forEach(System.out::println);
    }


    @Test
    public void testGetById(){
        System.out.println(studentDao.getById(3));
    }

    @Test
    public void testGetBySname(){
        System.out.println(studentDao.findBySname("jack' or '1' = '1"));
    }
}
