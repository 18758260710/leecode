package problems;

public class P66_PlusOne {
    //my solution
    public int[] plusOne(int[] digits) {
        if (digits.length==0)return digits;
        boolean plus = false;
        digits[digits.length-1]++;
        for (int i=digits.length-1;i>=0;i--){
            if (digits[i]>9){
                if (i>0){
                    digits[i-1]++;
                }else plus=true;
                digits[i]=0;
            }else {
                return digits;
            }
        }
        if (plus){
            int[] result = new int[digits.length+1];
            result[0]=1;
            return result;
        }else {
            return digits;
        }
    }

    public static void main(String[] args) {
        int[] a = new P66_PlusOne().plusOne(new int[]{9,8});
        return;
    }

//by diaa more clear
    public int[] plusOne2(int[] digits) {

        int n = digits.length;
        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newNumber = new int [n+1];
        newNumber[0] = 1;

        return newNumber;
    }
}
