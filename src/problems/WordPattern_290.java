package problems;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2020/3/14.
 */
public class WordPattern_290 {
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length!=pattern.length())return false;
        Map<Character,String> map = new HashMap<>();
        for (int i=0;i<pattern.length();i++){
            if (map.containsKey(pattern.charAt(i))){
                if (!map.get(pattern.charAt(i)).equals(words[i]))return false;
            }else {
                if (map.containsValue(words[i]))return false;
                map.put(pattern.charAt(i),words[i]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new WordPattern_290().wordPattern("abba","dog dog dog dog");
    }
}
