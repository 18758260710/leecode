package problems;

public class TwoSumIIInputarrayissorted_167 {
    //my solution1 1ms
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[]{0,numbers.length-1};
        while (result[0]<result[1]){
            int temp = numbers[result[0]]+numbers[result[1]];
            if (temp==target){
                result[1]++;
                result[0]++;
                return result;
            }
            if (temp>target){
                result[1]--;
            }
            if (temp<target){
                result[0]++;
            }
        }
        return result;
    }

    public int[] twoSum2(int[] numbers, int target) {
        int[] result = new int[2];
        int start = 0;
        int end = numbers.length - 1;
        while(start < end) {
            //不知道为啥不用tmep变量会快一些
            if(numbers[start] + numbers[end] > target) {
                end--;
            }else if(numbers[start] + numbers[end] < target) {
                start++;
            }else {
                result[0] = start + 1;
                result[1] = end + 1;
                break;
            }
        }
        return result;
    }
}
