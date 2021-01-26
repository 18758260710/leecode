package problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author wengtao
 * @date 2021/1/13
 **/
public class P327_CountofRangeSum {
    //O n2  slow
    public int countRangeSum(int[] nums, int lower, int upper) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            long sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (lower <= sum && sum <= upper) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        new P327_CountofRangeSum().countRangeSum3(new int[]{-2, 5, -1, 4, 9, -7, 1}, -2, 2);
    }

    //https://leetcode-cn.com/problems/count-of-range-sum/solution/qian-zhui-he-shu-zu-gui-bing-pai-xu-si-lu-by-sunfl/ 前缀和归并排序为什么不会影响原区间和顺序
    public int countRangeSum2(int[] nums, int lower, int upper) {
        long s = 0;
        long[] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            sum[i + 1] = s;
        }
        return countRangeSumRecursive(sum, lower, upper, 0, sum.length - 1);
    }

    public int countRangeSumRecursive(long[] sum, int lower, int upper, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            int mid = (left + right) / 2;
            int n1 = countRangeSumRecursive(sum, lower, upper, left, mid);
            int n2 = countRangeSumRecursive(sum, lower, upper, mid + 1, right);
            int ret = n1 + n2;

            // 首先统计下标对的数量
            int i = left;
            int l = mid + 1;
            int r = mid + 1;
            while (i <= mid) {
                while (l <= right && sum[l] - sum[i] < lower) {
                    l++;
                }
                while (r <= right && sum[r] - sum[i] <= upper) {
                    r++;
                }
                ret += r - l;
                i++;
            }

            // 随后合并两个排序数组
            int[] sorted = new int[right - left + 1];
            int p1 = left, p2 = mid + 1;
            int p = 0;
            while (p1 <= mid || p2 <= right) {
                if (p1 > mid) {
                    sorted[p++] = (int) sum[p2++];
                } else if (p2 > right) {
                    sorted[p++] = (int) sum[p1++];
                } else {
                    if (sum[p1] < sum[p2]) {
                        sorted[p++] = (int) sum[p1++];
                    } else {
                        sorted[p++] = (int) sum[p2++];
                    }
                }
            }
            for (int j = 0; j < sorted.length; j++) {
                sum[left + j] = sorted[j];
            }
            return ret;
        }
    }

    //线段树 通过hash映射预先建好树
    public int countRangeSum3(int[] nums, int lower, int upper) {
        long sum = 0;
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            preSum[i + 1] = sum;
        }

        Set<Long> allNumbers = new TreeSet<>();
        for (long x : preSum) {
            allNumbers.add(x);
            allNumbers.add(x - lower);
            allNumbers.add(x - upper);
        }
        // 利用哈希表进行离散化
        Map<Long, Integer> values = new HashMap<>();
        int idx = 0;
        for (long x : allNumbers) {
            values.put(x, idx);
            idx++;
        }

        SegNode root = build(0, values.size() - 1);
        int ret = 0;
        for (long x : preSum) {
            int left = values.get(x - upper), right = values.get(x - lower);
            ret += count(root, left, right);
            insert(root, values.get(x));
        }
        return ret;
    }

    public SegNode build(int left, int right) {
        SegNode node = new SegNode(left, right);
        if (left == right) {
            return node;
        }
        int mid = (left + right) / 2;
        node.lchild = build(left, mid);
        node.rchild = build(mid + 1, right);
        return node;
    }

    public int count(SegNode root, int left, int right) {
        if (left > root.hi || right < root.lo) {
            return 0;
        }
        if (left <= root.lo && root.hi <= right) {
            return root.add;
        }
        return count(root.lchild, left, right) + count(root.rchild, left, right);
    }

    public void insert(SegNode root, int val) {
        root.add++;
        if (root.lo == root.hi) {
            return;
        }
        int mid = (root.lo + root.hi) / 2;
        if (val <= mid) {
            insert(root.lchild, val);
        } else {
            insert(root.rchild, val);
        }
    }


    class SegNode {
        int lo, hi, add;
        SegNode lchild, rchild;

        public SegNode(int left, int right) {
            lo = left;
            hi = right;
            add = 0;
            lchild = null;
            rchild = null;
        }
    }

    //线段树 在添加节点的过程中构建树
    class Solution {
        public int countRangeSum(int[] nums, int lower, int upper) {
            long sum = 0;
            long[] preSum = new long[nums.length + 1];
            for (int i = 0; i < nums.length; ++i) {
                sum += nums[i];
                preSum[i + 1] = sum;
            }

            long lbound = Long.MAX_VALUE, rbound = Long.MIN_VALUE;
            for (long x : preSum) {
                lbound = Math.min(Math.min(lbound, x), Math.min(x - lower, x - upper));
                rbound = Math.max(Math.max(rbound, x), Math.max(x - lower, x - upper));
            }

            SegNode2 root = new SegNode2(lbound, rbound);
            int ret = 0;
            for (long x : preSum) {
                ret += count(root, x - upper, x - lower);
                insert(root, x);
            }
            return ret;
        }

        public int count(SegNode2 root, long left, long right) {
            if (root == null) {
                return 0;
            }
            if (left > root.hi || right < root.lo) {
                return 0;
            }
            if (left <= root.lo && root.hi <= right) {
                return root.add;
            }
            return count(root.lchild, left, right) + count(root.rchild, left, right);
        }

        public void insert(SegNode2 root, long val) {
            root.add++;
            if (root.lo == root.hi) {
                return;
            }
            long mid = (root.lo + root.hi) >> 1;
            if (val <= mid) {
                if (root.lchild == null) {
                    root.lchild = new SegNode2(root.lo, mid);
                }
                insert(root.lchild, val);
            } else {
                if (root.rchild == null) {
                    root.rchild = new SegNode2(mid + 1, root.hi);
                }
                insert(root.rchild, val);
            }
        }
    }

    class SegNode2 {
        long lo, hi;
        int add;
        SegNode2 lchild, rchild;

        public SegNode2(long left, long right) {
            lo = left;
            hi = right;
            add = 0;
            lchild = null;
            rchild = null;
        }
    }

    //树状数组
    public int countRangeSum5(int[] nums, int lower, int upper) {
        long sum = 0;
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            preSum[i + 1] = sum;
        }

        Set<Long> allNumbers = new TreeSet<Long>();
        for (long x : preSum) {
            allNumbers.add(x);
            allNumbers.add(x - lower);
            allNumbers.add(x - upper);
        }
        // 利用哈希表进行离散化
        Map<Long, Integer> values = new HashMap<Long, Integer>();
        int idx = 0;
        for (long x : allNumbers) {
            values.put(x, idx);
            idx++;
        }

        int ret = 0;
        BIT bit = new BIT(values.size());
        for (int i = 0; i < preSum.length; i++) {
            int left = values.get(preSum[i] - upper), right = values.get(preSum[i] - lower);
            ret += bit.query(right + 1) - bit.query(left);
            bit.update(values.get(preSum[i]) + 1, 1);
        }
        return ret;
    }
}

class BIT {
    int[] tree;
    int n;

    public BIT(int n) {
        this.n = n;
        this.tree = new int[n + 1];
    }

    public static int lowbit(int x) {
        return x & (-x);
    }

    public void update(int x, int d) {
        while (x <= n) {
            tree[x] += d;
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int ans = 0;
        while (x != 0) {
            ans += tree[x];
            x -= lowbit(x);
        }
        return ans;
    }
}
