package problems;

public class P87_ScrambleString {
    //my solution1 2ms
    public boolean isScramble(String s1, String s2) {
        System.out.println("s1:"+s1+",s2:"+s2);
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        if (s1.length() == 1) {
            return false;
        }
        int length=s1.length();
        //提前筛选
        int[] letter = new int[26];
        for(int i = 0; i < length; i++){
            letter[s1.charAt(i)-'a']++;
            letter[s2.charAt(i)-'a']--;
        }
        for(int i = 0; i < 26; i++){
            if(letter[i] != 0){
                return false;
            }
        }
//        //另一种提前筛选
//        long a =0;
//        long b =0;
//        for(int i = 0; i < length; i++){
//            a+=s1.charAt(i)*s1.charAt(i)*s1.charAt(i)*s1.charAt(i);
//            b+=s2.charAt(i)*s2.charAt(i)*s2.charAt(i)*s2.charAt(i);
//        }
//        if (a!=b)return false;

        for (int regex=1;regex<length;regex++) {
            if((isScramble(s1.substring(0, regex), s2.substring(0, regex)) && isScramble(s1.substring(regex),
                s2.substring(regex))) || (isScramble(s1.substring(0, regex), s2.substring(length-regex)) && isScramble(
                s1.substring(regex), s2.substring(0, length-regex))))return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new P87_ScrambleString().isScramble("aabb","abab"));
    }


    //1ms
    public boolean isScramble2(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int len = str1.length;
        int[] a = new int[26];

        int i = 0, j = len - 1;
        if (str1[i] == str2[i]) {            // 先处理相等的情况
            while (i < len && str1[i] == str2[i]) {
                i++;
            }
        } else {  // 不相等的情况
            a[str1[i] - 'a']++;
            a[str2[i] - 'a']--;
            i++;
            while (i < len && !isEqual(a)) {
                a[str1[i] - 'a']++;
                a[str2[i] - 'a']--;
                i++;
            }
        }
        if (i < len) {            // 没到头，说明可能为true
            boolean res =
                isScramble2(s1.substring(0, i), s2.substring(0, i)) && isScramble2(s1.substring(i), s2.substring(i));
            if (res) {
                return true;
            }
        }
        // 正序没找到,反过来找
        i = 0;
        a = new int[26];
        if (str1[i] == str2[j]) {
            while (i < len && str1[i] == str2[j]) {
                i++;
                j--;
            }
            if (i == len) {
                return true;
            }
        } else {
            a[str1[i] - 'a']++;
            a[str2[j] - 'a']--;
            i++;
            j--;
            while (i < len && !isEqual(a)) {
                a[str1[i] - 'a']++;
                a[str2[j] - 'a']--;
                i++;
                j--;
            }
        }
        // 注意这边的截取规则，包含头，不包含尾
        return i < len && isScramble2(s1.substring(0, i), s2.substring(j + 1)) && isScramble2(s1.substring(i),
            s2.substring(0, j + 1));
    }

    public boolean isEqual(int[] a){
        for(int i=0 ; i<26 ; i++){
            if(a[i] != 0)
                return false;
        }
        return true;
    }
}
