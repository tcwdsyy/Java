package syy.java_learning;

public class ThreadPractice3 {
    static boolean pNum = true;

    public static void main(String[] args) {
//        char ch = 'A';
//        System.out.print(++ch + " ");
        Object ob = new Object();


        Thread printNum = new Thread(() -> {
            for (int i = 1; i <= 51; i += 2) {
                synchronized (ob) {
                    try {
                        while (!pNum) {
                            ob.wait();
                        }
                        System.out.print(i);
                        System.out.print(i + 1);
                        pNum = !pNum;
                        ob.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        ob.notifyAll();
                    }
                }
            }
        });

        Thread printAlphabet = new Thread(() -> {
            for (char ch = 'A'; ch <= 'Z'; ch++) {
                synchronized (ob) {
                    try {
                        while (pNum) {
                            ob.wait();
                        }
                        System.out.print(ch);
                        pNum = !pNum;
                        ob.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        ob.notifyAll();
                    }
                }
            }
        });

        printNum.start();
        printAlphabet.start();
    }
}
