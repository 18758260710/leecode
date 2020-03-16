package problems;

public class WildcardMatching_44 {

    //my solution dp 时间太长 bad 17ms
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;
        for (int j = p.length() - 1; j >= 0; j--) {
            for (int i = s.length(); i >= 0; i--) {
                boolean first_match = (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?'));
                if (p.charAt(j) == '*') {
                    dp[i][j] = dp[i][j + 1];
                    if (dp[i][j]) {
                        while (i > 0) {
                            i--;
                            dp[i][j] = dp[i + 1][j];
                        }
                    }
                } else {
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    //by leetcode_deleted_user dp bit quicker than me   not good  12ms
    public boolean isMatch2(String s, String p) {
        boolean[][] match=new boolean[s.length()+1][p.length()+1];
        match[s.length()][p.length()]=true;
        for(int i=p.length()-1;i>=0;i--){
            if(p.charAt(i)!='*')
                break;
            else
                match[s.length()][i]=true;
        }
        for(int i=s.length()-1;i>=0;i--){
            for(int j=p.length()-1;j>=0;j--){
                if(s.charAt(i)==p.charAt(j)||p.charAt(j)=='?')
                    match[i][j]=match[i+1][j+1];
                else if(p.charAt(j)=='*')
                    match[i][j]=match[i+1][j]||match[i][j+1];
                else
                    match[i][j]=false;
            }
        }
        return match[0][0];
    }

    //best  2ms
    public boolean isMatch3(String s, String p) {
        int sp=0,pp=0,star=-1,match=0;
        while(sp<s.length()){
            //正常匹配
            if(pp<p.length()&&(s.charAt(sp)==p.charAt(pp)||p.charAt(pp)=='?')){
                sp++;
                pp++;
            }
            //*匹配尽可能少的字符
            else if(pp<p.length()&&p.charAt(pp)=='*'){
                star=pp;
                match=sp;
                pp++;
            //匹配不上返回最近的*,*多match一个字符再重试
            }else if(star!=-1){
                pp=star+1;
                match++;
                sp=match;
            }else{
                return false;
            }
        }
        //剩下全是*
        for(int i=pp;i<p.length();i++){
            if(p.charAt(i)=='*'){
                pp++;
            }
        }

        return pp==p.length();
    }

    public static void main(String[] args) {
//        System.out.println("**asd***213".replaceAll("\\*{2,}","*"));
        System.out.println(new WildcardMatching_44().isMatch3("abababab", "a*ab*ab"));
    }
}
