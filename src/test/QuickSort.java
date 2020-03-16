package test;

/**
 * Created by Administrator on 2019/12/4.
 */
public class QuickSort {
    public void  quickSort(int[] array,int left,int right){
        if (left>=right)return;
        int key = array[left];
        int i=left;
        int j=right;
        while (i<j){
            while (key<=array[j]&&i<j)j--;
            while (key>=array[i]&&i<j)i++;
            if (i<j){
                int temp=array[i];
                array[i]=array[j];
                array[j]=temp;
            }
        }
        array[left]=array[i];
        array[i]=key;
        quickSort(array,left,i-1);
        quickSort(array,i+1,right);
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i]=array[j];
        array[j]=temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{2,5,1,2,6,8,2,5,1,3};
        new QuickSort().quickSort(array,0,array.length-1);
        System.out.println(array);
    }
}
