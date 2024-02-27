package syy.java_learning;

import java.text.NumberFormat;
import java.util.Locale;

public class TimeLearning {
    public static void main(String[] args) {
        int n = 12349;
        System.out.println(Integer.toHexString(n));
        System.out.println(NumberFormat.getCurrencyInstance(Locale.CHINA).format(n));

        // old ver

        // new ver
    }
}
