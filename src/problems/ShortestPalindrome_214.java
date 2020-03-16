package problems;

/**
 * Created by Administrator on 2020/1/14.
 */
public class ShortestPalindrome_214 {
    //超时 本质是找左边开始的最长回文
    public String shortestPalindrome(String s) {
        int length = s.length();
        int i;
        for (i=length-1;i>=0;i--){
            if (s.charAt(i)==s.charAt(0)){
                if (check(s,1,i-1))break;
            }
        }
        if (i==length-1)return s;
        String tail = new StringBuilder(s.substring(i+1)).reverse().toString();
        return tail+s;
    }

    private boolean check(String s, int left, int right) {
        return left >= right || s.charAt(left) == s.charAt(right) && check(s, left + 1, right - 1);
    }

    public static void main(String[] args) {
        System.out.println(new ShortestPalindrome_214().shortestPalindrome2("aacecaaa"));
    }

//2ms niupi
    public String shortestPalindrome2(String s) {
        int i = 0;
        int length = s.length();
        for(int j=length-1;j>=0;j--)
        {
            if(s.charAt(i) == s.charAt(j)){
                i++;
            }
        }
//
//        # 这里的i其实是一个上界
//        # 解释: 如果存在j 使 shortestPalindrome = s[:j][::-1]+s
//        # 那么, i 永远大于end-j
//        # i==j 当且仅当 s是一个回文串
//        # 且 i 永远大于 1
        if (i == length)
        return s;
        String tail = new StringBuilder(s.substring(i)).reverse().toString();
        return tail + shortestPalindrome(s.substring(0,i)) + s.substring(i);
    }

    //马拉车算法 4ms
    public String shortestPalindrome3(String s) {
        if (s.length() <= 1) return s;

        char[] manacher = new char[(s.length() << 1) + 1];
        int[] result = new int[manacher.length];
        for (int i = 0; i < manacher.length; ++i) {
            if ((i & 1) == 0) {
                manacher[i] = Character.OTHER_LETTER;
            } else {
                manacher[i] = s.charAt(i >> 1);
            }
        }
        result[0] = 1;
        result[1] = 2;
        int lastMiddle = 1;
        int maxRange = 2, maxRangeIndex = 1;
        for (int i = 2; i < result.length; ++i) {
            if (lastMiddle + result[lastMiddle] > i) {
                result[i] = Math.min(result[lastMiddle - (i - lastMiddle)], lastMiddle + result[lastMiddle] - i);
            }
            if (lastMiddle + result[lastMiddle] <= i + result[i]) {
                while (0 <= i - result[i] && i + result[i] < result.length) {
                    if (manacher[i - result[i]] == manacher[i + result[i]]) ++result[i];
                    else break;
                }
                if (result[i] > maxRange && i - result[i] < 0) {
                    maxRange = result[i];
                    maxRangeIndex = i;
                }
                lastMiddle = i;
            }
        }
        int padSize = result.length - (maxRangeIndex + maxRange);
        if (padSize == 0) return s;
        else {
            char[] padChars = new char[padSize >> 1];
            for (int i = 0; i < padChars.length; ++i) {
                padChars[i] = manacher[manacher.length - (i << 1) - 2];
            }
            return new String(padChars) + s;
        }
    }
}
