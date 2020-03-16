package problems;

public class FindPeakElement_162 {
    //my solution1 0ms
    public int findPeakElement(int[] nums) {
        for (int i=0;i<nums.length-1;i++){
            if (nums[i]>nums[i+1])return i;
        }
        for (int i=nums.length-1;i>0;i--){
            if (nums[i]>nums[i-1])return i;
        }
        return 0;
    }

    //official solution1
    public int findPeakElement2(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return i;
        }
        return nums.length - 1;
    }

    //official solution2 二分
    public int findPeakElement3(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }
    public int search(int[] nums, int l, int r) {
        if (l == r)
            return l;
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid + 1])
            return search(nums, l, mid);
        return search(nums, mid + 1, r);
    }

    //official solution3 二分
    public int findPeakElement4(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(new FindPeakElement_162().findPeakElement3(new int[]{1,200,3,4,5,6,7,8,9,10}));
    }
}
