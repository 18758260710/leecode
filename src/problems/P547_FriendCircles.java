package problems;

/**
 * Created by Administrator on 2020/3/4.
 */
public class P547_FriendCircles {
    //mysolution 1ms
    public int findCircleNum(int[][] M) {
        int num = M.length;
        if (num==0)return 0;
        int result=0;
        boolean[] check = new boolean[num];
        for (int i=0;i<num;i++){
            if (!check[i])result++;
            doCheck(M,i,check);
        }
        return result;
    }

    private void doCheck(int[][] m, int i,boolean[] check) {
        if (check[i])return;
        check[i]=true;
        for (int j=0;j<m.length;j++){
            if (m[i][j]==1)doCheck(m,j,check);
        }
    }
}
