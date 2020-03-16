package problems;

public class SearchInsertPosition_35 {

    //my solution
    public int searchInsert(int[] nums, int target) {
        return searchInsert(nums, target, 0, nums.length - 1);
    }

    private int searchInsert(int[] nums, int target, int left, int right) {
        if (nums[left]>=target)return left;
        if (nums[right]<target)return right+1;
        if (right - left == 1) {
            return right;
        }
        int middle = (right + left) / 2;
        if (nums[middle] > target) {
            return searchInsert(nums, target, left, middle);
        } else {
            return searchInsert(nums, target, middle, right);
        }
    }

    public static void main(String[] args) {
        System.out.println(new SearchInsertPosition_35().searchInsert(new int[]{1, 3, 5, 6}, 2));
    }

    //by AmmsA
    public int searchInsert2(int[] A, int target) {
        int low = 0, high = A.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            if(A[mid] == target) return mid;
            else if(A[mid] > target) high = mid-1;
            else low = mid+1;
        }
        return low;
    }
}
