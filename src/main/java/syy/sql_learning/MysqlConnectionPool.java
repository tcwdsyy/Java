package syy.sql_learning;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnectionPool {
    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/java_practice");
        config.setUsername("root");
        config.setPassword("123456");
        config.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        config.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        config.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        DataSource ds = new HikariDataSource(config);

        Connection conn = ds.getConnection();
        Statement st = conn.createStatement();
        String sql2 = "SELECT id, name, age FROM person WHERE age=23";
        try (ResultSet rs = st.executeQuery(sql2)) {
            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);
                long age = rs.getLong(3);
                System.out.println(id + " " + name + " " + age);
            }
        }
        conn.close();
    }
}
