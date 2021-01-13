package problems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P49_GroupAnagrams {
    //my solution not best
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String,List<String>> temp = new HashMap<>();
        for (String str:strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (temp.containsKey(key)){
                temp.get(key).add(str);
            }else {
                List<String> list = new ArrayList<>();
                list.add(str);
                temp.put(key,list);
            }
        }

        return new ArrayList<>(temp.values());
    }

    //不会溢出 但是慢
    public List<List<String>> groupAnagrams2(String[] strs) {

        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};

        List<List<String>> res = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : strs) {
            BigInteger big_val = new BigInteger("1");
            for (char c : s.toCharArray()) {
                big_val = big_val.multiply(new BigInteger(String.valueOf(prime[c - 'a'])));
            }
            String key = big_val.toString();
            List<String> t;
            if (map.containsKey(key)) {
                t = res.get(map.get(key));
            } else {
                t = new ArrayList<>();
                res.add(t);
                map.put(key, res.size() - 1);
            }
            t.add(s);
        }
        return res;
    }

    //fast 但是小心溢出
    public static List<List<String>> groupAnagrams3(String[] strs) {
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};//最多10609个z

        List<List<String>> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (String s : strs) {
            int key = 1;
            for (char c : s.toCharArray()) {
                key *= prime[c - 'a'];
            }
            List<String> t;
            if (map.containsKey(key)) {
                t = res.get(map.get(key));
            } else {
                t = new ArrayList<>();
                res.add(t);
                map.put(key, res.size() - 1);
            }
            t.add(s);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new P49_GroupAnagrams().groupAnagrams2(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
