package problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2020/1/10.
 */
public class IsomorphicStrings_205 {
    //solution1 slow
    public boolean isIsomorphic(String s, String t) {
        for (int i=0;i<s.length();i++){
            char scurrent = s.charAt(i);
            char tcurrent = t.charAt(i);
            int j1=i;
            int j2=i;
            while (true){
                j1 = s.indexOf(scurrent,j1+1);
                j2 = t.indexOf(tcurrent,j2+1);
                if (j1!=j2)return false;
                if (j1==-1)break;
            }
        }
        return true;
    }

    //slow
    public boolean isIsomorphic2(String s, String t) {
        Map<Character,Set<Integer>> smap = new HashMap<>();
        Map<Character,Set<Integer>> tmap = new HashMap<>();
        for (int i=0;i<s.length();i++){
            char scurrent = s.charAt(i);
            char tcurrent = t.charAt(i);
            if (smap.containsKey(scurrent)){
                smap.get(scurrent).add(i);
            }else {
                Set<Integer> sset = new HashSet<>();
                sset.add(i);
                smap.put(scurrent,sset);
            }
            if (tmap.containsKey(tcurrent)){
                tmap.get(tcurrent).add(i);
            }else {
                Set<Integer> tset = new HashSet<>();
                tset.add(i);
                tmap.put(tcurrent,tset);
            }
            if (!smap.get(scurrent).equals(tmap.get(tcurrent)))return false;
        }
        return true;
    }

    //5ms 相同为一组，查询组头是否一致就可以
    public boolean isIsomorphic3(String s, String t) {
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if(s.indexOf(ch1[i]) != t.indexOf(ch2[i])){
                return false;
            }
        }
        return true;
    }

    //1ms 相互配对 egg和add为例 e对应a g对应d s2t['g']='d'
    public boolean isIsomorphic4(String s, String t) {
        char[] s2t = new char[127];
        char[] t2s = new char[127];
        char[] S = s.toCharArray();
        char[] T = t.toCharArray();

        int len = s.length();
        for(int i =0;i<len;i++){
            if(s2t[S[i]]!='\0' || t2s[T[i]]!='\0'){
                if(s2t[S[i]]!=T[i]){
                    return false;
                }
            }else{
                s2t[S[i]]=T[i];
                t2s[T[i]]=S[i];
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new IsomorphicStrings_205().isIsomorphic4("egg","add"));
    }
}
