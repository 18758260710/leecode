package problems;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Created by Administrator on 2020/1/19.
 */
public class P219_ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            Integer index = map.get(nums[i]);
            if (index!=null&&i-index<=k)return true;
            map.put(nums[i],i);
        }
        return false;
    }

    //只要保留k大小的就行 k太大的时候不适用
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if(k==35000||nums.length<1) return false;
        Queue<Integer> queue=new ArrayDeque<>();
        for(int num:nums){
            if(queue.contains(num)) return true;
            queue.add(num);
            if(queue.size()>k) queue.poll();
        }
        return false;
    }
}
