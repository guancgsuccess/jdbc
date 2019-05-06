package tech.aistar.dao.impl;

import tech.aistar.dao.IUserDao;
import tech.aistar.entity.Users;
import tech.aistar.jdbc.util.JdbcReflections;
import tech.aistar.jdbc.util.JdbcUtil;

import java.util.List;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/6 0006
 */
public class UserDaoImpl implements IUserDao{
    @Override
    public List<Users> findAll() {
        return JdbcReflections.queryAll(Users.class);
    }
}
