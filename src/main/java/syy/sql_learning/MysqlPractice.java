package syy.sql_learning;
import java.util.Scanner;
public class MysqlPractice {
    public static void main(String[] args) {
        AccountSystem as = new AccountSystem();
        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎使用中国银行ATM机");
        int choice = 0;
        do {
            System.out.println("1.开户 2.存款 3.取款 4.转账 5.修改密码 6.查看账户余额 7.注销 0.退出");
            System.out.print("请选择你的操作：");
            choice = sc.nextInt();
            switch(choice) {
                case 1:
                    as.register();
                    break;
                case 2:
                    as.saveMoney();
                    break;
                case 3:
                    as.takeMoney();
                    break;
                case 4:
                    as.transfers();
                    break;
                case 5:
                    as.updatePassword();
                    break;
                case 6:
                    as.getBalance();
                    break;
                case 7:
                    as.logout();
                    break;
                case 0:
                    as.closeConnection();
                    //return;
                default:
                    break;
            }
        } while(choice != 0);
    }
}
