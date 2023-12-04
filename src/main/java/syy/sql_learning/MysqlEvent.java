package syy.sql_learning;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlEvent {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/java_practice";
        String user = "root";
        String password = "123456";

        Connection connection = DriverManager.getConnection(url, user, password);
        try {
            // 设定隔离级别为READ COMMITTED:
            //connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            // 关闭自动提交:
            connection.setAutoCommit(false);

            // 执行多条SQL语句:
            //insert(); update(); delete();
            // 提交事务:
            connection.commit();
        } catch (SQLException e) {
            // 回滚事务:
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }


    }
}
