package problems;

public class P154_FindMinimuminRotatedSortedArrayII {
    //my solution1 0ms
    public int findMin(int[] nums) {
        return helper(nums,0,nums.length-1);
    }

    private int helper(int[] nums, int left, int right) {
        if (left==right)return nums[left];
        if (right-left==1)return Math.min(nums[right],nums[left]);
        int middle = (left+right)/2;
        if (nums[middle]>nums[right]){
            return helper(nums,middle+1,right);
        }
        if (nums[middle]<nums[left]){
            return helper(nums,left+1,middle);
        }
        int a = helper(nums,middle+1,right);
        if (a<nums[middle])return a;
        int b = helper(nums,left,middle-1);
        return b<nums[middle]?b:nums[middle];
    }

    public static void main(String[] args) {
        System.out.println(new P154_FindMinimuminRotatedSortedArrayII().findMin(new int[]{3,3,1,3}));
    }

    public int findMin2(int[] nums) {
        int start = 0, end = nums.length - 1;
        while(end > start){
            int mid = (end + start) / 2;
            if(nums[mid] > nums[end])
                start = mid + 1;
            else if(nums[mid] == nums[end])
                end--;
            else
                end = mid;
        }

        return nums[start];
    }
}
