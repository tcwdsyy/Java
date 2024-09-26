package syy.java_learning;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.Class;

public class Reflections {
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static void main(String[] args) throws Exception{
        //正常的调用
        Reflections apple = new Reflections();
        apple.setPrice(5);
        System.out.println("Apple Price:" + apple.getPrice());
        //使用反射调用
        //获取到Reflections类的Class对象
        Class<?> clz = Class.forName("syy.java_learning.Reflections");
        //通过Class对象获取Reflections类的setPrice方法
        Method setPriceMethod = clz.getMethod("setPrice", int.class);
        //通过Class对象获取Reflections类的构造方法
        Constructor<?> appleConstructor = clz.getConstructor();
        //通过Reflections类的构造方法创建Reflections类的对象
        Object appleObj = appleConstructor.newInstance();
        //通过Reflections类的setPrice方法设置Reflections类的price属性
        setPriceMethod.invoke(appleObj, 14);
        //通过Reflections类的getPrice方法获取Reflections类的price属性
        Method getPriceMethod = clz.getMethod("getPrice");
        System.out.println("Apple Price:" + getPriceMethod.invoke(appleObj));

        //获取Class对象的三种方式
        Class<?> clz1 = Class.forName("syy.java_learning.Reflections");
        Class clz2 = Reflections.class;
        Class clz3 = apple.getClass();
    }
}


