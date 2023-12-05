package syy.sql_learning;

import java.sql.*;

public class DBUtil {

    /**
     * 所有操作即为单线程操作，应用了多个Connection对象，我们将一个线程绑定一个Connection连接使用
     */
    private static final ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal<>();
//    private static final ThreadLocal<Connection> dbConnectionLocal = new ThreadLocal<Connection>(){
//        @Override
//        protected Connection initialValue() {
//            return super.initialValue();
//        }
//    };

    /**
     * 读取配置文件类加载时，只需要加载一次即可
     */
    static{
        /**
         * 使用类自身带的流读取配置，无需关闭
         */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    // 连接本地数据库
    public static Connection getConnection(){
        Connection connection = THREAD_LOCAL.get();
        if(connection==null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_practice", "root", "123456");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeAll(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
                THREAD_LOCAL.remove();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void begin(){
        Connection connection = getConnection();
        try{
            connection.setAutoCommit(false);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void commit(){
        Connection connection = getConnection();
        try{
            connection.commit();
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            DBUtil.closeAll(connection,null,null);
        }
    }

    public static void rollback(){
        Connection connection = getConnection();
        try{
            connection.rollback();
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            DBUtil.closeAll(connection,null,null);
        }
    }


}
