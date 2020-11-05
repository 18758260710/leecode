package problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * @author wengtao
 * @date 2020/9/30
 **/
public class RemoveDuplicateLetters_316 {
    //my solution 25ms slow O(n^2)
    public String removeDuplicateLetters(String s) {
        if (s.length()<=1){
            return s;
        }
        char[] chars = s.toCharArray();
        int currentIndex = chars.length-1;
        char current = 'z'+ 1;
        boolean[] choose = new boolean[26];
        for (int i = chars.length-1 ; i >=0; i--) {
            if (choose[chars[i]-'a'] && chars[i] <= current){
                current = chars[i];
                currentIndex = i;
                continue;
            }
            if (!choose[chars[i]-'a']){
                current = chars[i];
                currentIndex = i;
                choose[chars[i]-'a'] = true;
            }
        }
        return current+removeDuplicateLetters(s.substring(currentIndex+1).replaceAll(current+"",""));
    }

    public static void main(String[] args) {
        System.out.println(new RemoveDuplicateLetters_316().removeDuplicateLetters("abacb"));
    }

    //6ms 用栈  扫两遍就好   O(n)
    public String removeDuplicateLetters2(String s) {

        Stack<Character> stack = new Stack<>();

        // this lets us keep track of what's in our solution in O(1) time
        HashSet<Character> seen = new HashSet<>();

        // 找末尾
        HashMap<Character, Integer> last_occurrence = new HashMap<>();
        for(int i = 0; i < s.length(); i++) last_occurrence.put(s.charAt(i), i);

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            // 如果在里面就跳过
            if (!seen.contains(c)){
                //如果后面还有栈顶元素那么位置让出来
                while(!stack.isEmpty() && c < stack.peek() && last_occurrence.get(stack.peek()) > i){
                    seen.remove(stack.pop());
                }
                seen.add(c);
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder(stack.size());
        for (Character c : stack) sb.append(c.charValue());
        return sb.toString();
    }

    //用数组代替stack set 和map   3ms
    public String removeDuplicateLetters3(String s) {
        char[] stack = new char[26];
        int top = -1;
        //数组seen记录当前栈中已经存在的字符，如果后续再遇到可以跳过
        boolean[] seen = new boolean[26];
        //last_occurrence 记录字符串中出现过的字符在字符串最后一次出现的位置
        int[] last_occurrence = new int[26];
        char[] cs = s.toCharArray();
        for(int i = 0; i < s.length(); i++)
            last_occurrence[cs[i]-'a'] = i;
        //从左到右扫描字符串
        for(int i = 0; i < s.length(); i++){
            char c = cs[i];
            //若当前字符已经在栈中，无需处理
            if (!seen[c-'a']){
                //如果栈中有元素，且栈顶元素比当前字符小，并且栈顶字符在后续字符串还会出现
                //那么我们可以用当前字符替换栈顶字符得到一个字典序更小的字符串
                //（注意此处将一直与栈顶元素相比，直到栈为空或栈顶字符比当前字符小，或栈顶字符在当前位置之后不会再出现）
                while(top!=-1 && c < stack[top] && last_occurrence[stack[top]-'a'] > i)
                {
                    seen[stack[top--]-'a']=false;
                }

                seen[c-'a'] = true;
                stack[++top]=c;
            }
        }
        StringBuilder ss = new StringBuilder();
        for(int i = 0; i <= top; i++)
            ss.append(stack[i]);
        return ss.toString();
    }
}
