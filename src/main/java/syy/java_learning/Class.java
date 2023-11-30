package syy.java_learning;

public class Class {
    public static void main(String[] args) {
    // 成员内部类
        Outer outer = new Outer();
        outer.method1();//访问外部类方法
        Outer.Inner inner = outer.new Inner();//特殊的实例化方式
        inner.methos2();//访问内部类方法
        System.out.println(inner.num2);//外部实例化后访问内部类num2

    // 静态内部类
        System.out.println(Outer2.Inner2.num2);//访问静态内部类num2
        Outer2.Inner2 inner2 = new Outer2.Inner2();
        inner2.method2();//访问静态内部类的非静态方法
        Outer2.Inner2.method1();//直接访问静态内部类的静态方法

    //局部内部类
        Outer3 outer3 = new  Outer3();
        outer3.method1();

    //匿名内部类
        Lamp lamp = new Lamp();
        //1.这里利用了局部内部类
        class RedLight implements Light {
            public void shine() {
                System.out.println("shine in red");
            }
        }
        lamp.on(new RedLight());
        //2.这里利用了匿名内部类，没有类名，直接去new，去覆盖方法就OK
        lamp.on(new Light() {
            public void shine() {
                System.out.println("shine in yellow");
            }
        });
    }
}
class Outer {
    private int num1 = 111;

    public void method1() {
        System.out.println("This is OuterClassMethod");
    }

    //成员内部类
    class Inner {
        //不能定义静态成员，因为成员内部类，不能脱离外部类对象而独立存在
        //static String s = "Hello";
        int num2 = 222;

        public void methos2() {
            System.out.println(Outer.this.num1);//访问外部类num1
            System.out.println(num2);//内部直接访问内部类num2
            System.out.println("This is InnerClassMethod");
        }
    }
}

class Outer2 {
    private static int num1 = 111;

    String s = "Hello";

    //静态内部类
    static class Inner2 {
        static int num2 = 222;

        public static void method1() {

            System.out.println(Outer2.num1);//访问外部类中的私有属性

//			System.out.println(Outer.s);//静态不能直接访问非静态

            System.out.println("This is InnerClass StaticMethod");

        }

        public void method2() {
            System.out.println("This is InnerClass Method");
        }
    }
}

class Outer3 {
    int num1 = 111;

    //外部实例方法
    public void method1() {
        //与类和常量池、堆、栈的开始顺序和回收有关，这里必须是final
        //而jdk8提供默认为final，写不写都默认隐式存在
        final int num3 = 333;
        //局部内部类
        class Inner {
            int num2 = 222;
            public void method2() {
                System.out.println(Outer3.this.num1);//访问外部类的num1
                System.out.println(this.num2);//访问内部类num2，内部类访问优先
                System.out.println(num3);
                System.out.println("This is Inner method");
            }
        }
        Inner inner = new Inner();//创建Inner对象
        System.out.println(inner.num2);//访问内部类num2
        inner.method2();//访问内部类方法
    }
}

interface Light {
    void shine();
}

class Lamp {
    public void on(Light light) {
        light.shine();
    }
}