package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SubstringwithConcatenationofAllWords_30 {

    //my first solution not good
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (words.length==0)return result;
        String first = words[0];
        int length = first.length();
        int slength = s.length();
        if (slength<length*words.length)return result;
        for (String a:new HashSet<>(Arrays.asList(words))){
            int start=0;
            String temps = s;
            while (true) {
                int index = temps.indexOf(a);
                if (index<0)break;
                if (index + length * words.length > temps.length())
                    break;
                boolean match = checkMatch(temps.substring(index,index + length * words.length),words,length);
                if (match)result.add(index+start);
                temps = temps.substring(index+1);
                start+=index+1;
            }
        }
        return result;
    }
    private boolean checkMatch(String substring, String[] remains,int length) {
        List copy = Arrays.asList(remains);
        ArrayList copys=new ArrayList(copy);
        while (!substring.isEmpty()){
            String word = substring.substring(0,length);
            if (!copys.remove(word))return false;
            substring = substring.substring(length);
        }
        return true;
    }

    //my second solution not good too
    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        int wlength=words.length;
        if (wlength==0)return result;
        Map<Integer,String> fenbu = new HashMap<>();
        Map<String,Integer> geshu = new HashMap<>();

        int length =  words[0].length();
        Set<String> aa = new HashSet<>();
        for (String word:words){
            geshu.put(word,geshu.getOrDefault(word,0)+1);
            aa.add(word);
        }

        for (String word:aa) {
            String temp =s;
            while (true) {
                int index = temp.indexOf(word);
                if (index < 0)
                    break;
                fenbu.put(index, word);
                temp = new StringBuilder(temp).replace(index, index + 1, "_").toString();
            }
        }

        List<Integer> keysnew = new ArrayList(fenbu.keySet());
        Collections.sort(keysnew);
        for (Integer start: keysnew){
            if (start+wlength*length>s.length())break;
            Map<String,Integer> geshu2 = new HashMap<>();
            geshu2.put(fenbu.get(start),1);
            for (int i=1;i<wlength;i++){
                if (fenbu.containsKey(start+i*length)){
                    String word = fenbu.get(start+i*length);
                    geshu2.put(word,geshu2.getOrDefault(word,0)+1);
                }else break;
            }
            if (geshu.equals(geshu2))result.add(start);
        }
        return result;
    }

    //worst
    public List<Integer> findSubstring3(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        int wlength=words.length;
        if (wlength==0)return result;
        List<String> wordsCopy =Arrays.asList(words);
        Map<Integer,String> fenbu = new HashMap<>();
        Map<String,Integer> geshu = new HashMap<>();

        int length =  words[0].length();
        Set<String> aa = new HashSet<>();
        for (String word:words){
            geshu.put(word,geshu.getOrDefault(word,0)+1);
            aa.add(word);
        }

        for (String word:aa) {
            String temp =s;
            while (true) {
                int index = temp.indexOf(word);
                if (index < 0)
                    break;
                fenbu.put(index, word);
                temp = new StringBuilder(temp).replace(index, index + 1, "_").toString();
            }
        }

        List<Integer> keysnew = new ArrayList(fenbu.keySet());
        Collections.sort(keysnew);
        for (Integer start: keysnew){
            if (start+wlength*length>s.length())break;
            List<String> wordsList = new ArrayList<>(wordsCopy);
            wordsList.remove(fenbu.get(start));
            for (int i=1;i<wlength;i++){
                if (fenbu.containsKey(start+i*length)){
                    String word = fenbu.get(start+i*length);
                    if (!wordsList.remove(word))break;
                }else break;
            }
            if (wordsList.isEmpty())result.add(start);
        }
        return result;
    }

    //by jianchao-li
    public List<Integer> findSubstring4(String s, String[] words) {
        final List<Integer> indexes = new ArrayList<>();
        if (words.length==0)return indexes;
        final Map<String, Integer> counts = new HashMap<>();
        for (final String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        final int n = s.length(), num = words.length, len = words[0].length();
        for (int i = 0; i < n - num * len + 1; i++) {
            final Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while (j < num) {
                final String word = s.substring(i + j * len, i + (j + 1) * len);
                if (counts.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    if (seen.get(word) > counts.getOrDefault(word, 0)) {
                        break;
                    }
                } else {
                    break;
                }
                j++;
            }
            if (j == num) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    //by shaka.shadows fastest sliding window
    public List<Integer> findSubstring5(String s, String[] words) {
        int N = s.length();
        List<Integer> indexes = new ArrayList<>(s.length());
        if (words.length == 0) {
            return indexes;
        }
        int M = words[0].length();
        if (N < M * words.length) {
            return indexes;
        }
        int last = N - M + 1;

        //map each string in words array to some index and compute target counters
        Map<String, Integer> mapping = new HashMap<>(words.length);
        int [][] table = new int[2][words.length];
        int failures = 0, index = 0;
        for (int i = 0; i < words.length; ++i) {
            Integer mapped = mapping.get(words[i]);
            if (mapped == null) {
                ++failures;
                mapping.put(words[i], index);
                mapped = index++;
            }
            ++table[0][mapped];
        }

        //find all occurrences at string S and map them to their current integer, -1 means no such string is in words array
        int [] smapping = new int[last];
        for (int i = 0; i < last; ++i) {
            String section = s.substring(i, i + M);
            Integer mapped = mapping.get(section);
            if (mapped == null) {
                smapping[i] = -1;
            } else {
                smapping[i] = mapped;
            }
        }

        //fix the number of linear scans
        for (int i = 0; i < M; ++i) {
            //reset scan variables
            int currentFailures = failures; //number of current mismatches
            int left = i, right = i;
            Arrays.fill(table[1], 0);
            //here, simple solve the minimum-window-substring problem
            while (right < last) {
                while (currentFailures > 0 && right < last) {
                    int target = smapping[right];
                    if (target != -1 && ++table[1][target] == table[0][target]) {
                        --currentFailures;
                    }
                    right += M;
                }
                while (currentFailures == 0 && left < right) {
                    int target = smapping[left];
                    if (target != -1 && --table[1][target] == table[0][target] - 1) {
                        int length = right - left;
                        //instead of checking every window, we know exactly the length we want
                        if ((length / M) ==  words.length) {
                            indexes.add(left);
                        }
                        ++currentFailures;
                    }
                    left += M;
                }
            }

        }
        return indexes;
    }

    public static void main(String[] args) {
        System.out.println(new SubstringwithConcatenationofAllWords_30().findSubstring5("barfoothefoobarmanbarbarfoo",new String[]{"foo","bar","bar"}));
    }
}

