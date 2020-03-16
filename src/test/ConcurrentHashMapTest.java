package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        Map<Integer,Integer> map = new ConcurrentHashMap<>();
//        Map<Integer,Integer> map2 = new HashMap<>();
        map.put(1,1);
        map.put(2,1);
        map.put(9,1);
        map.put(17,1);
        map.put(29,2);
//        Set<Integer> keys = map.keySet();
//        keys.add(4);
//        map.get(1);
//        map.remove(1);
//        Iterator<Integer> iterator = map.keySet().iterator();
//        iterator.hasNext();
//        iterator.next();
    }
}
