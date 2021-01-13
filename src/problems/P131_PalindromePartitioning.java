package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P131_PalindromePartitioning {
    //my solution1 3ms
    Map<String,List<List<String>>> cache = new HashMap<>();
    public List<List<String>> partition(String s) {
        if (cache.containsKey(s))return cache.get(s);

        List<List<String>> result = new ArrayList<>();
        if (s.length()==1){
            List<String> item = new ArrayList<>();
            item.add(s);
            result.add(item);
            cache.put(s,result);
            return result;
        }

        String first = s.substring(0,1);
        List<List<String>> next = partition(s.substring(1));
        for (List<String> one:next){
            List<String> item = new ArrayList<>();
            item.add(first);
            item.addAll(one);
            result.add(item);
        }

        for (int i=1;i<s.length()-1;i++){
            if (s.charAt(i)==s.charAt(0)&&isPalindrome(s.substring(0,i+1))){
                first = s.substring(0,i+1);
                next = partition(s.substring(i+1));
                for (List<String> one:next){
                    List<String> item = new ArrayList<>();
                    item.add(first);
                    item.addAll(one);
                    result.add(item);
                }
            }
        }

        if (isPalindrome(s)){
            List<String> item = new ArrayList<>();
            item.add(s);
            result.add(item);
        }
        cache.put(s,result);
        return result;
    }
    public boolean isPalindrome(String a){
        StringBuilder stringBuilder = new StringBuilder(a);
        return stringBuilder.toString().equals(stringBuilder.reverse().toString());
    }

    public static void main(String[] args) {
        System.out.println(new P131_PalindromePartitioning().partition("aab"));
    }

    //1ms 先记录所有回文 然后dfs
    public List<List<String>> partition2(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];

        // expand panlindrome
        for (int center = 0; center < n; center++) {
            expand(isPalindrome, s, center, center);
            expand(isPalindrome, s, center, center+1);
        }

        List<List<String>> res = new ArrayList<>();
        List<String> stack = new ArrayList<>();
        getPartitions(res, stack, isPalindrome, s, 0);
        return res;
    }

    private void getPartitions(List<List<String>> res, List<String> stack, boolean[][] isPalindrome, String s, int i) {
        if (i == s.length()) {
            res.add(new ArrayList<>(stack));
            return;
        }

        for (int j = i; j < s.length(); j++) {
            if (isPalindrome[i][j]) {
                stack.add(s.substring(i, j+1));
                getPartitions(res, stack, isPalindrome, s, j+1);
                stack.remove(stack.size()-1);
            }
        }
    }

    private void expand(boolean[][] isPalindrome, String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            isPalindrome[l][r] = true;
            l--; r++;
        }
    }
}
