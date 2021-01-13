package problems;

/**
 * Created by Administrator on 2020/3/14.
 */
public class P287_FindtheDuplicateNumber {
    //看成一个链表 有重复就会有环 环的入口就是重复值
    public int findDuplicate(int[] nums) {
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }

    public static void main(String[] args) {
        System.out.println(new P287_FindtheDuplicateNumber().findDuplicate(new int[]{1,3,4,2,2,2}));
    }
}
