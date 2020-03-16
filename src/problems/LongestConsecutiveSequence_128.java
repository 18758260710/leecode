package problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestConsecutiveSequence_128 {
    //my solution1 8ms  O(n)
    public int longestConsecutive(int[] nums) {
        if (nums==null||nums.length==0)return 0;
        Set<Integer> numSet = new HashSet<>();
        for (int num:nums){
            numSet.add(num);
        }
        Map<Integer,Integer> left = new HashMap<>();
        Map<Integer,Integer> right = new HashMap<>();
        for (int num:numSet){
            Integer pre = right.get(num-1);
            Integer next = left.get(num+1);
            if (pre!=null&&next!=null){
                left.put(pre,next);
                right.put(next,pre);
                right.remove(num-1);
                left.remove(num+1);
            }else if (pre!=null){
                left.put(pre,num);
                right.put(num,pre);
                right.remove(num-1);
            }else if (next!=null){
                right.put(next,num);
                left.put(num,next);
                left.remove(num+1);
            }else {
                left.put(num,num);
                right.put(num,num);
            }
        }
        int result = 0;
        for (int key:left.keySet()){
            result = Math.max(result,left.get(key)-key+1);
        }
        return result;
    }

    public static void main(String[] args) {
        new LongestConsecutiveSequence_128().longestConsecutive(new int[]{-7,-1,3,-9,-4,7,-3,2,4,9,4,-9,8,-7,5,-1,-7});
    }

    //official solution2 直接排序 4ms O(nlogn)
    public int longestConsecutive2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int longestStreak = 1;
        int currentStreak = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                if (nums[i] == nums[i-1]+1) {
                    currentStreak += 1;
                }
                else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }

        return Math.max(longestStreak, currentStreak);
    }
    //official solution3 Set 代替数组 直接查找 5ms O(n)
    public int longestConsecutive3(int[] nums) {
        Set<Integer> num_set = new HashSet<>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum+1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
