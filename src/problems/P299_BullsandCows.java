package problems;

/**
 * @author wengtao
 * @date 2020/3/27
 **/
public class P299_BullsandCows {
    //10ms
    int[] counts = new int[10];
    int A = 0;
    int B = 0;

    public String getHint(String secret, String guess) {
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) A++;
            else {
                counts[secret.charAt(i) - '0']++;
                counts[guess.charAt(i) - '0']--;
            }
        }
        int notmatch = 0;
        for (int count:counts){
            if (count>0)notmatch+=count;
        }
        B = secret.length()-notmatch-A;
        return A+"A"+B+"B";
    }

    //3ms
    public String getHint2(String secret, String guess) {
        StringBuilder sb = new StringBuilder();
        int a = 0, b = 0;
        int[] s = new int[10];
        int[] g = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            s[secret.charAt(i) - '0']++;
            g[guess.charAt(i) - '0']++;
            a += secret.charAt(i) == guess.charAt(i) ? 1 : 0;
        }
        for (int i = 0; i < s.length; i++)
            b += Math.min(s[i], g[i]);
        return sb.append(a).append("A").append(b - a).append("B").toString();
    }

    public static void main(String[] args) {
        System.out.println(new P299_BullsandCows().getHint("1804","7810"));
    }
}
