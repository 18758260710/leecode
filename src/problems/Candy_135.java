package problems;

import java.util.Arrays;

public class Candy_135 {

    //my solution1 slow
    public int candy(int[] ratings) {
        int[] candy = new int[ratings.length];
        int high = 0;
        candy[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] < ratings[i - 1]) {
                candy[i] = 1;
                if (candy[i - 1] == 1) {
                    for (int j = i - 1; j >= high; j--) {
                        if (candy[j] <= candy[j + 1]) {
                            candy[j] += 1;
                        }
                    }
                }
            } else if (ratings[i] == ratings[i - 1]) {
                candy[i] = 1;
                high = i;
            } else {
                candy[i] = candy[i - 1] + 1;
                high = i;
            }
        }
        int sum = 0;
        for (int i = 0; i < ratings.length; i++) {
            sum += candy[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        new Candy_135().candy2(new int[]{1, 3, 4, 5, 2, 1});
    }

    //my solution2 2ms 100% O(n),O(n)
    public int candy2(int[] ratings) {
        int[] candy = new int[ratings.length];
        candy[0] = 1;
        int count = 0;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] < ratings[i - 1]) {
                count++;
            } else if (ratings[i] == ratings[i - 1]) {
                if (count > 0) {
                    candy[i - count - 1] = Math
                        .max(candy[i - count - 1], count + 1);//其实存一下candy[i-count-1]也就是峰值空间也是O                }
                    while (count > 0) {
                        candy[i - count] = count;
                        count--;
                    }
                    candy[i] = 1;
                } else {
                    if (count > 0) {
                        candy[i - count - 1] = Math.max(candy[i - count - 1], count + 1);
                    }
                    while (count > 0) {
                        candy[i - count] = count;
                        count--;
                    }
                    candy[i] = candy[i - 1] + 1;
                }
            }
        }
        if (count > 0) {
            candy[ratings.length - count - 1] = Math.max(candy[ratings.length - count - 1], count + 1);
        }
        while (count > 0) {
            candy[ratings.length - count] = count;
            count--;
        }
        int sum = 0;
        for (int i = 0; i < ratings.length; i++) {
            sum += candy[i];
        }
        return sum;
    }

    //by meng789987 O(n),O(1)
    public int Candy3(int[] ratings) {
        if (ratings.length == 0) {
            return 0;
        }
        int ret = 1;
        int up = 0, down = 0, peak = 0;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i]) {
                peak = ++up;
                down = 0;
                ret += 1 + up;
            } else if (ratings[i - 1] == ratings[i]) {
                peak = up = down = 0;
                ret += 1;
            } else {
                up = 0;
                down++;
                ret += 1 + down + (peak >= down ? -1 : 0);
            }
        }

        return ret;
    }

    //official solution2 双向走一遍取大的
    public int candy4(int[] ratings) {
        int sum = 0;
        int[] left2right = new int[ratings.length];
        int[] right2left = new int[ratings.length];
        Arrays.fill(left2right, 1);
        Arrays.fill(right2left, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right2left[i] = right2left[i + 1] + 1;
            }
        }
        for (int i = 0; i < ratings.length; i++) {
            sum += Math.max(left2right[i], right2left[i]);
        }
        return sum;
    }

    //official solution3 双向走一遍取大的，只是用一个数组
    public int candy5(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        int sum = candies[ratings.length - 1];
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            sum += candies[i];
        }
        return sum;
    }
}
