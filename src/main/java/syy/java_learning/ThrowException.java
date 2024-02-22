package syy.java_learning;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class ThrowException {
    public static void main(String[] args) throws UnsupportedEncodingException {
        try{
            process2();
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("6");
        // System.out.println(Arrays.toString(toGBK("nihao")));
        
    }

    static void process2(){
        try{
            process1();
        }catch(NullPointerException e){
            throw new IllegalArgumentException(e);
        }
    }

    static void process1() throws NullPointerException{
        throw new NullPointerException();
    }

    static byte[] toGBK(String s) throws UnsupportedEncodingException {
        return s.getBytes("GBK");
    }
}
