package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class P126_WordLadderII {

    int minLength = Integer.MAX_VALUE;
    //my solution1 超时 也就是说要缓存
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        temp.add(beginWord);
        helper(beginWord, endWord, temp, wordList, result);
        return result;
    }

    private void helper(String beginWord, String endWord, List<String> temp, List<String> wordList,
        List<List<String>> result) {
        if (beginWord.equals(endWord)) {
            if (temp.size() < minLength) {
                result.clear();
                minLength = temp.size();
            }
            result.add(new ArrayList<>(temp));
            return;
        }
        if (temp.size() == minLength) {
            return;
        }
        List<String> copy = new ArrayList<>(wordList);
        for (String word:copy){
            if (match(beginWord,word)){
                temp.add(word);
                wordList.remove(word);
                helper(word,endWord,temp,wordList,result);
                wordList.add(word);
                temp.remove(temp.size()-1);
            }
        }
    }

    public boolean match(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        return count == 1;
    }

    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add("hot");
        a.add("dot");
        a.add("dog");
        a.add("lot");
        a.add("log");
        a.add("cog");
        a.add("aaa");
        List aa = new P126_WordLadderII().findLadders3("hit","cog",a);
        System.out.println(aa);
    }

    //其实是个图算法 最短路径规划 100+ms 慢
    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> nodeNeighbors = new HashMap<>();// 记录每个节点和哪些节点相邻
        Map<String, Integer> distance = new HashMap<>();// 记录每个节点到起始位置的步数
        LinkedList<String> solution = new LinkedList<>();

        dict.add(beginWord);

        for (String str : dict)
            nodeNeighbors.put(str, new ArrayList<>());//初始化连接记录

        bfs(beginWord, endWord, dict, nodeNeighbors, distance);//BFS找到每个节点到起始位置的步数
        dfs(beginWord, endWord, nodeNeighbors, distance, solution, res);

        return res;
    }

    private void dfs(String cur, String end, Map<String, List<String>> nodeNeighbors, Map<String, Integer> distance, LinkedList<String> solution, List<List<String>> res) {
        solution.add(cur);
        if (end.equals(cur)) {
            res.add(new ArrayList<>(solution));
        } else {
            for (String next : nodeNeighbors.get(cur)) {
                if (distance.get(next) == distance.get(cur) + 1) {
                    dfs(next, end, nodeNeighbors, distance, solution, res);
                }
            }
        }
        solution.removeLast();
    }

    private void bfs(String start, String end, Set<String> dict, Map<String, List<String>> nodeNeighbors,
        Map<String, Integer> distance) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            int count = queue.size();
            boolean foundEnd = false;
            for (int i = 0; i < count; i++) {
                String cur = queue.poll();
                int curDistance = distance.get(cur);
                List<String> neighbors = getNeighbors(cur, dict);

                for (String neighbor : neighbors) {
                    nodeNeighbors.get(cur).add(neighbor);
                    if (!distance.containsKey(neighbor)) {// 如果已经有了，那必然比当前这条路短
                        distance.put(neighbor, curDistance + 1);
                        if (end.equals(neighbor))// 找到就可以结束了
                            foundEnd = true;
                        else
                            queue.offer(neighbor);
                    }
                }
            }

            if (foundEnd)
                break;
        }
    }

    private List<String> getNeighbors(String cur, Set<String> dict) {
        List<String> res = new ArrayList<>();
        for (String temp:dict){
            if (match(cur,temp))res.add(temp);
        }
        return res;
    }

    private List<String> getNeighbors2(String cur, Set<String> dict) {//better way to find neighbors
        List<String> res = new ArrayList<>();
        char chs[] = cur.toCharArray();

        for (char ch ='a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }

        }
        return res;
    }


    //前后翻转来寻找相邻关系 且只保留路径上的点进map
    public List<List<String>> findLadders3(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> start = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> dictionary = new HashSet<>(wordList);
        Map<String, List<String>> map = new HashMap<>();

        if (!dictionary.contains(endWord)) {
            return result;
        }

        start.add(beginWord);
        end.add(endWord);
        buildMap(map, start, end, dictionary, false);
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        buildResult(map, result, path, beginWord, endWord);

        return result;
    }

    private void buildMap(Map<String, List<String>> map, Set<String> start, Set<String> end, Set<String> dictionary,
        boolean reverse) {
        if (start.size() > end.size()) {//优化两边趋近
            buildMap(map, end, start, dictionary, !reverse);
            return;
        }

        dictionary.removeAll(start);
        boolean finished = false;
        Set<String> next = new HashSet<>();

        for (String oldWord : start) {
            char[] oldWordArray = oldWord.toCharArray();

            for (int i = 0; i < oldWordArray.length; ++i) {
                char oldLetter = oldWordArray[i];

                for (char letter = 'a'; letter <= 'z'; ++letter) {
                    oldWordArray[i] = letter;
                    String newWord = new String(oldWordArray);

                    if (dictionary.contains(newWord)) {
                        if (end.contains(newWord)) {
                            finished = true;
                        } else {
                            next.add(newWord);
                        }
                        //map中的每个value都是key通往结果的最短路径的下一步
                        String key = reverse ?  newWord : oldWord;
                        String value = reverse ? oldWord : newWord;

                        map.computeIfAbsent(key, k -> new ArrayList<>());

                        map.get(key).add(value);

                    }
                }

                oldWordArray[i] = oldLetter;
            }
        }

        if (!finished && !next.isEmpty()) {
            buildMap(map, next, end, dictionary, reverse);
        }
    }

    private void buildResult(Map<String, List<String>> map, List<List<String>> result, List<String> path, String word,
        String endWord) {
        if (word.equals(endWord)) {
            result.add(new ArrayList<>(path));
        }

        if (map.get(word) == null) {
            return;
        }

        for (String nextWord : map.get(word)) {
            path.add(nextWord);
            buildResult(map, result, path, nextWord, endWord);
            path.remove(path.size() - 1);
        }
    }
}
