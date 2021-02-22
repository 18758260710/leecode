package problems;

/**
 * @author wengtao
 * @date 2021/2/20
 **/
public class P335_SelfCrossing {
    public boolean isSelfCrossing(int[] x) {
        int len = x.length;
        if(len < 4) {
            return false;
        }
        if((x[2] <= x[0] && x[3] >= x[1]) || (len >= 5 && (x[3] < x[1] && x[4] >= x[2] || x[3] == x[1] && x[0] + x[4] == x[2]))) {
            return true;
        }
        for(int i = 5; i < len; ++ i) {
            /**
             * 必要条件 x[i-1] <= x[i-3]
             * x[i] >= x[i-2]表示  i穿过i-3的情况
             * x[i-2] >= x[i-4] && x[i-5] + x[i-1] >= x[i-3] && x[i] + x[i-4] >= x[i-2]表示 i穿过i-5的情况
             **/
            if(x[i-1] <= x[i-3] && ((x[i] >= x[i-2]) || (x[i-2] >= x[i-4] && x[i-5] + x[i-1] >= x[i-3] && x[i] + x[i-4] >= x[i-2]))){
                return true;
            }
        }
        return false;
    }
}
