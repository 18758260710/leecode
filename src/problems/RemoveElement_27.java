package problems;

public class RemoveElement_27 {
    //my solution
    public int removeElement(int[] nums, int val) {
        int num = 0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=val){
                nums[num]=nums[i];
                num++;
            }
        }
        return num;
    }

    //official solution1
    //same as mine
    //Two Pointers
    public int removeElement2(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }


    //official solution2
    // Two Pointers - when elements to remove are rare
    //感觉会改变顺序
    public int removeElement3(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                // reduce array size by one
                n--;
            } else {
                i++;
            }
        }
        return n;
    }
}
