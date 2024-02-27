package syy.java_learning;

import java.text.*;
import java.util.*;

public class TimeLearning {
    public static void main(String[] args) {
        int n = 12349;
        System.out.println(Integer.toHexString(n));
        System.out.println(NumberFormat.getCurrencyInstance(Locale.CHINA).format(n));

// old ver
    // Date
        Date date = new Date();
        System.out.println(date.getYear() + 1900); // 必须加上1900
        System.out.println(date.getMonth() + 1); // 0~11，必须加上1
        System.out.println(date.getDate()); // 1~31，不能加1

        // 转换为String:
        System.out.println(date.toString());
        // 转换为GMT时区:
        System.out.println(date.toGMTString());
        // 转换为本地时区:
        System.out.println(date.toLocaleString());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //        M：输出9
        //        MM：输出09
        //        MMM：输出Sep
        //        MMMM：输出September
        System.out.println(sdf.format(date));

    //calendar
        // 当前时间:
        Calendar c = Calendar.getInstance();
        // 清除所有:
        c.clear();
        // 设置2019年:
        c.set(Calendar.YEAR, 2019);
        // 设置9月:注意8表示9月:
        c.set(Calendar.MONTH, 8);
        // 设置2日:
        c.set(Calendar.DATE, 2);
        // 设置时间:
        c.set(Calendar.HOUR_OF_DAY, 21);
        c.set(Calendar.MINUTE, 22);
        c.set(Calendar.SECOND, 23);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime()));

        // 加5天并减去2小时:
        c.add(Calendar.DAY_OF_MONTH, 5);
        c.add(Calendar.HOUR_OF_DAY, -2);

        TimeZone tzDefault = TimeZone.getDefault(); // 当前时区
        System.out.println(tzDefault.getID()); // Asia/Shanghai


// new ver

    }
}
