package problems;

import java.util.Arrays;

/**
 * @author wengtao
 * @date 2020/10/13
 **/
public class P318_MaximumProductofWordLengths {
    //366ms 暴力
    public int maxProduct(String[] words) {
        if (words.length == 0) return 0;
        Arrays.sort(words, (o1, o2) -> {
            if (o1.length() > o2.length())return -1;
            else if (o1.length() < o2.length())return 1;
            return 0;
        });

        int max = 0;
        for (int i = 0; i < words.length - 1; ++i) {
            if (words[i].length() * words[i + 1].length() <= max) break;
            for (int j = i + 1; j < words.length; ++j) {
                if (!haveSame(words[i], words[j])) {
                    max = Math.max(words[i].length() * words[j].length(), max);
                }
            }
        }
        return max;
    }

    boolean haveSame(String str1, String str2) {
        for (char ch1 : str1.toCharArray()) {
            for (char ch2 : str2.toCharArray()) {
                if (ch1 == ch2) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int i = new P318_MaximumProductofWordLengths().maxProduct(new String[]{"edadc","ebbfe","aacdde","dfe","cb","fddddff","fabca","adccac","ece","ccaf","feba","bcb","edadc","aea","bacb","acefa","fcebffd","dfeebca","bedcbaa","deaccc","abedc","dadff","eef","ddebbb","abecab","cd","abdeee","eedce","deef","dceaddd","ced","fbbf","ba","eefeda","fb","cddc","adafbf","dded","aadbf","caefbaf","ccebf","dbb","ee","dadcecc","ddbcabb","afeaa","ec","aad","efde","cbcda","cdbdafd","cddc","ecaaa","ae","cfc","bccac","cdcc","abbbf","fcdface","ddbcdc","bfebb","daed","bc","dc","ecdfc","eeb","bb","dad","caecb","fbe","bbbc","cacea","dbc","fbe","bcfffbd","aeda","cff","ddfc","ea","bdfd","ccb","cb","ae","ceabefa","dcea","cbaed","bfedf","fa","ccd","fece","bceef","acabca","dafa","fdeec","dac","cae","adeeadb","ecacc","acfe","de"});
        System.out.println(i);
    }

    //8ms 用位运算来判断是否重复
    public int maxProduct2(String[] words) {
        if (words.length == 0) return 0;
        int[] fast = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                fast[i] |= 1 << (c-'a');
            }
        }

        int max = 0;
        for(int i = 0; i < words.length-1; ++i) {
            for(int j = i+1; j < words.length; ++j) {
                if((fast[i] & fast[j]) == 0)
                    max = Math.max(words[i].length() * words[j].length(), max);
            }
        }
        return max;
    }
}
