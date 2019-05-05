package tech.aistar.serice;

import org.junit.Test;
import tech.aistar.entity.Student;
import tech.aistar.service.IStudentService;
import tech.aistar.service.impl.StudentServiceImpl;
import tech.aistar.vo.PageList;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/5 0005
 */
public class TestStudentService {
    private IStudentService studentService = new StudentServiceImpl();

    @Test
    public void testFind(){
        PageList<Student> page = studentService.findAllByPage(3,3);

        System.out.println("当前页:"+page.getPageNow());
        System.out.println("总页:"+page.getPageCounts());
        System.out.println("每页显示:"+page.getPageSize()+"条");
        System.out.println("总的条目:"+page.getRows());

        page.getDatas().forEach(System.out::println);
    }
}
