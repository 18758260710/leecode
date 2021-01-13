package problems;

import java.util.Stack;

public class P71_SimplifyPath {

    //my solution 4ms
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] split = path.split("/");
        for (String word : split) {
            if (word.equals("..")) {
                if (!stack.isEmpty())
                stack.pop();
            } else if (!word.equals(".") && !word.isEmpty()) {
                stack.push(word);
            }
        }
        if (stack.isEmpty())return "/";
        StringBuilder stringBuilder = new StringBuilder();
        for (String word:stack){
            stringBuilder.append("/");
            stringBuilder.append(word);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new P71_SimplifyPath().simplifyPath2("/a//b////c/d//././/.."));
    }

    //1ms
    public String simplifyPath2(String path) {
        if(path==null||path.length()==0) return "/";
        char[] ch=path.toCharArray();
        int i=0;
        int j=0;
        int len=path.length();
        while(j<len){
            if(ch[j]=='/'){
                if(i==0||ch[i-1]!='/')
                    ch[i++]=ch[j];
            }
            else if(ch[j]=='.'){
                if (j+1 < len && ch[j+1] != '/') {
                    if(ch[j+1]=='.'&&(j+2>=len||ch[j+2]=='/')&&(i>0&&ch[i-1]=='/')){
                        i--;
                        while(i>0&&ch[i-1]!='/')
                            i--;
                    }
                    else{
                        while(j<len&&ch[j]!='/')
                            ch[i++]=ch[j++];
                        j--;
                    }
                }
            }
            else{
                while(j<len&&ch[j]!='/')
                    ch[i++]=ch[j++];
                j--;
            }
            j++;
        }
        if(i>0&&ch[i-1]=='/') i--;
        return i==0?"/":new String(ch,0,i);
    }
}
