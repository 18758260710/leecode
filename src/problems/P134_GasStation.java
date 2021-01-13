package problems;

public class P134_GasStation {
    //my solution1
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int length = gas.length;
        if (length == 0) {
            return -1;
        }
        int start = 0;
        while (start < length) {
            while (gas[start] < cost[start]) {
                start++;
                if (start>=length)return -1;
            }
            int temp = start;
            int tank = 0;
            while (true) {
                tank += gas[temp] - cost[temp];
                if (tank < 0) {
                    break;
                }
                temp++;
                if (temp == length) {
                    temp = 0;
                }
                if (temp == start) {
                    return start;
                }
            }
            if (temp <= start) {
                return -1;
            }
            start = temp;
        }
        return -1;
    }

    public static void main(String[] args) {
        int a = new P134_GasStation().canCompleteCircuit2(new int[]{4}, new int[]{5});
        System.out.println(a);
    }

    //by daxianji007
    public int canCompleteCircuit2(int[] gas, int[] cost){
        int start=0;
        int total=0;
        int tank=0;
        //if car fails at 'start', record the next station
        for(int i=0;i<gas.length;i++) {
            if((tank=tank+gas[i]-cost[i])<0) {
                start=i+1;
                total+=tank;
                tank=0;
            }
        }
        return (total+tank<0)? -1:start;
    }
}
