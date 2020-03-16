package problems;

import java.util.Arrays;
import java.util.List;

public class WordBreak_139 {
    //my solution1 3ms
    public boolean wordBreak(String s, List<String> wordDict) {
        s=s+"$";
        boolean[] temp = new boolean[s.length()];
        temp[s.length()-1] = true;
        for (int i=s.length()-1;i>0;i--) {
            if (temp[i]) {
                for (String word : wordDict) {
                    int length = word.length();

                    if (i-length>=0&&s.substring(i-length,i).equals(word)) {
                        temp[i-length]=true;
                    }
                }
            }
        }
        return temp[0];
    }

    public static void main(String[] args) {
        new WordBreak_139().wordBreak("leetcode", Arrays.asList(new String[]{"leet", "code"}));
    }

    //从前往后 记录不行的位置 1ms
    boolean[] marked;
    public boolean wordBreak2(String s, List<String> wordDict) {
        marked = new boolean[s.length()];
        return wordBreak(s, wordDict, 0);
    }
    public boolean wordBreak(String s, List<String> wordDict, int start) {
        if (start >= s.length()) return true;
        if (marked[start]) return false;
        for (String word: wordDict) {
            if (s.startsWith(word, start)) {
                if (wordBreak(s, wordDict, start + word.length())) return true;
                marked[start] = true;
            }
        }
        return false;
    }
}
