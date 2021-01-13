package problems;

public class P80_RemoveDuplicatesfromSortedArrayII {

    //my solution1
    public int removeDuplicates(int[] nums) {
        if (nums.length < 3) {
            return nums.length;
        }
        int index = 1;
        int current = nums[0];
        int currentCount = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == current) {
                currentCount++;
                if (currentCount<=2){
                    nums[index]=nums[i];
                    index++;
                }
            }else {
                currentCount = 1;
                current = nums[i];
                nums[index]=nums[i];
                index++;
            }
        }

        return index;
    }

    public static void main(String[] args) {
        System.out.println(new P80_RemoveDuplicatesfromSortedArrayII().removeDuplicates2(new int[]{0,0,0,1,1,2,3,3}));
    }
    //better 0ms
    public int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i - 2])
                nums[i++] = n;
        return i;
    }
}
