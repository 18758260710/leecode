package problems;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author wengtao
 * @date 2020/10/13
 **/
public class BulbSwitcher_319 {
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }

    public static void main(String[] args) {
        Date date = new Date();
        Instant instant = date.toInstant();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("YYYYMMdd")));
    }
}
