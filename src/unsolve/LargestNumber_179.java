package unsolve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LargestNumber_179 {
    //my solution 没做出来  思维被桶排序禁锢住了
    public String largestNumber(int[] nums) {
        return bucketSort(IntStream.of(nums).boxed().collect(Collectors.toList())
            ,0);
    }

    private String bucketSort(List<Integer> nums,int index) {
        Map<Integer,List<Integer>> buckets = new HashMap<>();
        Integer current = 10;
        String tail = "";
        for (Integer num:nums){
            if (String.valueOf(num).length()>index) {
                Integer wei = String.valueOf(num).charAt(index)-48;
                List<Integer> temp = buckets.getOrDefault(wei,new ArrayList<>());
                temp.add(num);
                buckets.put(wei,temp);
            }else {
                tail+=num;
                current = tail.charAt(0)-48;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer i = 9;i>=0;i--){
            if (buckets.containsKey(i)){
                if (i.equals(current)){
                    buckets.get(i).add(Integer.valueOf(tail));
                }
                if (buckets.get(i).size()>1){
                    stringBuilder.append(bucketSort(buckets.get(i),index+1));
                }else {
                    stringBuilder.append(buckets.get(i).get(0));
                }
            } else if (i.equals(current)){
                stringBuilder.append(tail);
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
//        System.out.println(new LargestNumber_179().largestNumber(new int[]{3,33,332,334,5,9}));
        System.out.println(new LargestNumber_179().largestNumber3(new int[]{121,12}));
    }

    private class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        }
    }

    //official solution 直接排序 4ms
    public String largestNumber2(int[] nums) {
        // Get input integers as strings.
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }

        // Sort strings according to custom comparator.
        Arrays.sort(asStrs, new LargerNumberComparator());

        // If, after being sorted, the largest number is `0`, the entire number
        // is zero.
        if (asStrs[0].equals("0")) {
            return "0";
        }

        // Build largest number from sorted array.
        StringBuilder largestNumberStr = new StringBuilder();
        for (String numAsStr : asStrs) {
            largestNumberStr.append(numAsStr);
        }

        return largestNumberStr.toString();
    }

    //1ms 自写快排，用int计算来比较大小
    public String largestNumber3(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        if (nums[0] == 0) return "0";
        StringBuilder sb = new StringBuilder();
        for (int i : nums) {
            sb.append(i);
        }
        return sb.toString();
    }
    public void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int mid = partition(nums, left, right);
            quickSort(nums, left, mid - 1);
            quickSort(nums, mid + 1, right);
        }
    }
    public int partition(int[] nums, int left, int right) {
        int p = nums[left];
        while (left < right) {
            while (left < right && compare(p,nums[right]) <= 0) {
                --right;
            }
            if (left < right) {
                nums[left] = nums[right];

            }
            while (left < right && compare(nums[left],p) <= 0) {
                ++left;
            }
            if (left < right) {
                nums[right] = nums[left];
            }
        }
        nums[left] = p;
        return left;
    }
    public int compare(int left, int right) {
        if (left == 0 || right == 0) {
            return right-left;
        }
        long x = left;
        for (int i = right; i > 0 ; i /= 10) {
            x *= 10;
        }
        x += right;
        long y = right;
        for (int i = left; i > 0; i /= 10) {
            y *= 10;
        }
        y += left;
        return (int) (y-x);
    }
}
