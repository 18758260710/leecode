package problems;

/**
 * @author wengtao
 * @date 2020/4/1
 **/
public class RangeSumQueryMutable_307 {
    //mysolution slow
//    int[] cache;
//    int[] nums;
//    public RangeSumQueryMutable_307(int[] nums) {
//        this.nums=nums;
//        cache = new int[nums.length];
//        if(cache.length>0){
//            cache[0] = nums[0];
//            for(int i=1;i<nums.length;i++){
//                cache[i]=nums[i];
//                cache[i] += cache[i-1];
//            }
//        }
//    }
//
//    public void update(int i, int val) {
//        int cha = val-nums[i];
//        nums[i]=val;
//        for (;i<cache.length;i++){
//            cache[i]+=cha;
//        }
//    }
//
//    public int sumRange(int i, int j) {
//        if(this.cache.length==0)
//            return 0;
//        //so sumRange(i,j) is just sumRange(0,j)-sumRange(0,i-1)
//        if(i==0){
//            return cache[j];
//        }else{
//            return cache[j] - cache[i-1];
//        }
//    }

//没看懂 fast
//    int[] tree;
//    int[] nums;
//    int len;
//
//    public RangeSumQueryMutable_307(int[] nums) {
//        this.len = nums.length;
//        this.tree = new int[len + 1];
//        this.nums = nums;
//        for(int i = 1; i <= len; i++)
//            modify(i,nums[i-1]);
//    }
//
//    private int lowbit(int x){
//        return x & (-x);
//    }
//
//    private void modify(int idx, int delta) {
//        while(idx <= len){
//            tree[idx] += delta;
//            idx += lowbit(idx);
//        }
//    }
//
//    public void update(int idx,int val){
//        modify(idx+1,val - nums[idx]);
//        nums[idx] = val;
//    }
//
//    private int sum(int idx){
//        int res = 0;
//        while(idx > 0){
//            res += tree[idx];
//            idx -= lowbit(idx);
//        }
//        return res;
//    }
//
//    public int sumRange(int i, int j) {
//        return sum(j+1) - sum(i);
//    }

    public static void main(String[] args) {
        RangeSumQueryMutable_307 array = new RangeSumQueryMutable_307(new int[]{2,4,5,7,8,9});
        array.sumRange(2,4);
    }
    //official solution 线段树 一种从下向上的树 所以用数组实现的时候长度是2*n
    int[] tree;
    int n;

    public RangeSumQueryMutable_307(int[] nums) {
        if (nums.length > 0) {
            n = nums.length;
            tree = new int[n * 2];
            buildTree(nums);
        }
    }
    private void buildTree(int[] nums) {
        for (int i = n, j = 0;  i < 2 * n; i++,  j++) {
            tree[i] = nums[j];
        }
        for (int i = n - 1; i > 0; --i) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    void update(int pos, int val) {
        pos += n;
        tree[pos] = val;
        while (pos > 0) {
            int left = pos;
            int right = pos;
            if (pos % 2 == 0) {
                right = pos + 1;
            } else {
                left = pos - 1;
            }
            // parent is updated after child is updated
            tree[pos / 2] = tree[left] + tree[right];
            pos /= 2;
        }
    }

    public int sumRange(int l, int r) {
        // get leaf with value 'l'
        l += n;
        // get leaf with value 'r'
        r += n;
        int sum = 0;
        while (l <= r) {
            if ((l % 2) == 1) {
                sum += tree[l];
                l++;
            }
            if ((r % 2) == 0) {
                sum += tree[r];
                r--;
            }
            l /= 2;
            r /= 2;
        }
        return sum;
    }

}
