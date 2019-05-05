package tech.aistar.dao.page;

import tech.aistar.entity.Student;

import java.util.List;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/5 0005
 */
public interface IStudentPageDao {

    /**
     * @param pageNow 当前页
     * @param pageSize 每页显示的条数
     * @return
     */
    List<Student> findByPage(Integer pageNow, Integer pageSize);

    /**
     * 查询所有的条数
     * @return
     */
    int getRows();

}
