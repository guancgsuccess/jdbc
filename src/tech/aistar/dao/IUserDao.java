package tech.aistar.dao;

import tech.aistar.entity.Users;

import java.util.List;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/6 0006
 */
public interface IUserDao {

    List<Users> findAll();
}
