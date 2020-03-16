package problems;

import java.util.*;

/**
 * Created by Administrator on 2020/1/10.
 */
public class CourseSchedule_207 {
    //slow
    Map<Integer, Set<Integer>> top = new HashMap<>();
    boolean[] oks;
    Stack<Integer> temp = new Stack<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        oks = new boolean[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            Set<Integer> set = top.getOrDefault(prerequisites[i][0], new HashSet<>());
            set.add(prerequisites[i][1]);
            top.put(prerequisites[i][0], set);
        }

        for (Integer start : top.keySet()) {
            if (!dfs(start)) return false;
        }
        return true;
    }

    private boolean dfs(Integer start) {
        if (temp.contains(start)) return false;
        if (oks[start]) return true;
        if (!top.containsKey(start)) {
            oks[start] = true;
            return true;
        }
        temp.push(start);
        Set<Integer> depends = top.get(start);
        for (Integer depend : depends) {
            if (!dfs(depend)) return false;
        }
        temp.pop();
        return true;
    }

    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        int[] temp = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            temp[prerequisites[i][0]]++;
            Set<Integer> set = top.getOrDefault(prerequisites[i][1], new HashSet<>());
            set.add(prerequisites[i][0]);
            top.put(prerequisites[i][1], set);
        }
        Queue<Integer> zeros = new LinkedList<>();
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 0) {
                zeros.add(i);
            }
        }
        while (!zeros.isEmpty()) {
            int zero = zeros.poll();
            Set<Integer> set = top.get(zero);
            if (set==null)continue;
            for (int cu : set) {
                temp[cu]--;
                if (temp[cu]==0)zeros.add(cu);
            }
        }
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] > 0) {
                return false;
            }
        }
        return true;
    }

    //类似方法1  用数组记录状态 不用栈
    public boolean canFinish3(int numCourses, int[][] prerequisites) {
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
                    return false;
                }
            }
        }
        return true;
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

        courseStates[i] = 2;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new CourseSchedule_207().canFinish2(2, new int[][]{{1, 0}}));
    }
}
