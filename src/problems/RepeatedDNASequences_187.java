package problems;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedDNASequences_187 {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet<>(), repeated = new HashSet<>();
        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            if (!seen.add(ten))
                repeated.add(ten);
        }
        return new ArrayList(repeated);
    }

    public static void main(String[] args) {
        new RepeatedDNASequences_187().findRepeatedDnaSequences2("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        int bitSet = 0;
        bitSet |= 1 << 3;
        System.out.println(Integer.toBinaryString(bitSet));
        bitSet |= 1 << 31;
        System.out.println(Integer.toBinaryString(bitSet));
        bitSet |= 1 << 4;
        System.out.println(Integer.toBinaryString(bitSet));
        bitSet |= 1 << 2;
        System.out.println(Integer.toBinaryString(bitSet));
        System.out.println(bitSet & 1 << 3);
        System.out.println(bitSet & 1 << 6);
        System.out.println(bitSet & 1 << 1);
    }

    //用bitset 来存储重复
    public List<String> findRepeatedDnaSequences2(String s) {
        int n = s.length();
        int[] map = new int[128];
        map['C'] = 1;
        map['G'] = 2;
        map['T'] = 3;
        List<String> res = new ArrayList<>();
        if (n <= 10)
            return res;

        char[] nums = s.toCharArray();
        BitSet seen = new BitSet(1 << 20);
        BitSet repeat = new BitSet(1 << 20);
        int num = 0;
        for (int i = 0; i < 10; i++) {
            num |= map[nums[i]] << (i * 2);
        }
        seen.set(num);

        for (int i = 10; i < n; i++) {
            num >>= 2;
            num |= map[nums[i]] << 18;
            if (!seen.get(num)) {
                seen.set(num);
            } else if (!repeat.get(num)) {
                res.add(s.substring(i - 9, i + 1));
                repeat.set(num);
            }
        }
        return res;
    }
}
