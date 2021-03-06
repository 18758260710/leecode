package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class P76_MinimumWindowSubstring {

    //my solution1 bad
    public String minWindow(String s, String t) {
        List<Character> remains = t.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        Map<Character, List<Integer>> temp = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            if (remains.contains(character)) {
                if (temp.containsKey(character)) {
                    temp.get(character).add(i);
                } else {
                    List<Integer> a = new ArrayList<>();
                    a.add(i);
                    temp.put(character, a);
                }
                list.add(i);
                remains.remove(character);
                if (remains.isEmpty()) {
                    result = s.substring(list.get(0), i + 1);
                }
            } else {
                if (temp.containsKey(character)) {
                    Integer index = temp.get(character).remove(0);
                    temp.get(character).add(i);
                    list.remove(index);
                    list.add(i);
                    if (remains.isEmpty()) {
                        if (result.isEmpty() || i - list.get(0) < result.length()) {
                            result = s.substring(list.get(0), i + 1);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new P76_MinimumWindowSubstring().minWindow("aa", "aa"));
    }

    //official solution1 sliding window
    public String minWindow2(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        //计数
        Map<Character, Integer> dictT = new HashMap<Character, Integer>();

        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }

        int required = dictT.size();

        int l = 0, r = 0;

        int formed = 0;

        Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();

        int[] ans = {-1, 0, 0};

        while (r < s.length()) {
            // Add one character from the right to the window
            char c = s.charAt(r);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);

            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            while (l <= r && formed == required) {
                c = s.charAt(l);
                // Save the smallest window until now.
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }

                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c) < dictT.get(c)) {
                    formed--;
                }

                l++;
            }

            r++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

    //fast
    public String minWindow4(String s, String t) {
        if(s.isEmpty() || t.isEmpty()) {
            return "";
        }

        // 用数组来记录需要字符个数
        int[] need = new int[128];
        for(char c : t.toCharArray()) {
            need[c]++;
        }

        int right = 0;
        int left = 0;
        int missing = t.length();
        char[] array = s.toCharArray();

        int i = 0;
        int j = 0;

        while (right < s.length()) {
            //需要的字符
            if (need[array[right]] > 0) {
                missing --;
            }
            //不需要的字符变为负数
            need[array[right]]--;
            right++;

            while (missing == 0) {
                if (j == 0 || (right - left) < (j - i)) {
                    j = right;
                    i = left;
                }
                need[array[left]]++;
                //大于0代表是需要的字符
                if (need[array[left]] > 0) {
                    missing++;
                }
                left++;
            }
        }
        return s.substring(i, j);
    }
}
