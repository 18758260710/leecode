package problems;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Administrator on 2020/3/12.
 */
public class P503_NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<nums.length;i++){
            res[i]=-1;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                res[stack.pop()] = nums[i];
            stack.push(i);
        }
        for (int num : nums) {
            while (!stack.isEmpty() && nums[stack.peek()] < num)
               res[stack.pop()] = num;
        }
        return res;
    }

    //数组实现栈
    public int[] nextGreaterElements2(int[] nums) {
        int len=nums.length;
        int[] res=new int[len];
        Arrays.fill(res,-1);
        int[] stack=new int[len];
        int top=-1;
        for(int i=0;i<len*2;i++){
            int num=nums[i%len];
            while(top>-1 && num>nums[stack[top]]){
                int preIndex=stack[top--];
                res[preIndex]=num;
            }
            if(i<len){
                stack[++top]=i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new P503_NextGreaterElementII().nextGreaterElements(new int[]{1,2,1});
    }
}
