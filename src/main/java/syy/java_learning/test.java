package syy.java_learning;

public class test {
    public static void main(String[] args) {
        //int num = 0;
        Num num = new Num(0);

        Function1 f1 = new Function1(num);
        Function2 f2 = new Function2(num);
        f1.plus1();
        f1.plus1();
        f1.plus1();
        f2.minus1();
        System.out.println(f1.getNum());
        System.out.println(f2.getNum());
        System.out.println(num.num);
    }
}

class Num {

    public int num;

    public Num(int num) {
        this.num = num;
    }

    public void plus1(){
        num++;
    }

    public void minus1(){
        num--;
    }

}
class Function1 {
    Num num;

    public Function1(Num num) {
        this.num = num;
    }

    public void plus1(){
        num.plus1();
    }

    public int getNum() {
        return num.num;
    }
}

class Function2 {
    Num num;

    public Function2(Num num) {
        this.num = num;
    }

    public void minus1(){
        num.minus1();
    }

    public int getNum() {
        return num.num;
    }
}
