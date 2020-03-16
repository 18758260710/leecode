package problems;

public class DungeonGame_174 {
    //my solution1 2ms
    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] health = new int[n][m];
        if (dungeon[n-1][m-1]>0){
            health[n-1][m-1]=1;
        }else {
            health[n-1][m-1] = -dungeon[n-1][m-1]+1;
        }

        for (int i=n-2;i>=0;i--){
            int min = health[i + 1][m-1] - dungeon[i][m-1];
            health[i][m-1] = Math.max(min, 1);
        }
        for (int i=m-2;i>=0;i--){
            int min = health[n-1][i+1] - dungeon[n-1][i];
            health[n-1][i] = Math.max(min, 1);
        }
        for (int i=n-2;i>=0;i--){
            for (int j=m-2;j>=0;j--){
                int min = Math.max(Math.min(health[i+1][j],health[i][j+1]),1)-dungeon[i][j];

                health[i][j] = Math.max(min,1);
            }
        }
        return health[0][0];
    }

    public static void main(String[] args) {
        DungeonGame_174 a = new DungeonGame_174();
        a.calculateMinimumHP3(new int[][]{{-2,-3,3},{-5,-10,1},{10,30,-5}});
        System.out.println(a.count);
    }

    //my solution2 1ms
    public int calculateMinimumHP2(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;
        int[] health = new int[m];
        if (dungeon[n-1][m-1]>0){
            health[m-1]=1;
        }else {
            health[m-1] = -dungeon[n-1][m-1]+1;
        }

        for (int i=m-2;i>=0;i--){
            int min = health[i+1] - dungeon[n-1][i];
            health[i] = Math.max(min, 1);
        }
        for (int i=n-2;i>=0;i--){
            for (int j=m-1;j>=0;j--){
                if (j==m-1){
                    health[j] = Math.max(health[j] - dungeon[i][j], 1);
                }else {
                    int min = Math.min(health[j], health[j + 1]) - dungeon[i][j];

                    health[j] = Math.max(min, 1);
                }
            }
        }
        return health[0];
    }

    //递归 0ms
    int count=0;
    public int calculateMinimumHP3(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0)
            return 0;

        int m = dungeon.length;
        int n = dungeon[0].length;
        int dp[][] = new int[m][n];

        //when you are at princes . m-1,n-1
        dp[m - 1][n - 1] = dungeon[m - 1][n - 1] >= 0 ? 1 : -dungeon[m - 1][n - 1] + 1;

        return calculateMinimumHP(dungeon, 0, 0, dp);


    }

    private int calculateMinimumHP(int[][] dungeon, int i, int j, int[][] dp) {

        if (i >= dungeon.length || j >= dungeon[0].length)
            return Integer.MAX_VALUE;

        if (dp[i][j] != 0)
            return dp[i][j];

        count++;
        int down = calculateMinimumHP(dungeon, i + 1, j, dp);
        int right = calculateMinimumHP(dungeon, i, j + 1, dp);

        int min = Math.min(down, right);


        final int hp = min - dungeon[i][j];

        dp[i][j] = hp <= 0 ? 1 : hp;


        return dp[i][j];
    }
}
