package problems;

import java.util.HashMap;
import java.util.Map;

public class P166_FractiontoRecurringDecimal {

    //my solution1 slow 用map寻找循环
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder result = new StringBuilder();

        if (numerator == 0) {
            return "0";
        }

        long numeratorl = numerator;
        long denominatorl = denominator;
        if (numeratorl < 0 && denominatorl > 0) {
            numeratorl = -numeratorl;
            result.append('-');
        }
        if (numeratorl > 0 && denominatorl < 0) {
            denominatorl = -denominatorl;
            result.append('-');
        }
        if (numeratorl < 0 && denominatorl < 0) {
            numeratorl = -numeratorl;
            denominatorl = -denominatorl;
        }
        if (numeratorl < denominatorl) {
            result.append("0.");
            numeratorl *= 10;
        }
        long first = numeratorl;

        Map<Long, String> store = new HashMap<>();
        while (true) {
            if (numeratorl == 0) {
                result.append(store.get(first));
                return result.toString();
            }
            if (store.containsKey(numeratorl)) {
                String a = store.get(first);
                String b = store.get(numeratorl);
                result.append(a.replaceFirst(b, "")).append("(").append(b).append(")");
                return result.toString();
            }

            if (numeratorl < denominatorl) {
                if (result.indexOf(".") < 0) {
                    result.append(".");
                } else {
                    for (Long key : store.keySet()) {
                        store.put(key, store.get(key) + "0");
                    }
                    store.put(numeratorl, "0");
                }
                numeratorl *= 10;
            } else {
                long shang = numeratorl / denominatorl;
                if (result.indexOf(".") < 0) {
                    numeratorl = numeratorl % denominatorl;
                    result.append(shang);
                    if (numeratorl == 0) {
                        return result.toString();
                    }
                    result.append('.');
                    numeratorl *= 10;
                    first = numeratorl;
                } else {
                    for (Long key : store.keySet()) {
                        store.put(key, store.get(key) + shang);
                    }
                    store.put(numeratorl, String.valueOf(shang));
                    numeratorl = numeratorl % denominatorl;
                    numeratorl *= 10;
                }
            }
        }
    }

    public static void main(String[] args) {
        new P166_FractiontoRecurringDecimal().fractionToDecimal2(221453, 10);
    }

    //用快慢步寻找循环 1ms
    public String fractionToDecimal2(int numerator, int denominator) {

        if (numerator == Integer.MIN_VALUE && denominator == -1) {
            return "2147483648";
        }
        int dummy = numerator / denominator, lef = numerator % denominator;
        String str = dummy + "";
        if (dummy == 0) {
            if ((numerator > 0 && denominator < 0) || (numerator < 0 && denominator > 0)) {
                str = "-" + str; // 0 does not have a negative mark in front of it, so we have to add that.
            }
        }
        if (lef == 0) {
            return str; // this indicates the result is an integer.
        }

        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(".");
        long left = lef < 0 ? -lef : lef;
        long denomin = denominator < 0 ? -denominator : denominator;
        if (denominator == Integer.MIN_VALUE) {
            denomin = Integer.MAX_VALUE;
            denomin++; // without this, when the denominator is -214748648, it will cause a overflow.
        }
        long slow = mod(left, denomin), fast = mod(mod(left, denomin), denomin);
        if (slow == 0) { // no need to start the loop.
            extend(left, denomin, sb);
            return sb.toString();
        }

        //类似于链表找环的起点
        while (slow != fast) {
            if (fast == 0) {  // this indicates there are no cycles.
                while (left != 0) {
                    left = extend(left, denomin, sb);
                }
                return sb.toString();
            }
            slow = mod(slow, denomin);
            fast = mod(mod(fast, denomin), denomin);
        }
        slow = left;
        while (slow != fast) {
            slow = mod(slow, denomin);
            fast = mod(fast, denomin);
        }

        //非循环部分
        while (left != slow) {
            left = extend(left, denomin, sb);
        }

        //循环部分
        sb.append("("); // the start of the cycle.
        left = extend(left, denomin, sb);
        while (left != slow) {
            left = extend(left, denomin, sb);
        }
        sb.append(")");
        return sb.toString();
    }

    private long mod(long left, long denominator) {
        left = left * 10;
        return left % denominator;
    }

    private long extend(long left, long denominator, StringBuilder sb) {
        left = left * 10;
        sb.append(left / denominator);
        return left % denominator;
    }
}
