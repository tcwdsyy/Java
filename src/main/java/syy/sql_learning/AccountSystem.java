package syy.sql_learning;

import java.sql.*;
import java.util.Scanner;

public class AccountSystem {
    Scanner scanner = new Scanner(System.in);
    private static Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    static {
        connection = DBUtil.getConnection();
    }

    public void closeConnection() {
        DBUtil.closeAll(connection, preparedStatement, resultSet);
    }

    // 开户
    public void register(){
        System.out.print("请输入卡号：");
        String card_id = scanner.next();
        System.out.print("请输入密码：");
        String password = scanner.next();
        System.out.print("请输入用户名：");
        String username = scanner.next();
        System.out.print("请输入存储金额：");
        double balance = scanner.nextDouble();
        System.out.print("请输入预留手机号：");
        String phone = scanner.next();

        String sql = "INSERT INTO t_account (card_id, password, username, balance, phone) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1,card_id);
            ps.setObject(2,password);
            ps.setObject(3,username);
            ps.setObject(4,balance);
            ps.setObject(5,phone);
            int result = ps.executeUpdate();
            if(result>0){
                System.out.println("开户成功");
            }else {
                System.out.println("开户失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //存款
    public void saveMoney() {
        System.out.print("请输入卡号：");
        String card_id = scanner.next();
        System.out.print("请输入密码：");
        String password = scanner.next();
        System.out.print("请输入存储金额：");
        double money = scanner.nextDouble();
        if(money>0) {
            String sql = "UPDATE t_account SET balance = balance + ? WHERE card_id = ? AND password = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, money);
                ps.setObject(2, card_id);
                ps.setObject(3, password);
                int result = ps.executeUpdate();
                if (result > 0) {
                    System.out.println("存款成功！");
                } else {
                    System.out.println("卡号或密码错误！");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("输入存储金额错误！");
        }
    }

    //取款
    public void takeMoney() {
        System.out.print("请输入卡号：");
        String card_id = scanner.next();
        System.out.print("请输入密码：");
        String password = scanner.next();
        System.out.print("请输入取出金额：");
        double money = scanner.nextDouble();
        if(money>0){
            String sql = "UPDATE t_account SET balance = balance - ? WHERE card_id = ? AND password = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, money);
                ps.setObject(2, card_id);
                ps.setObject(3, password);
                int result = ps.executeUpdate();
                if (result > 0) {
                    System.out.println("取款成功！");
                } else {
                    System.out.println("卡号或密码错误！");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("输入存储金额错误！");
        }
    }

    //查看余额
    public void getBalance() {
        System.out.print("请输入卡号：");
        String card_id = scanner.next();
        System.out.print("请输入密码：");
        String password = scanner.next();
        String sql = "SELECT balance FROM t_account WHERE card_id = ? AND password = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, card_id);
            ps.setObject(2, password);
            try (ResultSet result = ps.executeQuery()){
                if(result.next()){
                    double balance = result.getDouble(1);
                    System.out.println("卡内余额为：" + balance);
                } else {
                    System.out.println("卡号或密码错误！");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //修改密码
    public void updatePassword() {
        System.out.print("请输入卡号：");
        String card_id = scanner.next();
        System.out.print("请输入密码：");
        String password = scanner.next();
        System.out.print("请输入新密码：");
        String newPassword = scanner.next();
        String sql = "UPDATE t_account SET password = ? WHERE card_id = ? AND password = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setObject(1,newPassword);
            ps.setObject(2,card_id);
            ps.setObject(3,password);
            int result = ps.executeUpdate();
            if(result>0){
                System.out.println("密码修改成功！");
            } else {
                System.out.println("卡号或密码错误！请核对后再修改！");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //注销
    public void logout() {
        System.out.print("请输入卡号：");
        String card_id = scanner.next();
        System.out.print("请输入密码：");
        String password = scanner.next();
        String sql = "DELETE FROM t_account WHERE card_id = ? AND password = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setObject(1,card_id);
            ps.setObject(2,password);
            int result = ps.executeUpdate();
            if(result >0){
                System.out.println("注销成功！");
            } else {
                System.out.println("卡号或密码错误！");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //转账
    public void transfers() {
        System.out.print("请输入付款卡号：");
        String card_id = scanner.next();
        System.out.print("请输入付款密码：");
        String password = scanner.next();
        System.out.print("请输入转账金额：");
        double money = scanner.nextDouble();
        String sql1 = "SELECT balance FROM t_account WHERE card_id = ? AND password = ?";
        try {
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setObject(1,card_id);
            preparedStatement.setObject(2,password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                double balance = resultSet.getDouble(1);
                if(balance>=money) {
                    System.out.print("请输入收款卡号：");
                    String toCard_id = scanner.next();
                    String sql2 = "SELECT balance FROM t_account WHERE card_id = ?";
                    preparedStatement = connection.prepareStatement(sql2);
                    preparedStatement.setObject(1,toCard_id);
                    resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                        String sql3 = "UPDATE t_account SET balance = balance - ? WHERE card_id = ?";
                        preparedStatement = connection.prepareStatement(sql3);
                        preparedStatement.setObject(1, money);
                        preparedStatement.setObject(2, card_id);
                        preparedStatement.executeUpdate();
                        String sql4 = "UPDATE t_account SET balance = balance + ? WHERE card_id = ?";
                        preparedStatement = connection.prepareStatement(sql4);
                        preparedStatement.setDouble(1, money);
                        preparedStatement.setString(2, toCard_id);
                        preparedStatement.executeUpdate();
                        System.out.println("转账成功！");

                    } else{
                        System.out.println("收款卡号不存在！");
                    }
                } else {
                    System.out.println("卡内余额不足！");
                }
            } else {
                System.out.println("卡号或密码错误！");
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
