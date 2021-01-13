package problems;

public class P43_MultiplyStrings {
    //my solution 99.86  99.98
    public String multiply(String num1, String num2) {
        if (num1.equals("0")||num2.equals("0"))return "0";
        char[] num1chars = num1.toCharArray();
        char[] num2chars = num2.toCharArray();
        int length1 = num1chars.length;
        int length2 = num2chars.length;
        int[] result = new int[length1 + length2];
        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length2; j++) {
                int current = (length1 - i) + (length2 - j) - 2;
                result[current] += (num1chars[i] - '0') * (num2chars[j] - '0');
                result[current + 1] += result[current] / 10;
                result[current] = result[current] % 10;
            }
        }
        for (int i = 0; i < result.length - 1; i++) {
            result[i + 1] += result[i] / 10;
            result[i] = result[i] % 10;
        }
        StringBuilder stringBuilder = new StringBuilder((result[result.length - 1]>0)?String.valueOf(result[result.length - 1]):"");
        for (int i = result.length - 2; i >= 0; i--) {
            stringBuilder.append(result[i]);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new P43_MultiplyStrings().multiply("123", "456"));
    }
}
