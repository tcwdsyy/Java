package syy.java_learning;

public class Exceptions {
    public static void main(String[] args) {
        StudentE stu = new StudentE();
        try {
            stu.setAge(250);//是可能出现异常的代码
        }catch(Exception e) {
            System.err.println(e.getMessage());//只获得报错的原因即可
        }

        try {
            stu.setSex("未知");//受查异常，编译期间就报错，需要处理
        }catch(SexMismatchException se) {//根据方法声明的异常，捕获相应的类型
            System.err.println(se.getMessage());
        }catch(Exception e ) {
            e.printStackTrace();
        }


//		Class.forName("xxx.xxx");//参数（包名.类名）   可能写错！
    }
}

//受查异常(是在编译期间，就必须处理的异常！ 需要声明出去)
class SexMismatchException extends Exception{
    public SexMismatchException() {}
    public SexMismatchException(String message) {
        super(message);
    }
}

//运行时异常
class AgeInputException extends RuntimeException{

    public AgeInputException() {}//支持创建无异常原因信息的异常对象
    public AgeInputException(String message) {//提供有参构造方法，支持编写异常原因信息
        super(message);//调用父类的有参构造方法，为message属性赋值。
    }
}
//在应用场景下，可以根据自身的需要，自定义异常。
class StudentE {
    private int age;//年龄
    private String sex;//性别  男  女

    public void setSex(String sex) throws SexMismatchException {
        //告知调用者，使用该方法，会存在异常。必须处理.声明的异常类型最好与抛出的异常类型一致
        if(sex.equals("男") || sex.equals("女")) {
            this.sex = sex;
        }else {
            //在用户输入一个性别后！就做好提醒！性别的输入可能不准确！   受查异常。
            throw new SexMismatchException("性别输入的值为:“男”或者“女”");
        }
    }
    public String getSex() {
        return this.sex;
    }


    public int getAge() {
        return this.age;
    }
    public void setAge(int age){
        if(age > 0 && age < 123) {
            this.age = age;
        }else {
            throw new AgeInputException("年龄的赋值应该在0岁到123岁之间");
            //抛运行时异常的父类。 不合理。现存的定义好的异常，没有符合现在程序的场景
        }
    }
}