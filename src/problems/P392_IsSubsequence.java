package problems;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author wengtao
 * @date 2020/11/21
 **/
public class P392_IsSubsequence {
    //1ms
    public boolean isSubsequence(String s, String t) {
        int i = 0,j = 0;
        if (s.isEmpty())return true;
        if (t.isEmpty())return false;
        while (i < s.length()){
            if (j == t.length())return false;
            while (s.charAt(i) != t.charAt(j)){
                j++;
                if (j == t.length())return false;
            }
            i++;
            j++;
        }
        return true;
    }

    //0ms substring
    public boolean isSubsequence2(String s, String t) {
        if(s.length() > t.length() || t == null) {
            return false;
        }
        if("".equals(s)) {
            return true;
        }
        int tmp = 0;
        int [] a = new int [s.length()];
        for(int i =0;i<s.length();i++) {
            int j = t.indexOf(s.charAt(i));
            if(j == -1) {
                return false;
            }else {
                a[i]=tmp + j;
                if(i>0) {
                    if(a[i-1] > a[i]) {
                        return false;
                    }
                }
            }
            tmp = tmp + j+1;
            t = t.substring(j+1);
        }
        return true;
    }

    /**
     * 获取年
     *
     * @return 年
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月份
     *
     * @return 月份
     */
    public static int getMonth(Date date) {
        LocalDateTime nowTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return nowTime.getMonth().getValue();
    }

    public static int getYearMonthInt(Date date) {
        int year = getYear(date);
        int month = getMonth(date);
        return year*100+month;
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.MONTH, 2);
        System.out.println(calendar.get(Calendar.MONTH));
        calendar.setTimeZone(TimeZone.getDefault());
        Date date = calendar.getTime();
        System.out.println(date);
        System.out.println(getYearMonthInt(date));
//        new IsSubsequence_392().isSubsequence("acb", "ahbgdc");
    }
}
