package problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P149_MaxPointsonaLine {

    //my solution1 最大公约数 存商和余数 16ms
    public int maxPoints(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < points.length - 1; i++) {
            Map<Integer, Map<Integer, Integer>> counts = new HashMap<>();
            int samepoint = 0;
            int samerow = 0;
            int sameclone = 0;
            for (int j = i + 1; j < points.length; j++) {
                if (points[j][1] == points[i][1] && points[j][0] == points[i][0]) {
                    samepoint++;
                } else if (points[j][1] == points[i][1]) {
                    samerow++;
                } else if (points[j][0] == points[i][0]) {
                    sameclone++;
                } else {
                    int gcd = getGCD(points[j][0] - points[i][0], points[j][1] - points[i][1]);
                    int a = (points[j][0] - points[i][0]) / gcd;
                    int b = (points[j][1] - points[i][1]) / gcd;
                    if (a < 0) {
                        a = -a;
                        b = -b;
                    }

                    Map<Integer, Integer> countMap = counts.getOrDefault(a, new HashMap<>());
                    int count = countMap.getOrDefault(b, 0);
                    countMap.put(b, count + 1);
                    counts.put(a, countMap);
                }
            }
            for (Map<Integer, Integer> countMap : counts.values()) {
                for (Integer count : countMap.values()) {
                    max = Math.max(count + samepoint, max);
                }
            }
            max = Math.max(samepoint + samerow, max);
            max = Math.max(samepoint + sameclone, max);
        }
        return max + 1;
    }

    public int getGCD(int a, int b) {
        int r;
        while (b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    public static void main(String[] args) {
//        System.out.println(-5%3);
//        new MaxPointsonaLine_149().maxPoints2(new int[][]{{0,9},{138,429},{115,359},{115,359},{-30,-102},{230,709},{-150,-686},{-135,-613},{-60,-248},{-161,-481},{207,639},{23,79},{-230,-691},{-115,-341},{92,289},{60,336},{-105,-467},{135,701},{-90,-394},{-184,-551},{150,774}});
        new P149_MaxPointsonaLine().maxPoints(new int[][]{{0, 9}, {1, 5}, {0, 3}, {1, 8}, {0, 5}});
    }

    //1ms 这种方法投机取巧 测试用例不完善  {{0, 9}, {1, 5}, {0, 3}, {1, 8}, {0, 5}} 这个结果是2
    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public int maxPoints2(int[][] points) {
        if (points == null) {
            return 0;
        }
        if (points.length < 3) {
            return points.length;
        }
        int max = 2;
        Set<Integer> map = new HashSet<>();
        for (int i = 1; i < points.length; i++) {
            int cnt = 0;
            int x1 = points[i - 1][0];
            int y1 = points[i - 1][1];
            int x2 = points[i][0];
            int y2 = points[i][1];

            int xdif1 = x2 - x1;
            int ydif1 = y2 - y1;
            int gcd1 = gcd(ydif1, xdif1);
            if (xdif1 == 0 && ydif1 == 0) {
                for (int p = 0; p < points.length; p++) {
                    if (points[p][0] == x1 && points[p][1] == y1) {
                        cnt++;
                    }
                }
            } else {
                if (!map.contains(gcd1)) {
                    for (int p = 0; p < points.length; p++) {
                        if ((((long) points[p][1] - y2) * ((long) x2 - x1)) == (((long) y2 - y1) * ((long) points[p][0]
                            - x2))) {
                            cnt++;
                            int gcd2 = gcd(points[p][1] - y2, points[p][0] - x2);
                            gcd1 = gcd(gcd1, gcd2);
                            map.add(gcd1);
                        }
                    }

                }
            }
            max = Math.max(max, cnt);
        }
        return max;
    }
}
