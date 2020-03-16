package problems;

public class ContainerWithMostWater_11 {

    //my solution
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int area = (j - i) * Math.min(height[i], height[j]);
        while (i < j) {
            int temp = (j - i) * Math.min(height[i], height[j]);
            if (temp > area) {
                area = temp;
            }
            if (height[i] > height[j]) {
                j -= 1;
            } else {
                i += 1;
            }
        }

        return area;
    }

    public static void main(String[] args) {
        new ContainerWithMostWater_11().maxArea(new int[]{2, 3, 10, 5, 7, 8, 9});
    }

    //official solution
    public int maxArea3(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return maxarea;
    }
}
