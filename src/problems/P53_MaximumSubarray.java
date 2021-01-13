package problems;

public class P53_MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if (nums.length==0)return 0;
        int[] result = new int[nums.length];
        result[0]=nums[0]>0?nums[0]:0;
        int count = result[0];
        for (int i=1;i<nums.length;i++){
            result[i]=Math.max(result[i-1]+nums[i],0);
            count = Math.max(count,result[i]);
        }
        if (count==0){
            count = Integer.MIN_VALUE;
            for (int i=0;i<nums.length;i++){
                count = Math.max(nums[i],count);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        new P53_MaximumSubarray().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
    }
    //better
    public int maxSubArray2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int max = nums[0];
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i] + sum) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            max = max > sum ? max : sum;
        }


        return max;
    }
}
