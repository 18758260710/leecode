package problems;

/**
 * Created by Administrator on 2020/1/9.
 */
public class CountPrimes_204 {
    //solution1 26ms
    public int countPrimes(int n) {
        if (n<=2)return 0;
        int[] a= new int[n+1];
        int count=0;

        for (int i=2;i<n;i++){
            int plus = 2;
            if (a[i]==0){
                while (i*plus<=n){
                    a[i*plus] = 1;
                    plus++;
                }
                count++;
            }
        }
        return count;
    }
    //solution2 16ms
    public int countPrimes2(int n) {
        if (n<=2)return 0;
        boolean[] a= new boolean[n+1];
        int count=0;

        for (int i=2;i<=(int) (Math.sqrt(n));i++){
            if (!a[i]){
                for (int j=i*i;j<n+1;j+=i){
                    a[j] = true;
                }

                count++;
            }
        }
        for (int i = (int) (Math.sqrt(n))+1; i<n; i++){
            if (!a[i]){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new CountPrimes_204().countPrimes(499979));
    }

    //从后往前 不是的减一
    public int countPrimes3(int n) {
        if (n < 3)
            return 0;

        boolean[] f = new boolean[n];
        //Arrays.fill(f, true); boolean[] are initialed as false by default
        int count = n / 2;
        for (int i = 3; i * i < n; i += 2) {
            if (f[i])
                continue;

            for (int j = i * i; j < n; j += 2 * i) {
                if (!f[j]) {
                    --count;
                    f[j] = true;
                }
            }
        }
        return count;
    }
}
