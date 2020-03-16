package problems;

public class UniquePaths_62 {
    //my solution 0ms math way
    public int uniquePaths(int m, int n) {
        if (m<=0||n<=0)return 0;
        m--;
        n--;
        int a = m+n;
        if (m>n){
            m = m + n;
            n = m - n;
            m = m - n;
        }
        long result = 1;
        int temp = m;
        while (temp>0){
            result*=a;
            result/=(m-temp+1);
            temp--;
            a--;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(new UniquePaths_62().uniquePaths(23,12));
    }
}
