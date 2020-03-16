package problems;

public class RemoveDuplicatesfromSortedArray_26 {

    //my solution
    public int removeDuplicates(int[] nums) {
        if (nums.length==0)return 0;
        int num = 1;
        for (int i=1;i<nums.length;i++){
            if (nums[i]!=nums[i-1]){
                nums[num]=nums[i];
                num++;
            }
        }
        return num;
    }

    //official solution1
    //Two Pointers  same as mine
    public int removeDuplicates2(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
