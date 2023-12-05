package syy.sql_learning;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestDateTimes {
    public static void main(String[] args) throws ParseException {
        /**
         * java.util.Date  当前系统时间
         * Tue Mar 31 19:44:55 CST 2020
         */
        System.out.println(new java.util.Date());

        /**
         * 字符串转换为util.Date类型
         * 1.自定义一个时间字符串
         * 2.日期转换：字符串转换为java.util.Date
         * 3.将日期字符串转换为util.Date类型
         * Sun Feb 02 00:00:00 CST 2020
         */
        String str = "2020-02-02";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = simpleDateFormat.parse(str);
        System.out.println(utilDate);

        /**
         * 字符串转换为sql.Date类型
         * 1.自定义一个时间字符串
         * 2.日期转换：字符串转换为java.util.Date
         * 3.将日期类型转换为util.Date类型
         * 4.将util.Date类型转换为sql.Date类型
         * 注意：getTime()方法传入的是毫秒值
         * 时间戳是指格林威治时间1970年01月01日00时00分00秒(北京时间1970年01月01日08时00分00秒)起至现在的总毫秒数。
         * 2020-03-06
         */
        String string = "2020-03-06";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilsDate = dateFormat.parse(string);
        java.sql.Date sqlDate = new java.sql.Date(utilsDate.getTime());
        System.out.println(sqlDate);

        /**
         * 利用工具将字符串转换为util.Date类型
         * Sat Feb 08 00:00:00 CST 2020
         */
        //java.util.Date date = DateUtils.strToUtilDate("2020-02-08");
        //System.out.println(date);

        /**
         * 利用工具将util.Date类型转换为sql.Date类型
         * 2020-02-08
         */
        //java.sql.Date date1 = DateUtils.utilToSqlDate(date);
        //System.out.println(date1);

        /**
         * 将当前系统时间util.Date类型转换为sql.Date类型
         * 2020-03-31
         */
        System.out.println(new java.sql.Date(new java.util.Date().getTime()));
    }
}
