package problems;

/**
 * @author wengtao
 * @date 2021/2/27
 **/
public class P344_ReverseString {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length-1;
        while (left < right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left ++;
            right --;
        }
    }
}
