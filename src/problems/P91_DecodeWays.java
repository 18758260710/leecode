package problems;

public class P91_DecodeWays {
    //my solution 1ms
    int[] count;
    public int numDecodings(String s) {
        if (s.length() == 0) {
            return 0;
        }
        count = new int[s.length()+1];
        count[s.length()]=1;
        if (s.charAt(s.length()-1)!='0')count[s.length()-1]=1;

        for (int i=s.length()-2;i>=0;i--){
            if (s.charAt(i)>'0'){
                count[i]+=count[i+1];
                if (s.charAt(i)=='1'||(s.charAt(i)=='2'&&s.charAt(i+1)<='6')){
                    count[i]+=count[i+2];
                }
            }
        }
        return count[0];
    }

    //my solution2 0ms
    public int numDecodings3(String s) {
        if (s.length() == 0 || s.charAt(0)=='0') {
            return 0;
        }
        int nextTwo=1;
        int nextOne=s.charAt(s.length()-1)=='0'?0:1;

        int temp=1;
        for (int i=s.length()-2;i>=0;i--){
            temp=0;
            if (s.charAt(i)>'0'){
                temp+=nextOne;
                if (s.charAt(i)=='1'||(s.charAt(i)=='2'&&s.charAt(i+1)<='6')){
                    temp+=nextTwo;
                }
            }
            nextTwo = nextOne;
            nextOne = temp;
        }
        return temp;
    }

    //只和后两个值相关 不用全存起来 0ms
    public int numDecodings2(String s) {
        if (s.isEmpty() || s.startsWith("0")) {
            return 0;
        }

        int prev1 = 1;
        for (int i = 1, prev2 = 1, left = s.charAt(0) - '0'; i < s.length(); ++i) {
            int right = s.charAt(i) - '0';
            if (right == 0) {
                if (left != 1 && left != 2) {
                    return 0;
                }
                int temp = prev2;
                prev2 = prev1;
                prev1 = temp;
            } else if (left == 1 || left == 2 && right <= 6) {
                prev1 += prev2;
                prev2 = prev1 - prev2;
            } else {
                prev2 = prev1;
            }
            left = right;
        }

        return prev1;
    }



    public static void main(String[] args) {
        System.out.println(new P91_DecodeWays().numDecodings3("226"));
    }
}
