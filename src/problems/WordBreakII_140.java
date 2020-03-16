package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreakII_140 {
    //my solution1 超时 从后往前
    int count = 0;
    Map<Integer,List<String>> temp = new HashMap<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        s=s+"$";
        List<String> last = new ArrayList<>();
        last.add("");
        temp.put(s.length()-1,last);
        for (int i=s.length()-1;i>0;i--) {
            if (temp.containsKey(i)) {
                count++;
                for (String word : wordDict) {
                    int length = word.length();

                    if (i-length>=0&&s.substring(i-length,i).equals(word)) {
                        List<String> current = temp.getOrDefault(i-length,new ArrayList<>());
                        for (String a:temp.get(i)){
                            current.add(word+(a.isEmpty()?"":" ")+a);
                        }
                        temp.put(i-length,current);
                    }
                }
            }
        }
        return temp.getOrDefault(0,new ArrayList<>());
    }

    public static void main(String[] args) {
        WordBreakII_140 wordBreakII_140 = new WordBreakII_140();
        System.out.println(wordBreakII_140.wordBreak3("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Arrays.asList(new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"})));
        System.out.println(wordBreakII_140.count);
    }

    //从前往后 循环次数少 4ms
    public List<String> wordBreak2(String s, List<String> wordDict) {
        return DFS(s, wordDict, new HashMap<>());
    }

    List<String> DFS(String s, List<String> wordDict, HashMap<String, List<String>>map) {
        if (map.containsKey(s))
            return map.get(s);

        List<String>res = new ArrayList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        count++;
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }

    //1ms 有点投机取巧 如果dict中数量少但是长度差大，则会慢  循环次数和2一样 单次循环较快 空间更少 因为没有存 如果存了不知道会不会更快
    public List<String> wordBreak3(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();

        if(wordDict.isEmpty()) return result;

        boolean[] invalid = new boolean[s.length()];
        Set<String> keys = new HashSet<>();
        int minLen = wordDict.get(0).length();
        int maxLen = wordDict.get(0).length();
        StringBuilder sentence = new StringBuilder();
        for(String word:wordDict){
            keys.add(word);
            minLen = Math.min(minLen,word.length());
            maxLen = Math.max(maxLen,word.length());
        }

        word(s,keys,0,minLen,maxLen,sentence,result,invalid);
        return result;
    }

    public boolean word(String s,Set<String> keys,int start,int min,int max,StringBuilder sb, List<String> result,boolean[] invalid){
        if(start>=s.length()){
            result.add(sb.toString().trim());
            return true;
        }
        boolean found = false;
        count++;
        for(int i=start+min;i<=Math.min(s.length(),start+max);++i){
            if(i < s.length() && invalid[i]) {
                continue;
            }
            String key = s.substring(start,i);
            if(keys.contains(key)){
                int oldLength =  sb.length();
                sb.append(" ").append(key);//加上key
                found |= word(s,keys,i,min,max,sb,result,invalid);
                sb.setLength(oldLength);//去掉key
            }
        }

        if (!found) {
            invalid[start] = true;
            return false;
        }

        return true;
    }
}
