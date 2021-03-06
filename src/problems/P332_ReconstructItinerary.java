package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author wengtao
 * @date 2021/2/18
 **/
public class P332_ReconstructItinerary {
    //mysolution dfs 8ms
    private Map<String, List<String>> map = new HashMap<>();

    private LinkedList<String> resList = new LinkedList<>();

    int depth;

    public List<String> findItinerary(List<List<String>> tickets) {
        depth = tickets.size();
        for (List<String> ticket : tickets) {
            String src = ticket.get(0);
            String dst = ticket.get(1);
            if (!map.containsKey(src)) {
                List<String> pq = new ArrayList<>();
                map.put(src, pq);
            }
            map.get(src).add(dst);
            Collections.sort(map.get(src));
        }
        dfs("JFK");
        return resList;
    }

    private void dfs(String src) {
        List<String> pq = map.get(src);
        if (pq != null) {
            for (int i = 0; i < pq.size(); i++) {
                String target = pq.get(i);
                if ("#".equals(target)) {
                    continue;
                }
                pq.set(i, "#");
                depth--;
                dfs(target);
                if (depth != 0) {
                    pq.set(i, target);
                    depth++;
                }
            }
        }
        if (depth == 0) {
            resList.addFirst(src);
        }
    }

    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("MUC", "LHR"));
        tickets.add(Arrays.asList("JFK", "MUC"));
        tickets.add(Arrays.asList("JFK", "AAA"));
        tickets.add(Arrays.asList("AAA", "JFK"));
        tickets.add(Arrays.asList("SFO", "SJC"));
        tickets.add(Arrays.asList("LHR", "SFO"));
        new P332_ReconstructItinerary().findItinerary2(tickets);
    }

    //用pq 6ms  其实不需要把元素再放回去 因为这个图必然是符合要求的
    private Map<String, PriorityQueue<String>> pqMap = new HashMap<>();

    public List<String> findItinerary2(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0);
            String dst = ticket.get(1);
            if (!pqMap.containsKey(src)) {
                PriorityQueue<String> pq = new PriorityQueue<>();
                pqMap.put(src, pq);
            }
            pqMap.get(src).add(dst);
        }
        dfs2("JFK");
        return resList;
    }

    private void dfs2(String src) {
        PriorityQueue<String> pq = pqMap.get(src);
        while (pq != null && !pq.isEmpty())
            dfs2(pq.poll());
        resList.addFirst(src);
    }
}
