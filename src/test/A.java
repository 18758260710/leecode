package test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2019/12/4.
 */
public class A { //父类
    public static String staticStr = "A静态属性";
    public String nonStaticStr = "A非静态属性";
    public static void staticMethod(){
        System.out.println("A静态方法");
    }


    public void nonStaticMethod(){
        Comparator a = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
        System.out.println("A非静态方法");
    }

    public static void main(String[] args) {
        new A().getResult(new int[]{1,5,2,2,3});
    }
    public int[] getResult(int[] array){
        int max=0;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<array.length;i++){
            if(array[i]>max)max=array[i];
            if(array[i]<min)min=array[i];
        }
        boolean[] bitmap = new boolean[max-min+1];
        boolean[] duplicate = new boolean[max-min+1];
        for(int i=0;i<array.length;i++){
            if(bitmap[array[i]-min]){
                duplicate[array[i]-min]=true;
            }else{
                bitmap[array[i]-min]=true;
            }
        }
        List<Integer> result=new ArrayList<>();
        for(int i=0;i<max-min;i++){
            if(!bitmap[i]||duplicate[i]){
                result.add(i+min);
            }
        }
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }
}
