package problems;

import java.util.Arrays;

/**
 * Created by Administrator on 2020/3/4.
 */
public class P567_PermutationinString {
    //mysolution1 9ms
    public boolean checkInclusion(String s1, String s2) {
        int[] bitmap = new int[26];
        for (char a:s1.toCharArray())
            bitmap[a-'a']++;

        int[] bitmap2 = new int[26];
        int start=0;
        int end=0;

        int tooMuch=0;
        while (end<s2.length()){
            tooMuch=s2.charAt(end)-'a';
            bitmap2[tooMuch]++;
            end++;
            while (bitmap[tooMuch]<bitmap2[tooMuch]){
                bitmap2[s2.charAt(start)-'a']--;
                start++;
            }
            if (Arrays.equals(bitmap,bitmap2))return true;
        }
        return false;
    }

    //4ms
    public boolean checkInclusion2(String s1, String s2) {
        if(s1==null || s2==null || s1.length()>s2.length()) return false;
        char[] c1 = s2.toCharArray();
        char[] c2 = s1.toCharArray();
        int[] map = new int[256];
        for(int i=0;i<c2.length;i++){
            map[c2[i]]++;
        }
        int left=0,right=0,match=c2.length,min=Integer.MAX_VALUE;
        while(right<c1.length){
            map[c1[right]]--;
            if(map[c1[right]]>=0) match--;
            if(match==0){
                while(map[c1[left]]<0) map[c1[left++]]++;
                min=Math.min(min,(right-left+1));
                map[c1[left++]]++;match++;
            }
            right++;

        }
        return min == c2.length;

    }

    public static void main(String[] args) {
        ClassLoader a = P567_PermutationinString.class.getClassLoader();
        System.out.println();
    }
}
