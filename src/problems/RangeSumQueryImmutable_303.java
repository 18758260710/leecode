package problems;

/**
 * @author wengtao
 * @date 2020/3/30
 **/
public class RangeSumQueryImmutable_303 {
//    int[] nums;
//    public RangeSumQueryImmutable_303(int[] nums) {
//        this.nums=nums;
//    }
//
//    public int sumRange(int i, int j) {
//        int sum=0;
//        while (i<=j){
//            sum+=nums[i];
//            i++;
//        }
//        return sum;
//    }

    //初始化时计算好到i的所有和  计算的时候减一下就好
    private int[] cache;
    public RangeSumQueryImmutable_303(int[] nums) {
        cache = new int[nums.length];
        if(cache.length>0){
            cache[0] = nums[0];
            for(int i=1;i<nums.length;i++){
                cache[i]=nums[i];
                cache[i] += cache[i-1];
            }
        }

    }

    public int sumRange(int i, int j) {
        if(this.cache.length==0)
            return 0;
        //so sumRange(i,j) is just sumRange(0,j)-sumRange(0,i-1)
        if(i==0){
            return cache[j];
        }else{
            return cache[j] - cache[i-1];
        }
    }
}
