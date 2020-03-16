package problems;

public class SearchinRotatedSortedArrayII_81 {
    //my solution1 0ms
    public boolean search(int[] nums, int target) {
        return explore(0, nums.length - 1, nums, target);
    }

    private boolean explore(int left, int right, int[] nums, int target) {
        if (left > right) {
            return false;
        }
        int middle = (left + right) / 2;
        if (nums[middle] == target) {
            return true;
        }
        boolean leftWay = false;
        boolean rightWay = false;
        //分割点在middle和left之间
        if (nums[left] >= nums[middle] && nums[middle] <= nums[right] && nums[left] >= nums[right]) {
            if (nums[middle] > target){
                leftWay = true;
            }else {
                if (target>nums[right])leftWay=true;
                else rightWay=true;
            }
        }

        //分割点在middle和right之间
        if (nums[left] <= nums[middle] && nums[middle] >= nums[right] && nums[left] >= nums[right]) {
            if (nums[middle] < target){
                rightWay = true;
            }else {
                if (target<nums[left])rightWay=true;
                else leftWay=true;
            }
        }

        //无分割点
        if (nums[left] <= nums[middle] && nums[middle] <= nums[right]) {
            if (nums[middle] < target){
                rightWay = true;
            }else {
                leftWay=true;
            }
        }

        return (leftWay&&explore(left,middle-1,nums,target))|| (rightWay&&explore(middle+1,right,nums,target));
    }

    public static void main(String[] args) {
        System.out.println(new SearchinRotatedSortedArrayII_81().search(new int[]{1, 1, 3, 1}, 1));
    }
}
