package problems;

import java.util.Objects;

/**
 * Created by Administrator on 2020/2/11.
 */
public class P242_ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length()!=t.length())return false;
        int[] bitsmap = new int[26];
        for (char c:s.toCharArray()){
            bitsmap[c-'a']++;
        }
        for (char c:t.toCharArray()){
            if (bitsmap[c-'a']==0)return false;
            bitsmap[c-'a']--;
        }
        return true;
    }

    public boolean isAnagram2(String s, String t) {
        if (Objects.equals(s, t)) return true;
        if (s == null || t == null) return false;
        int[] k = new int[26], h = new int[26];
        for (char c : s.toCharArray()) {
            k[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            h[c - 'a']++;
        }
        for (int i = 0; i < 26; ++i) {
            if (k[i] != h[i]) {
                return false;
            }
        }
        return true;
    }
}
