package tech.aistar.dao.page;

import org.junit.Test;
import tech.aistar.dao.page.impl.StudentDaoPageImpl;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/5 0005
 */
public class TestStudentDaoPage {
    private IStudentPageDao pageDao = new StudentDaoPageImpl();

    @Test
    public void findByPage(){
        pageDao.findByPage(2,3).forEach(System.out::println);
    }

    @Test
    public void findRows(){
        System.out.println(pageDao.getRows());
    }
}
