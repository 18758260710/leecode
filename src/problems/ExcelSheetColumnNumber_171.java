package problems;

public class ExcelSheetColumnNumber_171 {
    //my solution1 1ms
    public int titleToNumber(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        int current = 1;
        for (int i=chars.length-1;i>=0;i--){
            result+=current*(chars[i]-64);
            current*=26;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new ExcelSheetColumnNumber_171().titleToNumber("AZ"));
    }
}
