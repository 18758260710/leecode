package problems;

import java.util.*;

/**
 * Created by Administrator on 2020/1/13.
 */
public class CourseScheduleII_210 {
    //5ms
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        int index = 0;
        List<Integer> [] preqs = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            preqs[i] = new LinkedList<>();
        }
        int[] temp = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            temp[prerequisites[i][0]]++;
            preqs[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        Queue<Integer> zeros = new LinkedList<>();
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 0) {
                zeros.add(i);
            }
        }
        while (!zeros.isEmpty()) {
            int zero = zeros.poll();
            result[index] = zero;
            index++;
            List<Integer> set = preqs[zero];
            for (int cu : set) {
                temp[cu]--;
                if (temp[cu]==0)zeros.add(cu);
            }
        }
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] > 0) {
                return new int[0];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        new CourseScheduleII_210().findOrder2(2,new int[][]{{1,0}});
    }

    //3ms
    int[] result;
    int index=0;
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        result = new int[numCourses];
        int [] courseStates = new int[numCourses];
        List<Integer> [] preqs = new List[numCourses];

        int i;
        for (i = 0; i < numCourses; i++) {
            courseStates[i] = 0;
            preqs[i] = new LinkedList<>();
        }

        for (int[] pair : prerequisites) {
            preqs[pair[0]].add(pair[1]);
        }

        for (i = 0; i < numCourses; i++) {
            if (courseStates[i] == 0) {
                if (!dfs(preqs, courseStates, i)) {
                    return new int[0];
                }
            }
        }
        return result;
    }

    boolean dfs(List<Integer> [] preqs, int [] courseStates, int i) {
        courseStates[i] = 1;
        for (Integer j: preqs[i]) {
            if (courseStates[j] == 0) {
                if (!dfs(preqs, courseStates, j)) {
                    return false;
                }
            }
            else if (courseStates[j] == 1) {
                return false;
            }
        }
        result[index]=i;
        index++;
        courseStates[i] = 2;
        return true;
    }
}
