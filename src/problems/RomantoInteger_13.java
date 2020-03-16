package problems;

public class RomantoInteger_13 {

    //my solution
    public int romanToInt(String s) {
        String parts[] = {"I", "VI", "V", "XI", "X", "LX", "L", "CX", "C", "DC", "D", "MC", "M"};
        int nums[] = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        int result = 0;
        String reverse = new StringBuilder(s).reverse().toString();
        for (int i = 0; i < parts.length; i++) {
            while (reverse.startsWith(parts[i])) {
                result += nums[i];
                reverse = reverse.replaceFirst(parts[i], "");
            }
        }
        return result;
    }

    //by hongbin2       much faster
    public int romanToInt2(String s) {
        int sum = 0;
        if (s.contains("IV")) {
            sum -= 2;
        }
        if (s.contains("IX")) {
            sum -= 2;
        }
        if (s.contains("XL")) {
            sum -= 20;
        }
        if (s.contains("XC")) {
            sum -= 20;
        }
        if (s.contains("CD")) {
            sum -= 200;
        }
        if (s.contains("CM")) {
            sum -= 200;
        }

        char c[] = s.toCharArray();
        int count = 0;

        for (; count <= s.length() - 1; count++) {
            if (c[count] == 'M') {
                sum += 1000;
            }
            if (c[count] == 'D') {
                sum += 500;
            }
            if (c[count] == 'C') {
                sum += 100;
            }
            if (c[count] == 'L') {
                sum += 50;
            }
            if (c[count] == 'X') {
                sum += 10;
            }
            if (c[count] == 'V') {
                sum += 5;
            }
            if (c[count] == 'I') {
                sum += 1;
            }

        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new RomantoInteger_13().romanToInt("MCMXCIV"));
    }
}
