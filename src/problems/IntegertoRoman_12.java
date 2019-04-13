package problems;

public class IntegertoRoman_12 {

    //my solution
    public String intToRoman(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        int M = num / 1000;
        num = num % 1000;
        int D = num / 500;
        num = num % 500;
        int C = num / 100;
        num = num % 100;
        int L = num / 50;
        num = num % 50;
        int X = num / 10;
        num = num % 10;
        int V = num / 5;
        int I = num % 5;

        if (I > 3) {
            if (V > 0) {
                stringBuilder.append("XI");
            } else {
                stringBuilder.append("VI");
            }
        } else {
            for (int i = 0; i < I; i++) {
                stringBuilder.append("I");
            }
            if (V > 0) {
                stringBuilder.append("V");
            }
        }
        if (X > 3) {
            if (L > 0) {
                stringBuilder.append("CX");
            } else {
                stringBuilder.append("LX");
            }
        } else {
            for (int i = 0; i < X; i++) {
                stringBuilder.append("X");
            }
            if (L > 0) {
                stringBuilder.append("L");
            }
        }
        if (C > 3) {
            if (D > 0) {
                stringBuilder.append("MC");
            } else {
                stringBuilder.append("DC");
            }
        } else {
            for (int i = 0; i < C; i++) {
                stringBuilder.append("C");
            }
            if (D > 0) {
                stringBuilder.append("D");
            }
        }
        for (int i = 0; i < M; i++) {
            stringBuilder.append("M");
        }
        return stringBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new IntegertoRoman_12().intToRoman2(9));
    }

    //by fabrizio3 骚套路
    public String intToRoman2(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }

    //by chipbk10
    public String intToRoman3(int num) {
        StringBuilder res = new StringBuilder();
        String s = "MCMDCDCXCLXLXIXVIVI";
        int[] ns = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int i = 0, j = 0, step = 1;
        while (num > 0) {
            if (num - ns[i] >= 0) {
                num -= ns[i];
                res.append(s.charAt(j));
                if (step == 2) {
                    res.append(s.charAt(j + 1));
                }
            } else {
                i++;
                j += step;
                step = (step == 1) ? 2 : 1;
            }
        }

        return res.toString();
    }
}
