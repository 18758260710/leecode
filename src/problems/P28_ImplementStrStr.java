package problems;


public class P28_ImplementStrStr {

    //赖皮方法
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    //简化上方方法
    public int strStr2(String haystack, String needle) {
        char[] source = haystack.toCharArray();
        char[] target = needle.toCharArray();
        int sourceCount = source.length;
        int targetCount = target.length;
        if (targetCount == 0) {
            return 0;
        }
        char first = target[0];
        int max =  sourceCount - targetCount;
        int targetOffset=0;
        for (int i = 0; i <= max; i++) {
            /* Look for first character. */
            if (source[i] != first) {
                while (++i <= max && source[i] != first);
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = targetOffset + 1; j < end && source[j]
                    == target[k]; j++, k++);

                if (j == end) {
                    /* Found whole string. */
                    return i;
                }
            }
        }
        return -1;
    }

    public int strStr3(String haystack, String needle) {
        for (int i = 0; i<=haystack.length()-needle.length(); i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
        return -1;
    }
}
