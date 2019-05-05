package tech.aistar.dao;

import tech.aistar.entity.Student;

import java.util.List;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:学生DAO对象
 * @date 2019/4/28 0028
 */
public interface IStudentDao {

    /**
     * 保存一个学生对象
     * @param s
     */
    void save(Student s);

    /**
     * 根据id进行查询操作
     * @param id
     * @return
     */
    Student getById(Integer id);

    /**
     * 查询所有的学生信息
     * @return
     */
    List<Student> findAll();

    /**
     * 根据id进行删除操作
     * @param id
     */
    void delById(Integer id);

    /**
     * 根据学生的id进行更新操作.
     * @param s
     */
    void update(Student s);

    /**
     * 根据学生的名字进行查找
     * @param sname 唯一性
     * @return
     */
    Student findBySname(String sname);

    /**
     * 批处理
     * @param students
     */
    void saveList(List<Student> students);
}
