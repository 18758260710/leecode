package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NextPermutation_31 {

    //my solution
    public void nextPermutation(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int replace = nums.length-1;
        for (int i = replace; i > 0; i--) {
            list.add(nums[i]);
            if (nums[i] > nums[i - 1]) {
                replace=i-1;
                break;
            }
        }

        if (replace==nums.length-1){
            Arrays.sort(nums);
        }else {
            int re = nums[replace];
            for (int i=0;i<list.size();i++){
                if (list.get(i)>re){
                    nums[replace] = list.get(i);
                    list.set(i,re);
                    break;
                }
            }
            for (int a:list){
                nums[++replace]=a;
            }
        }
        System.out.println();
    }

    //official solution 1 same as mine  Single Pass Approach
    public void nextPermutation2(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        new NextPermutation_31().nextPermutation(new int[]{1,3,2});
    }
}
