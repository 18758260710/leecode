package problems;

public class P55_JumpGame {
    //my solution 0ms
    public boolean canJump(int[] nums) {
        if (nums.length<=1)return true;
        if (nums[0] == 0) {
            return false;
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] == 0) {
                int j = i - 1;

                while (j >= 0 && nums[j] + j <= i) {
                    j--;
                }

                if (j<0)return false;
                else i=j;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new P55_JumpGame().canJump3(new int[]{3,0,2,3,2,1,0,4}));
    }
    //official solution1 Backtracking slow
    public boolean canJump2(int[] nums) {
        return canJumpFromPosition(0, nums);
    }

    public boolean canJumpFromPosition(int position, int[] nums) {
        if (position == nums.length - 1) {
            return true;
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = furthestJump; nextPosition > position; nextPosition--) {
            if (canJumpFromPosition(nextPosition, nums)) {
                return true;
            }
        }

        return false;
    }

    //official solution2 Dynamic Programming Top-down
    Index[] memo;

    public boolean canJump3(int[] nums) {
        memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;
        return canJumpFromPosition2(0, nums);
    }

    public boolean canJumpFromPosition2(int position, int[] nums) {
        if (memo[position] != Index.UNKNOWN) {
            return memo[position] == Index.GOOD ? true : false;
        }

        int furthestJump = Math.min(position + nums[position], nums.length - 1);
        for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
            if (canJumpFromPosition2(nextPosition, nums)) {
                memo[position] = Index.GOOD;
                return true;
            }
        }

        memo[position] = Index.BAD;
        return false;
    }

    //official solution3 Dynamic Programming Bottom-up
    public boolean canJump4(int[] nums) {
        Index[] memo = new Index[nums.length];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = Index.UNKNOWN;
        }
        memo[memo.length - 1] = Index.GOOD;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == Index.GOOD) {
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }

        return memo[0] == Index.GOOD;
    }

    //official solution4 Greedy 1ms
    public boolean canJump5(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    //by Xulai_Cao 更加易懂 1ms greedy
    public boolean canJump6(int[] nums) {
        int len = nums.length;
        int max = 0;
        for(int i=0; i<=max; i++){
            max = Math.max(max, i+nums[i]);
            if(max >= len-1)  return true;
        }
        return false;
    }
}
enum Index {
    GOOD, BAD, UNKNOWN
}
