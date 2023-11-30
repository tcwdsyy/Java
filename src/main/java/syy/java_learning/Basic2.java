package syy.java_learning;
// some basic array implementation and modification
public class Basic2 {
    public static void main(String[] args){
        int[] a = new int[2];
        a[0] = 1;
        a[1] = 2;
//        for (int i = 0; i < a.length; i++) {//数组名.length可动态获得数组长度
//            System.out.println(a[i]);//使用循环变量“i”充当下标，逐一访问数组中的每个元素
//        }
        //数组的复制方式
        //循环将原数组中所有元素逐一赋值给新数组

        int[] arr1 = new int [] {1,2,3,4,5};
        int[] arr2 = new int[10];

        //System.arraycopy(原数组, 原数组起始, 新数组, 新数组长度, 长度);
        System.arraycopy(arr1,0,arr2,5,5);

        //java.util.Arrays.copyOf(原数组, 新长度); //返回带有原值的新数组
        int[]arr3 = java.util.Arrays.copyOf(arr1,10);

        print(arr1);
        print(arr2);
        print(arr3);
        print(2);

    }
    public static void print (int... arr){
        for(int num:arr){
            System.out.println(num);
        }
        System.out.println();
    }
}
