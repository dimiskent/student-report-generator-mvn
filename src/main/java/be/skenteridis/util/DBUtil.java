package be.skenteridis.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
    private static final BasicDataSource dataSource = new BasicDataSource();
    static {
        dataSource.setUsername(ConfigReader.get("db.user"));
        dataSource.setPassword(ConfigReader.get("db.password"));
        dataSource.setUrl(ConfigReader.get("db.url"));
        dataSource.setMinIdle(2);
        dataSource.setMaxIdle(5);
        dataSource.setMaxTotal(10);
    }
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
