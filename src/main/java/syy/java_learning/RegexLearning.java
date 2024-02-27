package syy.java_learning;

public class RegexLearning {
    public static void main(String[] args) {
        String number = "18378590501";
        System.out.println(number.matches("\\d{11}"));
        String re2 = "a\\&c"; // 对应的正则是a\&c
        System.out.println("a&c".matches(re2));
        System.out.println("a-c".matches(re2));
        System.out.println("a&&c".matches(re2));
    }
}
