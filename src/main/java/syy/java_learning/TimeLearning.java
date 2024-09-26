package syy.java_learning;

import java.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TimeLearning {
    public static void main(String[] args) {
        int n = 12349;
        System.out.println(Integer.toHexString(n));
        System.out.println(NumberFormat.getCurrencyInstance(Locale.CHINA).format(n));

// Old ver
    // Date
        System.out.println("\nDate:");
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
        // M：输出9
        // MM：输出09
        // MMM：输出Sep
        // MMMM：输出September
        System.out.println(sdf.format(date));

    //calendar
        System.out.println("\nCalendar:");
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
    // LocalDateTime
        System.out.println("\nLocalDate:");
        LocalDateTime d = LocalDateTime.now();
        System.out.println(d);

        LocalDate d2 = LocalDate.of(2019, 11,30);
        LocalTime t2 = LocalTime.of(15, 16, 17); // 15:16:17
        LocalDateTime dt2 = LocalDateTime.of(2019, 11, 30, 15, 16, 17);
        LocalDateTime dt3 = LocalDateTime.of(d2, t2);

        // 自定义格式化:
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()));

        // 用自定义格式解析:
        LocalDateTime dtc = LocalDateTime.parse("2019/11/30 15:16:17", dtf);
        System.out.println(dtc);

        // 日期变为31日:
        LocalDateTime dt = LocalDateTime.of(2019, 10, 26, 20, 30, 59);
        System.out.println(dt);
        LocalDateTime dt4 = dt.withDayOfMonth(31); // withxxx()
        System.out.println(dt4); // 2019-10-31T20:30:59

        // 时间比较
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime target = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        System.out.println(now.isBefore(target));
        System.out.println(LocalDate.now().isBefore(LocalDate.of(2019, 11, 19)));

        LocalDateTime start = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        LocalDateTime end = LocalDateTime.of(2020, 1, 9, 19, 25, 30);
        Duration dur = Duration.between(start, end);
        System.out.println(dur); // PT1235H10M30S

        Period p = LocalDate.of(2019, 11, 19).until(LocalDate.of(2020, 1, 9));
        System.out.println(p); // P1M21D

    // ZonedDateTime
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
        ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("America/New_York")); // 用指定时区获取当前时间
        System.out.println(zbj);
        System.out.println(zny);


    // PRACTICE
        System.out.println("===================PRACTICE=============================");
        LocalDateTime ldt = LocalDateTime.of(2019, 9, 15, 15, 16, 17);
        ZonedDateTime zbj2 = ldt.atZone(ZoneId.systemDefault());
        System.out.println(zbj2);

        ZonedDateTime zny2 = zbj2.withZoneSameInstant(ZoneId.of("America/New_York"));

        LocalDateTime departureAtBeijing = LocalDateTime.of(2019, 9, 15, 13, 0, 0);
        int hours = 13;
        int minutes = 20;
        LocalDateTime arrivalAtNewYork = calculateArrivalAtNY(departureAtBeijing, hours, minutes);
        System.out.println(departureAtBeijing + " -> " + arrivalAtNewYork);
        // test:
        if (!LocalDateTime.of(2019, 10, 15, 14, 20, 0)
                .equals(calculateArrivalAtNY(LocalDateTime.of(2019, 10, 15, 13, 0, 0), 13, 20))) {
            System.err.println("测试失败!");
        } else if (!LocalDateTime.of(2019, 11, 15, 13, 20, 0)
                .equals(calculateArrivalAtNY(LocalDateTime.of(2019, 11, 15, 13, 0, 0), 13, 20))) {
            System.err.println("测试失败!");
        }
    }

    static LocalDateTime calculateArrivalAtNY(LocalDateTime bj, int h, int m) {
        ZonedDateTime zbj = bj.atZone(ZoneId.systemDefault());
        zbj = zbj.plusHours(h).plusMinutes(m);

        return bj;
    }
}
