package problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/2/5.
 */
public class SummaryRanges_228 {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums.length==0)return result;
        int left=nums[0];
        int right=nums[0];
        for (int i=1;i<nums.length;i++){
            if (nums[i]==right+1){
                right++;
            }else {
                if (left==right)result.add(String.valueOf(left));
                else {
                    result.add(left+"->"+right);
                }
                left=nums[i];
                right=nums[i];
            }
        }
        if (left==right)result.add(String.valueOf(left));
        else {
            result.add(left+"->"+right);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new SummaryRanges_228().summaryRanges(new int[]{0,2,3,4,6,8,9}));
    }

    public List<String> summaryRanges2(int[] nums) {
        List<String> ans = new ArrayList<>();
        int length = nums.length;

        int l = 0;
        int r = 0;

        while (r < length) {
            while (r < length - 1 && nums[r] + 1 == nums[r + 1]) {
                ++r;
            }
            if (l != r) {
                ans.add(Integer.toString(nums[l]) + "->" + Integer.toString(nums[r]));
            } else {
                ans.add(Integer.toString(nums[l]));
            }
            ++r;
            l = r;
        }
        return ans;
    }
}
