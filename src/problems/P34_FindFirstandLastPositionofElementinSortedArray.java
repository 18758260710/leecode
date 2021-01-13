package problems;

public class P34_FindFirstandLastPositionofElementinSortedArray {
    public int[] searchRange(int[] nums, int target) {
        return searchHead(nums,target,0,nums.length-1);
    }

    private int[] searchHead(int[] nums, int target, int left, int right) {
        if (left>right)return new int[]{-1,-1};
        int middle = (left+right)/2;
        int middleNum = nums[middle];
        if (middleNum<target)return searchHead(nums,target,middle+1,right);
        if (middleNum>target)return searchHead(nums,target,left,middle-1);
        int head=middle,tail=middle;
        while (left<head){
            int a = (left+head-1)/2;
            if (nums[a]<target)left=a+1;
            else head = a;
        }
        while (right>tail){
            int a = (right+tail+1)/2;
            if (nums[a]>target)right=a-1;
            else tail = a;
        }
        return new int[]{head,tail};
    }

    public static void main(String[] args) {
        System.out.println(new P34_FindFirstandLastPositionofElementinSortedArray().searchRange(new int[]{5,7,7,8,8,10},6));
    }

    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }

        return lo;
    }

    //official solution
    public int[] searchRange2(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;

        return targetRange;
    }
}
