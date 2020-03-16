package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsofaPhoneNumber_17 {
    //my solution
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.isEmpty()) {
            return result;
        }
        char[] characters = digits.toCharArray();
        result.add("");
        Map<Character, char[]> map = new HashMap<>();
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});
        for (char a : characters) {
            List<String> temp = new ArrayList<>();
            for (String string : result) {
                for (char b : map.get(a)) {
                    temp.add(string + b);
                }
            }
            result = temp;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new LetterCombinationsofaPhoneNumber_17().letterCombinations("23"));
    }
}
