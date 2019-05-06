package tech.aistar.dao;

import org.junit.Test;
import tech.aistar.dao.impl.StudentDaoImpl;
import tech.aistar.dao.impl.StudentDaoPreImpl;
import tech.aistar.entity.Gender;
import tech.aistar.entity.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:单元测试类
 * @date 2019/4/28 0028
 */
public class TestStudentDaoPre {

    IStudentDao studentDao = new StudentDaoPreImpl();

    IStudentDao dao2 = new StudentDaoImpl();

    /**
     * 测试保存单个学生对象
     */
    @Test
    public void testSave(){
        //1. 模拟数据
        Student s1 = new Student("1011","王静静r3",new Date(), Gender.F);
        //Student s2 = new Student("1004","tom",new Date(), Gender.M);

        studentDao.save(s1);
        //studentDao.save(s2);
    }

    @Test
    public void testUpdate(){
        //模拟更新的对象
        //1. 如果是自己new出来的,那么这个对象的id的值,在表中一定要存在.
        //Student s2 = new Student("1004","mike",new Date(), Gender.M);
       // s2.setId(6);
       /// studentDao.update(s2);

        //最好的方式,在开发中一般是根据对象的id进行更新的.

        //先根据id进行获取
        Student s = dao2.getById(6);
        //进行修改
        s.setSname("SUDO");
        studentDao.update(s);
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
        System.out.println(studentDao.getById(2));
    }

    @Test
    public void testDelById(){
        studentDao.delById(9);
    }

    @Test
    public void testGetBySname(){
        System.out.println(studentDao.findBySname("jack' or '1' = '1"));
    }


    @Test
    public void testSaveList(){
        List<Student> list01 = new ArrayList<>();

        for (int i = 0; i < 100000; i++) {
            Student s = new Student("200"+i,"t"+i,new Date(),i%2==0?Gender.F:Gender.M);
            list01.add(s);
        }

        long date = System.currentTimeMillis();

        studentDao.saveList(list01);

        System.out.println("耗时:"+(System.currentTimeMillis() - date)+"ms");//耗时:21504ms
    }
}
