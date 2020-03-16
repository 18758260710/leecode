package problems;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters_3 {

    public int mysolution(String s) {
        int length = 0;
        Map<Character, Integer> exists = new HashMap<>();
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            Character character = s.charAt(j);
            Integer existIndex = exists.get(character);
            if (existIndex != null && existIndex >= i) {
                exists.put(character, j);
                length = Math.max(length, j - i);
                i = existIndex + 1;
            }
            exists.put(character, j);
        }
        return Math.max(length, s.length() - i);
    }

    //official solution
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    //by mynenisruthi better than official
    public int lengthOfLongestSubstring2(String s) {
        String temp = "", charS = "";
        int count1 = 0, count2 = 0;
        int length = s.length();

        for (int i = 0; i < length; i++) {

            charS = String.valueOf(s.charAt(i));
            if (!temp.contains(charS)) {
                temp = temp.concat(charS);
                count1++;

                if (count1 > count2 && i == length - 1) {
                    return count1;
                }

            } else {

                int index = temp.indexOf(charS);
                temp = temp.concat(charS);
                temp = temp.substring(index + 1);
                if (count1 >= count2) {
                    count2 = count1;
                }
                count1 = temp.length();
                if (length-i+count1<count2)break;//don't know whether this early break whill work, it's added by myself
            }
        }

        return count2;

    }
}
