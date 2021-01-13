package problems;

import java.util.Stack;

public class P42_TrappingRainWater {

    //my solution 99.76 99.95 good
    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int[] waterheight = new int[height.length];
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) {
                max = height[i];
                maxIndex = i;
            }
        }
        int leftMaxIndex = maxIndex;
        int rightMaxIndex = maxIndex;

        while (leftMaxIndex > 0) {
            int tempindex = 0;
            int tempmax = 0;
            for (int i = leftMaxIndex - 1; i >= 0; i--) {
                if (height[i] > tempmax) {
                    tempmax = height[i];
                    tempindex = i;
                }
            }
            for (int i = tempindex; i < leftMaxIndex; i++) {
                waterheight[i] = tempmax;
            }
            leftMaxIndex = tempindex;
        }
        while (rightMaxIndex < height.length - 1) {
            int tempindex = height.length - 1;
            int tempmax = 0;
            for (int i = rightMaxIndex + 1; i < height.length; i++) {
                if (height[i] > tempmax) {
                    tempmax = height[i];
                    tempindex = i;
                }
            }
            for (int i = tempindex; i > rightMaxIndex; i--) {
                waterheight[i] = tempmax;
            }
            rightMaxIndex = tempindex;
        }
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            if (waterheight[i] > height[i]) {
                result += waterheight[i] - height[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new P42_TrappingRainWater().trap4(new int[]{4, 2, 0, 3, 2, 5}));
    }

    //official solution 1   Brute force bad
    public int trap2(int[] height) {
        int ans = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                max_left = Math.max(max_left, height[j]);
            }
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                max_right = Math.max(max_right, height[j]);
            }
            ans += Math.min(max_left, max_right) - height[i];
        }
        return ans;
    }

    //official solution 2   Dynamic Programming
    public int trap3(int[] height) {
        if (height == null) {
            return 0;
        }
        int ans = 0;
        int size = height.length;
        if (size < 3) {
            return 0;
        }
        int[] left_max = new int[size];
        int[] right_max = new int[size];
        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }
        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(left_max[i], right_max[i]) - height[i];
        }
        return ans;
    }

    //official solution 3   Using stacks
    public int trap4(int[] height) {
        int ans = 0, current = 0;
        Stack<Integer> st = new Stack<>();
        while (current < height.length) {
            while (!st.empty() && height[current] > height[st.peek()]) {
                int top = st.pop();
                if (st.empty()) {
                    break;
                }
                int distance = current - st.peek() - 1;
                int bounded_height = Math.min(height[current], height[st.peek()]) - height[top];
                ans += distance * bounded_height;
            }
            st.push(current++);
        }
        return ans;
    }

    //official solution 4   Using 2 pointers
    public int trap5(int[] height) {
        {
            int left = 0, right = height.length - 1;
            int ans = 0;
            int left_max = 0, right_max = 0;
            while (left < right) {
                if (height[left] < height[right]) {
                    if (height[left] >= left_max) {
                        left_max = height[left];
                    } else {
                        ans += (left_max - height[left]);
                    }
                    ++left;
                } else {
                    if (height[right] >= right_max) {
                        right_max = height[right];
                    } else {
                        ans += (right_max - height[right]);
                    }
                    --right;
                }

            }
            return ans;
        }
    }
}