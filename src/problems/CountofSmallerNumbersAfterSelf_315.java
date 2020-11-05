package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wengtao
 * @date 2020/4/9
 **/
public class CountofSmallerNumbersAfterSelf_315 {
    //my solution1 41ms slow
    //从小到大排好序的
    List<Integer> array= new LinkedList<>();

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result=  new LinkedList<>();

        for (int i=nums.length-1;i>=0;i--){
            int current = nums[i];
            int index = getIndex(current);
            result.add(0,index);
            array.add(index, current);
        }
        return result;
    }

    public int getIndex(int current) {
        if (array.size()==0){
            return 0;
        }
        if (array.get(array.size()-1) < current){
            return array.size();
        }
        int left = 0;
        int right = array.size()-1;

        while (left<right){
            int middle = left + (right - left) / 2;
            if (array.get(middle) < current){
                left = middle+1;
            }else {
                right = middle;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        System.out.println(new CountofSmallerNumbersAfterSelf_315().countSmaller2(new int[]{5,2,6,1}));
    }

    //2ms BITree 树状数组
    public  List<Integer> countSmaller2(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        //todo 此处可以对nums做离散化，减小
        int min = Integer.MAX_VALUE; // nums数组最小值BITree的大小
        for (int value : nums) {
            if (value < min) {
                min = value;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] - min + 1;
        }

        int max = Integer.MIN_VALUE;
        for (int value : nums) {
            if (value > max) {
                max = value;
            }
        }

        int[] BITree = new int[max + 1];
        BITree[0] = 0;
        int[] countArr = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int count = getSum(nums[i] - 1, BITree);
            countArr[i] = count;
            update(nums[i], BITree);
        }
        List<Integer> result = new ArrayList<>();
        for (int value : countArr) {
            result.add(value);
        }
        return result;
    }
    //获取和
    public static int getSum(int value, int[] BITree) { // 获得a[i]从1，value的和
        int sum = 0;
        while (value > 0) {
            sum += BITree[value];
            value -= (value & -value);
        }
        return sum;
    }
    //单点更新值
    public static void update(int value, int[] BITree) {
        while (value <= BITree.length - 1) {
            BITree[value] += 1;
            value += (value & -value);
        }
    }
}
