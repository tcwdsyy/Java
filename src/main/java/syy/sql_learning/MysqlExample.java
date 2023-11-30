package syy.sql_learning;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载驱动，将驱动字节码文件加载到JVM中
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.连接数据库
        String url = "jdbc:mysql://localhost:3306/java_practice";
        String user = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, user, password);
        //3.获取发送SQL语句的对象
        Statement statement = connection.createStatement();
        //4.编写SQL语句，并执行SQL语句
        String sql = "insert into person (id, name, age) values ('1', 'Jack', '23')";
        int result = statement.executeUpdate(sql);
        //5.处理结果
        if (result > 0) {
            System.out.println("succeed");
        } else {
            System.out.println("failed");
        }
        //6.释放资源，先开后关
        statement.close();
        connection.close();
    }
}
