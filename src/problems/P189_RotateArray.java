package problems;

import java.util.Arrays;

public class P189_RotateArray {
    //my solution1 space O(k)
    public void rotate(int[] nums, int k) {
        int length= nums.length;
        k = k%length;
        if (k==0)return;
        int[] temp;
        temp = Arrays.copyOfRange(nums,length-k,length);

        System.arraycopy(nums, 0, nums, k, length - k - 1 + 1);
        System.arraycopy(temp, 0, nums, 0, k);
    }

    //my solution2 space O(1)   official solution1
    public void rotate2(int[] nums, int k) {
        while (k>0){
            rightMove(nums);
        }
    }

    private void rightMove(int[] nums) {
        int last = nums[nums.length-1];
        System.arraycopy(nums, 0, nums, 1, nums.length - 1);
        nums[0]=last;
    }

    //my solution3 space O(1) official solution3
    public void rotate4(int[] nums, int k) {
        int length= nums.length;
        k = k%length;
        if (k==0)return;
        int count=0;
        for(int start=0;count<length;start++){
            int index = start;
            int temp1;
            int temp2 = nums[index];
            do {
                temp1 = nums[(index+k)%length];
                nums[(index+k)%length] = temp2;
                temp2=temp1;
                index = (index+k)%length;
                count++;
            }while (index!=start);
        }

    }

    //official solution2  space O(n)
    public void rotate3(int[] nums, int k) {
        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }
        System.arraycopy(a, 0, nums, 0, nums.length);
    }

    //official solution4  space O(1)
    public void rotate5(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4,5,6};
        new P189_RotateArray().rotate4(a,2);
        System.out.println(1);
    }
}
