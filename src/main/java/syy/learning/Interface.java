package syy.learning;

import java.util.Arrays;

public class Interface {
    public static void main(String[] args) {
        Student[] students = new Student[] {
                new Student("Ziph", 99),
                new Student("Marry", 66),
                new Student("Tom", 88)
        };

        Integer[] arr = new Integer[]{9,8,7,6,5,4,3,2,1};
        int[] arri = new int[]{9,8,7,6,5,4,3,2,1};
        Tools.sort(students);
        Tools.sort(arr);
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].name + "  成绩：" + students[i].score);
        }
        for(int num:arr){
            System.out.println(num);
        }
    }
    public interface Sports {
        public void setHomeTeam(String name);
        public void setVisitingTeam(String name);
    }

    // 文件名: Football.java
    public interface Football extends Sports { //可以extend多个interface
        public void homeTeamScored(int points);
        public void visitingTeamScored(int points);
        public void endOfQuarter(int quarter);
    }
}

class Student implements Comparable<Student> {
    String name;
    int score;
    public Student() {
    }

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Student s) {
        if(this.score>s.score){
            return 1;
        }else if(this.score<s.score){
            return -1;
        }
        return 0;
    }
}

class Tools {
    public static void sort(Object[] type) { //type T or Object?
        for(int i = 0; i<type.length; i++){
            for(int j = 0; j< type.length-1; j++){
                Comparable cur = (Comparable) type[j];
                int n = cur.compareTo(type[j+1]);
                if(n>0){
                    Object temp = type[j];
                    type[j] = type[j+1];
                    type[j+1] = temp;
                }
            }
        }
    }
}




