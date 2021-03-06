package problems;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class P349_IntersectionofTwoArrays {
    //8ms
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums2).distinct().filter(set::contains).toArray();
    }

    //用数组代替hash
    public int[] intersection2(int[] nums1, int[] nums2) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : nums1) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        boolean[] arr = new boolean[max - min + 1];
        for (int num : nums1) {
            arr[num - min] = true;
        }
        // 判断数组 nums2 中的数是否在数组 nums1 中存在，
        // 如果存在保存在数组 tmp 中
        int[] tmp = new int[max - min + 1];
        int idx = 0;
        for (int num : nums2) {
            if (num >= min && num <= max && arr[num - min]) {
                tmp[idx++] = num;
                arr[num- min] = false;//保证每个值只存储一次
            }
        }

        // 修剪数组，返回结果
        int[] ret = new int[idx];
        for (int i = 0; i < idx; i++) {
            ret[i] = tmp[i];
        }
        return ret;
    }
}
