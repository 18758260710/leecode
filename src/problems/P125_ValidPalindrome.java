package problems;

public class P125_ValidPalindrome {
    //my solution1 4ms
    public boolean isPalindrome(String s) {
        if (s.length()<2)return true;
        s = s.toLowerCase();//这步耗时较多
        int length = s.length();
        int left = 0;
        int right = length-1;
        while (true){
            while (!valid(s.charAt(left))){
                left++;
                if (left==length)break;
            }
            while (!valid(s.charAt(right))){
                right--;
                if (right==-1)break;
            }
            if (left>=right)break;
            if (s.charAt(left)!=s.charAt(right))return false;
            left++;
            right--;
        }
        return true;
    }
    public boolean valid(char a){
        return (a>='a'&&a<='z')||(a>='0'&&a<='9');
    }

    public static void main(String[] args) {
        System.out.println(new P125_ValidPalindrome().isPalindrome("0P"));
    }

    //my solution2 2ms
    public boolean isPalindrome2(String s) {
        if (s.length()<2)return true;
        int length = s.length();
        int left = 0;
        int right = length-1;
        while (left<right){
            char lc = s.charAt(left);
            char rc = s.charAt(right);
            if (!valid2(lc)){
                left++;
                continue;
            }
            if (!valid2(rc)){
                right--;
                continue;
            }
            if (lc>='A'&&lc<='Z')lc+=32;
            if (rc>='A'&&rc<='Z')rc+=32;
            if (lc!=rc)return false;
            left++;
            right--;
        }
        return true;
    }

    public boolean valid2(char a){
        return (a>='a'&&a<='z')||(a>='0'&&a<='9')||(a>='A'&&a<='Z');
    }

    //1ms 有效字符鉴别优化
    private static final char[]charMap = new char[256];
    static{
        for(int i=0;i<10;i++){
            charMap[i+'0'] = (char)(1+i);  // numeric
        }
        for(int i=0;i<26;i++){
            charMap[i+'a'] = charMap[i+'A'] = (char)(11+i);  //alphabetic, ignore cases
        }
    }
    public boolean isPalindrome3(String s) {
        char[] cs = s.toCharArray();
        int l=0,r = cs.length-1;

        while(l<r){
            char cl = charMap[cs[l]];
            char cr = charMap[cs[r]];
            if(cl!=0 && cr!=0){
                if(cl==cr){
                    l++;
                    r--;
                }else{
                    return false;
                }
            }else{
                if(cl==0) l++;
                if(cr==0) r--;
            }
        }
        return true;
    }
}
