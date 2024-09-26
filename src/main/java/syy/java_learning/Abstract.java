package syy.java_learning;

public class Abstract {
    // Animal a = new Animal();
    public static void main(String[] args) {
        Animal a = new Dog();
        a.eat();
        a.sleep();

        Cat c = new Cat();
        System.out.println(c.getAge());
    }
}

abstract class Animal {
    String breed;
    int age;
    String sex;

    public Animal() {
        this.age = 5;
        this.sex = "idk";
    }

    public abstract void eat();

    public void sleep() {
        System.out.println("The animal is sleeping");
    }
}

class Dog extends Animal {
    public void eat() {
        System.out.println("The dog is eating");
    }


    public void sleep() {
        System.out.println("The dog is sleeping");
    }
}

class Cat extends Dog {
    public Cat() {
        super();
        this.age += 3;
    }

    public int getAge() {
        return age;
    }
}