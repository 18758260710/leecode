package problems;

public class P33_SearchinRotatedSortedArray {

    //my solution divide
    public int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length - 1);
    }

    private int search(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        if (left==right)return nums[left]==target?left:-1;
        int middle = (left + right) / 2;
        int middleNum = nums[middle];
        if (target > middleNum){
            if (nums[right]>middleNum&&nums[right]>=target)return search(nums,target,middle+1,right);
            if (nums[right]>middleNum&&nums[right]<target)return search(nums,target,left,middle-1);
            if (nums[right]<middleNum)return search(nums,target,middle+1,right);
        }else if (target<middleNum){
            if (nums[right]>middleNum)return search(nums,target,left,middle-1);
            if (nums[right]<middleNum&&nums[right]<target)return search(nums,target,left,middle-1);
            if (nums[right]<middleNum&&nums[right]>=target)return search(nums,target,middle+1,right);
        }
        return middle;
    }

    public static void main(String[] args) {
        System.out.println(new P33_SearchinRotatedSortedArray().search(new int[]{4,5,6,7,8,1,2, 3}, 8));
    }

    //by flyinghx61 same as mine
    public int search2(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[start] <= nums[mid]){
                if (target < nums[mid] && target >= nums[start])
                    end = mid - 1;
                else
                    start = mid + 1;
            }

            if (nums[mid] <= nums[end]){
                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }
}
