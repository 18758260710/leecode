package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder_127 {
    //my solution1 正向搜索 63ms
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Map<String, Integer> distance = new HashMap<>();// 记录每个节点到起始位置的步数

        dict.add(beginWord);

        return bfs(beginWord, endWord, dict, distance);//BFS找到每个节点到起始位置的步数
    }

    private int bfs(String start, String end, Set<String> dict, Map<String, Integer> distance) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distance.put(start, 1);

        while (!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                String cur = queue.poll();
                int curDistance = distance.get(cur);
                List<String> neighbors = getNeighbors(cur, dict);

                for (String neighbor : neighbors) {
                    if (!distance.containsKey(neighbor)) {// 如果已经有了，那必然比当前这条路短
                        distance.put(neighbor, curDistance + 1);
                        if (end.equals(neighbor))// 找到就可以结束了
                            return curDistance + 1;
                        else
                            queue.offer(neighbor);
                    }
                }
            }
        }

        return 0;
    }

    private List<String> getNeighbors(String cur, Set<String> dict) {//better way to find neighbors
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

    int count=1;

    //my solution2 8ms 双向搜索
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> start = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> dictionary = new HashSet<>(wordList);
        if (!dictionary.contains(endWord)) {
            return 0;
        }
        start.add(beginWord);
        end.add(endWord);
        return buildMap(start, end, dictionary, false);
    }

    private int buildMap(Set<String> start, Set<String> end, Set<String> dictionary,
        boolean reverse) {
        if (start.size() > end.size()) {//优化两边趋近
            return buildMap(end, start, dictionary, !reverse);
        }

        dictionary.removeAll(start);
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
                            return count+1;
                        } else {
                            next.add(newWord);
                        }
                        //map中的每个value都是key通往结果的最短路径的下一步
                    }
                }

                oldWordArray[i] = oldLetter;
            }
        }
        count++;

        if (!next.isEmpty()) {
            return buildMap(next, end, dictionary, reverse);
        }
        return 0;
    }
}
