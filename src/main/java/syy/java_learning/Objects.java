package syy.java_learning;

import java.math.BigDecimal;

public class Objects {
    public static void main(String[] args) {
        ex e = new ex();
        System.out.println(e.getClass());
        System.out.println(e.hashCode());
        System.out.println(e.toString());
        System.out.println(e.equals(e));
        //System.out.println(e.finalize());

    //== 与 equal
        Stude s1 = new Stude("Ziph", 21, "131000042424249090", 100);
        Stude s2 = new Stude("Ziph", 21, "131000042424249090", 100);
        //s1和s3地址不同内容也不同
        Stude s3 = new Stude("Tom", 21, "131000042424249090", 99);

        //开始比较
        System.out.println(s1.equals(s2));      //true
        System.out.println(s1.equals(s3));      //false
    //String 类
//        根据下标获取字符
//        public char charAt(int index)
//        判断当前字符串中是否包含str
//        public boolean contains(String str)
//        将字符串转换成数组
//        public char[] toCharArray()
//        查找str首次出现的下标，存在，则返回该下标；不存在，则返回-1
//        public int indexOf(String str)
//        public int IndexOf(String str, int fromIndex)
//        查找字符串在当前字符串中最后一次出现的下标索引
//        public int lastIndexOf(String str)
//        public int lastIndexOf(String str, int fromIndex)
//        返回字符串的长度
//        public int length()
//        去掉字符串前后的空格
//        public String trim()
//        将小写转成大写
//        public String toUpperCase()
//        判断字符串是否以str结尾
//        public boolean endWith(String str)
//        将旧字符串替换成新字符串
//        public String replace(char oldChar, char newChar)
//        根据str做拆分
//        public String[] split(String str)
//        public String[] split(String regex, int limit)
//        注意： . 、 $、 | 和 * 等转义字符，必须得加 \（转义字符），多个分隔符，可以用 | 作为连字符
    //BigDecimal类
        BigDecimal bd = new BigDecimal("1.0");
//        BigDecimal add(BigDecimal bd)
//        BigDecimal subtract(BigDecimal bd)
//        BigDecimal multiply(BigDecimal bd)
//        BigDecimal divide(BigDecimal bd)

        Stude st = new Stude("Bob",12,"123123123",99.0);
        st.equals(st);
    }

}

class ex {
    public void print(){
        System.out.println("qwe");
    }
}

class Stude {
    private String name;			//姓名
    private int age;				//年龄
    private String id;				//身份证号码
    private double score;			//分数

    public Stude(String name, int age, String id, double score) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.score = score;
    }

    public boolean equals(Object obj) {
        //1.比较两个对象的地址是否一样
        if (this == obj) {
            return true;
        }

        //2.判断对象不能为空，做的非空判断
        if (obj == null) {
            return false;
        }

        //3.确认类型，确认是同一类型，在这个案例中确认是学生对象类型
        if (this.getClass() != obj.getClass()) {
            return false;
        }

        //4.转型（转型的前提是类型肯定是Student，也就是说必须是同一类型，在上面已经做过了判断）
        //转型的目的就是把obj类型强转为学生类，即：Student类型
        Stude stu = (Stude)obj;

        //5.比较内容，假如是内容相同那么我们就认为他们是同一个人
        //也就是覆盖后的equals
        if (this.name.equals(stu.name) && this.age == stu.age && this.id.equals(stu.id) && this.score == stu.score) {
            return true;
        }
        return false;
    }
}