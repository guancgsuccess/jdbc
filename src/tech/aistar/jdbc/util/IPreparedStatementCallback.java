package tech.aistar.jdbc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/6 0006
 */
public interface IPreparedStatementCallback {

    PreparedStatement createPrepareStatement(Connection conn) throws SQLException;
}
