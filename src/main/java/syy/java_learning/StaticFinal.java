package syy.java_learning;

public class StaticFinal {
    public static void main(String[] args) {
        MyClas m = new MyClas();
        m.method();

        System.out.println(MyClas.getSField());

        OuterClass.InnerClass in = new OuterClass.InnerClass(); //静态内部类可以使用 static 关键字定义，静态内部类我们不需要创建外部类来访问，可以直接访问它：
        System.out.println(in.y);
    }
}

class MyClas{
    private static String sField;
    static{// 类加载静态代码仅执行一次
        System.out.println("生成静态属性");
        sField="hello world";
    }

    public static String getSField(){
        return sField;
    }

    public static void changeSField(){
        System.out.println("called");
        sField="called once";
    }

    public void method(){
        MyClas.changeSField();//non-static method calls static method
    }

//    final修饰类：此类不能被继承
//    final修饰方法：此方法不能被覆盖
//    final修饰变量：此变量值不能被改变（无初始值，只允许赋值一次）
//    局部变量：显示初始化
//    实例变量：实现初始化、动态代码快、构造方法
//    静态常量：显示初始化、静态代码块
//    基本数据类型常量：值不可变
//    引用数据类型常量：地址不可变
}

class OuterClass {
    int x = 10;

    static class InnerClass {
        int y = 5;
    }
}
