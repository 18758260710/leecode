package problems;

import java.util.ArrayList;
import java.util.List;

public class P93_RestoreIPAddresses {
    //my solution1 1ms
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        explore(s,4,result,"");
        return result;
    }

    private void explore(String s, int num, List<String> result,String temp) {
        if (s.length()<num||s.length()>3* num)return;
        if (num==1){
            if (s.charAt(0)=='0'&&s.length()>1)return;
            if (Integer.parseInt(s)<256)result.add(temp+s);
        }else {
            if (s.length()>1){
                explore(s.substring(1),num-1,result,temp+s.substring(0,1)+".");
                if (s.charAt(0)=='0')return;
            }
            if (s.length()>2){
                explore(s.substring(2),num-1,result,temp+s.substring(0,2)+".");
            }
            if (s.length()>3&&Integer.parseInt(s.substring(0,3))<256){
                explore(s.substring(3),num-1,result,temp+s.substring(0,3)+".");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new P93_RestoreIPAddresses().restoreIpAddresses("010010"));
    }

    //more easy
    public List<String> restoreIpAddresses2(String s) {
        List<String> result = new ArrayList<>();
        doRestore(result, "", s, 0);
        return result;
    }

    private void doRestore(List<String> result, String path, String s, int k) {
        if (s.isEmpty() || k == 4) {
            if (s.isEmpty() && k == 4)
                result.add(path.substring(1));
            return;
        }
        for (int i = 1; i <= (s.charAt(0) == '0' ? 1 : 3) && i <= s.length(); i++) { // Avoid leading 0
            String part = s.substring(0, i);
            if (Integer.valueOf(part) <= 255)
                doRestore(result, path + "." + part, s.substring(i), k + 1);
        }
    }
}
