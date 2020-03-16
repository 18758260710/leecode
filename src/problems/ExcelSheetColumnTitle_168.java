package problems;

public class ExcelSheetColumnTitle_168 {
    //my solution1 0ms
    String array = "0ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while (n>26){
            int a = n%26;
            if (a>0) {
                result.append(array.charAt(a));
                n = n / 26;
            }else {
                result.append('Z');
                n = n / 26 - 1;
            }
        }
        result.append(array.charAt(n));
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new ExcelSheetColumnTitle_168().convertToTitle(701));
    }
}
