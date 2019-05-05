package tech.aistar.service;

import tech.aistar.entity.Student;
import tech.aistar.vo.PageList;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/5 0005
 */
public interface IStudentService {

    PageList<Student> findAllByPage(Integer pageNow,Integer pageSize);

}
