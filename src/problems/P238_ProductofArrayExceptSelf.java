package problems;

/**
 * Created by Administrator on 2020/2/9.
 */
public class P238_ProductofArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] left = new int[length];
        int[] right = new int[length];
        int[] result = new int[length];
        left[0]=1;
        right[length-1]=1;
        for (int i=1;i<length;i++){
            left[i]=left[i-1]*nums[i-1];
            right[length-1-i]=right[length-i]*nums[length-i];
        }
        for (int i=0;i<length;i++){
            result[i]=left[i]*right[i];
        }
        return result;
    }

    //少用一些空间 O（1）空间复杂度
    public int[] productExceptSelf2(int[] nums) {
        int[] L=new int[nums.length];
        L[0]=1;
        for(int i=1;i<nums.length;i++){
            L[i]=nums[i-1]*L[i-1];
        }
        int R=1;
        for(int i=nums.length-1;i>=0;i--){
            if(i<nums.length-1)
                R=nums[i+1]*R;
            L[i]*=R;
        }
        return L;
    }
}
