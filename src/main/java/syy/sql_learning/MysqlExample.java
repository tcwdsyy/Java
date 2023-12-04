package syy.sql_learning;

import java.sql.*;

public class MysqlExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载驱动，将驱动字节码文件加载到JVM中
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.连接数据库: jdbc:mysql://<hostname>:<port>/<db>?key1=value1&key2=value2
        String url = "jdbc:mysql://localhost:3306/java_practice";
        String user = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, user, password);
        //3.获取发送SQL语句的对象
        Statement statement = connection.createStatement();
        //4.编写SQL语句，并执行SQL语句
//        String sql = "insert into person (id, name, age) values ('1', 'Jack', '23')";
//
//        int result = statement.executeUpdate(sql);
//        //5.处理结果
//        if (result > 0) {
//            System.out.println("succeed");
//        } else {
//            System.out.println("failed");
//        }

        String sql2 = "SELECT id, name, age FROM person WHERE age=23";
        try (ResultSet rs = statement.executeQuery(sql2)) {
            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);
                long age = rs.getLong(3);
                System.out.println(id + " " + name + " " + age);
            }
        }

        // 查找数据 (避免sql注入攻击，采用？占位符)
        // Statement statement = connection.createStatement();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM person WHERE name=? AND age=?");
        ps.setObject(1, "Jack");
        ps.setObject(2, "23");
        try (ResultSet rs2 = ps.executeQuery()) { // executeQuery 用于SELECT
            while (rs2.next()) {
                long id = rs2.getLong(1);
                String name = rs2.getString(2);
                long age = rs2.getLong(3);
                System.out.println(id + " " + name + " " + age);
            }
        }

        // 插入数据(并且获取主键, 插入name=k, age=23)
        try (PreparedStatement ps2 = connection.prepareStatement(
                "INSERT INTO person (name,age) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps2.setObject(1, "k");
            ps2.setObject(2, 23);
            int n = ps2.executeUpdate(); // update 用于insert
            System.out.println("插入" + n + "行");
            try (ResultSet rs3 = ps2.getGeneratedKeys()) { // getGeneratedKeys()获取一个ResultSet对象
                if (rs3.next()) {
                    long id = rs3.getLong(1);
                    System.out.println("id=" + id);
                }
            }
        }

        // 更新数据,(把k的年龄改成18)
        try (PreparedStatement ps3 = connection.prepareStatement("UPDATE person SET age=? WHERE name=?")) {
            ps3.setObject(1, 18);
            ps3.setObject(2, "k");
            int n = ps3.executeUpdate();// 返回更新的行数
            System.out.println("更新" + n + "行");
        }

        // 删除(删除k)
        try (PreparedStatement ps4 = connection.prepareStatement("DELETE FROM person WHERE name=?")) {
            ps4.setObject(1, "k");
            int n = ps4.executeUpdate();
            System.out.println("已删除" + n + "行");
        }


        //6.释放资源，先开后关
        statement.close();
        connection.close();
    }
}
