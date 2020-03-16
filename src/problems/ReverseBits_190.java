package problems;

public class ReverseBits_190 {
    //my solution1 3ms slow
    public int reverseBits(int n) {
        String ni = Integer.toBinaryString(n);
        StringBuilder stringBuilder = new StringBuilder(ni).reverse();
        int remain = 32-ni.length();
        while (remain>0){
            stringBuilder.append(0);
            remain--;
        }
        String bi = stringBuilder.toString();
        return Integer.parseUnsignedInt(bi,2);
    }

    //my solution2 1ms
    public int reverseBit2(int n) {
        int result = 0;
        for (int i=0;i<32;i++){
            result <<= 1;
            result |= n&1;
            n = n >>> 1;
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(Integer.parseInt("00000010100101000001111010011100",2));
        System.out.println(Integer.toBinaryString(43261596));
        System.out.println(new ReverseBits_190().reverseBit2(43261596));
        System.out.println(964176192*2);
    }
}
