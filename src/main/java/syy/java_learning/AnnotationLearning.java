package syy.java_learning;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class AnnotationLearning {
    public static void main(String[] args) throws ReflectiveOperationException {
        Annot annot = new Annot(19,9);
        annot.check(annot);

        for(Field field: Annot.class.getFields()){
            System.out.println(field.getType());
        }
    }


}

class Annot{
    @Range(min=18,max=90)
    public int age;
    @Range(min=6,max=10)
    public int name;

    Annot(int age, int name){
        this.age = age;
        this.name = name;
    }

    void check(Annot annot) throws IllegalArgumentException, ReflectiveOperationException{
        for(Field field: annot.getClass().getFields()){
            Range range = field.getAnnotation(Range.class);
            if(range!=null){
                Object ob = field.get(annot);
                if((Integer)ob< range.max()&& (Integer)ob> range.min()){
                    System.out.println("passed");
                }
            }

        }
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Range{
    int min() default 0;
    int max() default 50;
}

