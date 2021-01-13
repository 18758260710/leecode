package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P84_LargestRectangleinHistogram {
    int count=0;
    //my solution1 slow
    public int largestRectangleArea(int[] heights) {
        int a= explore(0,heights.length-1,heights);
        System.out.println(count);
        return a;
    }

    private int explore(int left, int right, int[] heights) {
        count++;
        if (left>right)return 0;
        int min = heights[left];
        List<Integer> indexs = new ArrayList<>();
        indexs.add(left);
        for (int i=left+1;i<=right;i++){
            if (min>heights[i]){
                min = heights[i];
                indexs.clear();
                indexs.add(i);
            }else if (min==heights[i]){
                indexs.add(i);
            }
        }
        int area = (right-left+1)*min;
        for (Integer index:indexs){
            area = Math.max(explore(left,index-1,heights),area);
            left = index+1;
        }
        area = Math.max(explore(indexs.get(indexs.size()-1)+1,right,heights),area);
        return area;
    }

    public static void main(String[] args) {
        System.out.println(new P84_LargestRectangleinHistogram().largestRectangleArea4(new int[]{2,1,5,6,2,3}));
    }

    //my solution2 slow
    public int largestRectangleArea2(int[] heights) {
        int area=0;
        int left;
        int right;
        for (int i=0;i<heights.length;i++){
            left=i;
            right=i;
            while (left>=0&&heights[left]>=heights[i]){
                left--;
            }
            while (right<heights.length&&heights[right]>=heights[i]){
                right++;
            }
            area = Math.max((right-left-1)*heights[i],area);
        }
        return area;
    }

    //0ms 思想上和 mysolution1 相同 单调区间不循环了
    public int largestRectangleArea3(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int a= sub(heights, 0, heights.length - 1);
        System.out.println(count);
        return a;
    }

    private int sub(int[] heights, int start, int end) {
        count++;
        if (start > end) return 0;
        if (start == end) return heights[start];

        int max = 0;
        boolean asc = true;
        boolean desc = true;
        int min = heights[start];
        for (int i = start + 1; i <= end; i++) {
            if (heights[i] < min) min = heights[i];
            if (heights[i] < heights[i-1]) asc = false;
            if (heights[i] > heights[i-1]) desc = false;
        }

        if (asc) {
            max = heights[end];
            for(int i = end-1; i >= start; i--) {
                int sum = (end - i + 1) * heights[i];
                if (sum > max) max = sum;
            }
            return max;
        }
        if (desc) {
            max = heights[start];
            for(int i = start+1; i <= end; i++) {
                int sum = (i - start + 1) * heights[i];
                if (sum > max) max = sum;
            }
            return max;
        }

        int substart = start;
        int submax = 0;
        max = min * (end - start + 1);
        for (int i = start; i <= end; i++) {
            if (heights[i] == min) {
                submax = sub(heights, substart, i - 1);
                max = submax > max ? submax : max;
                substart = i + 1;
            }
        }
        submax = sub(heights, substart, end);
        max = submax > max ? submax : max;

        return max;
    }

    //用栈 单调递增时入栈 否则出栈到比当前值小为止
    public int largestRectangleArea4(int[] heights){
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for(int i = 0; i <= heights.length; i++){
            int h = (i == heights.length) ? 0 : heights[i];
            while(!stack.isEmpty() && heights[stack.peek()] > h){
                int index = stack.pop();
                int leftBound = -1;
                if(!stack.isEmpty()){
                    leftBound =  stack.peek();
                }
                max = Math.max(max, heights[index] * (i - leftBound - 1));
            }
            stack.push(i);
        }
        return max;
    }
}
