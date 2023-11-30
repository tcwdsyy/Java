package syy.java_learning;

import java.util.Scanner; //1.引入包
public class Basic1 {

    public static void main (String [] args){
        //System.out.println("This is a java learning");

        //整数
        byte b = 127; //取值范围 -128 ~ 127 （共计256个数字）          1 byte
        short s = 32767; //取值范围 -32768 ~ 32767 （共计65536个数字） 2 bytes
        int i = 2147483647; //取值范围 -2147483648 ~ 2147483647     4 bytes
        long l = 2147483648L; //                                   8 bytes
        byte myByte = (byte)128;
        //有符号数，首位为符号位，符号位为0时，代表整数，为1时代表负数
        //当byte的最大取值（127）+ 1时，则有效的8位空间中，符号位发生变化，将128变为了负数
        //System.out.println(myByte);

        //小数
        float f = 1.2F; //need to add a cast letter "F" when defining a float   4 bytes
        double d = 1.3;//Java中任何一个小数，默认类型是double                        8 bytes

        //char      2 bytes
        char c = 'A';
        char c3 = '\u0042'; // '\u0042' 16进制66
        //System.out.println(c3);
        char c4 = 'a';
        //System.out.println( (int)c4 ); //将c4所代表的文字的对应整数打印出来
        int num1 = 123;
        int num2 = 567;
        String str = "abc";
        String newStr = num1 + num2 + str;//坑 ，两个操作数为数值时，相加； 两个操作数有一个为String，拼接。
        System.out.println(newStr);

        Scanner input = new Scanner(System.in); //引用数据类型的声明方式
        System.out.println("请输入一个值：");
        int in = input.nextInt(); //作用：可以在控制台接收一个整数的值，并保存在变量中。
        System.out.println( "您输入的值为：" + in );// 您输入的值为：123
    }

    public void cast1() { //基本数据类型与包装类之间的转化
        int i = 10;
        System.out.println(i);
        boolean b = false;
        //基本数据类型-->对应的包装类：调用包装类的构造器
        Integer integer1 = new Integer(i);
        System.out.println(integer1.toString());

        //赋值属性(形参)"12.30"同样也是float类型
        Float f = new Float(12.30F);
        System.out.println(f);
        integer1 = new Integer("12");
        System.out.println(integer1);

        //""内必须也得符合赋值的基本数据类型规则
        //否则报错java.lang.NumberFormatException
        integer1 = new Integer("12dd");
        //System.out.println(integer1);
        Boolean b1 = new Boolean(true);
        System.out.println(b1);

        //boolean类型则""中不是true则其他都是false，不会出现报错
        b1 = new Boolean("true1");
        System.out.println(b1);//false


        //包装类-->基本数据类型：调用包装类的Xxx的XxxValue()方法
        int i2 = integer1.intValue();
        System.out.println(i2);
        float f1 = f.floatValue();
        System.out.println(f1);

        //自动装箱和拆箱
        int i4 = 12;
        Integer i3 = i4;//自动装箱
        int i5 = i3;//自动拆箱
    }

    public void cast2() {
        //基本数据类型、包装类-->String类：调用String类的静态的valueOf(Xxx x)方法
        int i1 = 10;
        String str1 = i1 + "";//"10"
        System.out.println(str1);
        Integer i2 = i1;
        String str2 = String.valueOf(i1);
        System.out.println(str2);
        //String类-->基本数据类型、包装类：调用包装类的parseXxx(String str)方法
        int i3 = Integer.parseInt(str2);
        System.out.println(i3);
    }

    public static <E> void print  (E element){
        System.out.println(element);
    }
}
