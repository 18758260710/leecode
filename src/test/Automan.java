package test;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2020/3/13.
 */
public class Automan {
    boolean[][] check;
    public int getWay(int[][] map){
        check = new boolean[map.length][map[0].length];
        int i=0;
        int j=0;
        return help(0,0,map);
    }

    private int help(int i, int j, int[][] map) {
        return 0;
    }

    int[][] tu=new int[][]{{1,2,3,4,5},{11,12,13,14,15},{21,22,23,24,25},{31,32,33,34,35},{41,42,43,44,45}};
    Map<Integer,String> allow=new HashMap<>();
    public boolean get(int[] target){
        int i=0;
        int j=0;
        for (int a=0;a<5;a++){
            for (int b=0;b<5;b++){
                if (tu[a][b]==target[0]){
                    i=a;
                    j=b;
                    break;
                }
            }
        }
        if (i>0)allow.put(tu[i-1][j],(i-1)+"-"+j);
        if (j>0)allow.put(tu[i][j-1],i+"-"+(j-1));
        if (i<4)allow.put(tu[i+1][j],(i+1)+"-"+j);
        if (j<4)allow.put(tu[i][j+1],i+"-"+(j+1));
        boolean[] check = new boolean[6];
        check[0]=true;
        int total=1;
        while (true){
            int count=0;
            for (int k=1;k<6;k++){
                if (!check[k]&&allow.keySet().contains(target[k])){
                    String index = allow.get(target[k]);
                    String[] indexs = index.split("-");
                    i= Integer.parseInt(indexs[0]);
                    j= Integer.parseInt(indexs[1]);
                    check[k]=true;
                    total++;
                    count++;
                    if (i>0)allow.put(tu[i-1][j],(i-1)+"-"+j);
                    if (j>0)allow.put(tu[i][j-1],i+"-"+(j-1));
                    if (i<4)allow.put(tu[i+1][j],(i+1)+"-"+j);
                    if (j<4)allow.put(tu[i][j+1],i+"-"+(j+1));
                }
            }
            if (total==6)return true;
            if (count==0)return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Automan().get(new int[]{1,2,3,4,5,35}));
    }
}
