package syy.java_learning;
// This file has covered features of class and inheritor in java such as creating constructor, overload constructor,
// overload method, access modifiers, super constructor, override method, class casting

public class ObjectOriented1 {
    public static void main(String[] args){
        System.out.println("Person:");
        Person p = new Person("syy",18);
        System.out.println(p.getName()+" "+p.getAge());
        p.define("nihao");
        p.define(123);

        System.out.println("\nMale:");
        Male m = new Male("Jack",23,170);
        System.out.println(m.getName()+" "+m.getAge()+" "+ m.getHeight());
        m.define(66);

//        System.out.println("\nMale2:");
//        Male m2 = new Male();
//        System.out.println(m2.getName()+" "+m2.getAge()+" "+ m2.getHeight());
//        m2.define(66);

        System.out.println("\nPerson2:");
        Person p2 = new Male();//向上转型（装箱）
        p2.define(99);
        //System.out.println(p2 instanceof Person);
        Male m2 = (Male)p2;//向下转型（拆箱）
        System.out.println(m2.getHeight());
    }
}

class Person {
    private String name;
    int age;

    //重载构造方法
    Person(){
        this.name="no name";
        this.age=0;
    }
    Person(String name, int age){
        this.name=name;
        this.age=age;
    }

    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }

    //重载方法 overload
    public void define(int age){
        System.out.println("Input age is "+age);
    }
    public void define(String name){
        System.out.println("Input name is "+name);
    }
}

//继承
class Male extends Person {
    //private String name;
    private int height=50;
    int age;
    Male(){
        super(); // 调用父类建造
        this.height=90;
        this.age=5;
    }
    Male(String name, int age, int height){
        super(name,age); // 调用父类建造 如果没有默认使用super()
        this.height=height;
        this.age=5;
    }

    public int getHeight(){
        return height;
    }
    //override
    public void define(int age){
        System.out.println("The male age is "+this.age);
        System.out.println("The person age is "+super.age);
        System.out.println("Input age is "+age);
    }

}
