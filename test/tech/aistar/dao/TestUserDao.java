package tech.aistar.dao;

import org.junit.Test;
import tech.aistar.dao.impl.UserDaoImpl;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/6 0006
 */
public class TestUserDao {
    private IUserDao userDao = new UserDaoImpl();
    @Test
    public void testQueryAll(){
        userDao.findAll().forEach(System.out::println);
    }
}
