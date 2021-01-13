package problems;

public class P8_StringtoIntegerAtoi {

    //my solution
    public int myAtoi(String str) {
        if (str.isEmpty()) {
            return 0;
        }
        str = str.trim();
        boolean hasfuhao = false;
        boolean start = false;
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < str.length(); j++) {
            char i = str.charAt(j);
            if (j == 0 && (i == '-' || i == '+')) {
                stringBuilder.append(i);
                hasfuhao = true;
            } else if (i > '0' && i <= '9') {
                stringBuilder.append(i);
                start = true;
            } else if(i=='0'&&start) {
                stringBuilder.append(i);
            } else if (i=='0'){
                continue;
            }else break;
        }
        if (stringBuilder.length() > 11) {
            if (str.startsWith("-")) {
                return Integer.MIN_VALUE;
            }
            return Integer.MAX_VALUE;
        }
        if (stringBuilder.length() > 1 && hasfuhao || stringBuilder.length() > 0 && !hasfuhao) {
            String result = stringBuilder.toString();
            long resultLong = Long.parseLong(result);
            if (resultLong > Integer.MAX_VALUE) {
                resultLong = Integer.MAX_VALUE;
            }
            if (resultLong < Integer.MIN_VALUE) {
                resultLong = Integer.MIN_VALUE;
            }
            return (int) resultLong;
        }
        return 0;
    }


    /**
     solution by yuruofeifei

     1.discards all leading whitespaces
     2.sign of the number
     3.overflow
     4.invalid input
     */
    public int myAtoi2(String str) {
        if (str.isEmpty()) {
            return 0;
        }
        int sign = 1, base = 0, i = 0;
        while (i<str.length()&&str.charAt(i) == ' ') { i++; }
        if (i<str.length()&&(str.charAt(i) == '-' || str.charAt(i) == '+')) {
            sign =(str.charAt(i++) == '-')?-1:1;
        }
        while (i<str.length()&&str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            if (base >  Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > 7)) {
                if (sign == 1) return Integer.MAX_VALUE;
                else return Integer.MIN_VALUE;
            }
            base  = 10 * base + (str.charAt(i++) - '0');
        }
        return base * sign;
    }
}
