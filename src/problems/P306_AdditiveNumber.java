package problems;

/**
 * @author wengtao
 * @date 2020/3/31
 **/
public class P306_AdditiveNumber {
    //2ms
    public boolean isAdditiveNumber(String num) {
        int length = num.length();
        if (length < 3) return false;

        for (int first = 1; first <= length / 2; first++) {
            for (int second = 1; length - first - second >= Math.max(first, second); second++) {
                if (docheck(0, first, second, num)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean docheck(int start, int first, int second, String num) {
        if (start+first+second+second>num.length())return false;
        if (num.charAt(start)=='0'&&first>1)return false;
        if (num.charAt(start+first)=='0'&&second>1)return false;
        String next = bigAdd(num.substring(start,start+first),num.substring(start+first,start+first+second));
        String substring = num.substring(start + first + second);
        if (substring.equals(next))return true;
        if (substring.startsWith(next)){
            return docheck(start+first,second,next.length(),num);
        }
        return false;
    }

    String bigAdd(String a, String b) {
        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();
        if (as.length < bs.length) {
            char[] temp = as;
            as = bs;
            bs = temp;
        }
        int alength = as.length;
        int blength = bs.length;
        for (int i = 1; i < alength; i++) {
            if (blength - i >= 0) {
                as[alength - i] += bs[blength - i] - '0';
            }
            if (as[alength - i] > '9') {
                as[alength - i - 1]++;
                as[alength - i] -= 10;
            }
        }
        if (alength == blength) {
            as[0] += bs[0] - '0';
        }
        if (as[0] > '9') {
            as[0] -= 10;
            return '1' + new String(as);
        }
        return new String(as);
    }
    String bigAdd2(String a, String b) {
        return String.valueOf(Long.parseLong(a)+Long.parseLong(b));
    }

    public static void main(String[] args) {
        System.out.println(new P306_AdditiveNumber().isAdditiveNumber("199001200"));
    }

    //回溯  减少substring开销
    public boolean isAdditiveNumber2(String num) {
        return backtrack(num,0,0,0,0);
        //第一个0：从num的0号位置开始计算
        //第二个0：前面两个数的总和初始为0
        //第三个0：前面一个数的值初始为0
        //第四个0：当前搜索的数字是第几个数（需要先找到前两个再对第三个及以后进行分析，所以需要这个k）
    }
    private boolean backtrack(String num,int index,long presum,long prenum,int k)
    {
        if(k>2&&index>=num.length()) return true;//index是当前数字的开始处
        for(int len=1;len+index<=num.length();len++) {//len是当前数字的长度
            long f=isSum(presum,num,index,index+len-1,k) ;//presum是之前二者的和，（num index，index+len-1）是当前数字
            if(f>=0){
                if (backtrack(num,index+len,f+prenum,f,k+1)) return true;
                //f>0时表示当前匹配成功进入递归，此时f变成prenum，f+prenum变成当前数的前两个数字之和（一开始的prenum就是f的前一个数）
            }
        }
        return false;
    }
    private long isSum(long sum,String num,int l,int h,int k) {
        if(num.charAt(l)=='0'&&l<h) return -1;
        long s=0;
        while(l<=h)
        {
            s=s*10+num.charAt(l)-'0';
            l++;
        }//num的从l到h的这一段字符串对应的数值
        if(k<2) return s;//前两个数直接返回isSum=1的情况
        return sum==s?s:-1;//后面就需判断二者和是否为sum（之前两个数二者的和）
    }
}
