package problems;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author wengtao
 * @date 2021/2/27
 **/
public class P345_ReverseVowelsofaString {
    private final static HashSet<Character> vowels = new HashSet<>(
            Arrays.asList('a', 'e', 'i', 'o', 'u', 'A',
                    'E', 'I', 'O', 'U'));

    public String reverseVowels(String s) {
        if(s==null) return null;
        int low = 0,high = s.length()-1;
        char[] c = new char[s.length()] ;
        while(low<=high){
            char cl = s.charAt(low);
            char ch = s.charAt(high);
            if(!vowels.contains(cl)){
                c[low++] = cl;
            }else if(!vowels.contains(ch)){
                c[high--] = ch;
            }else{
                c[low++] = ch;
                c[high--] = cl;
            }
        }
        return new String(c);
    }
}
