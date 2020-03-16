package test;

import java.util.*;

/**
 * Created by Administrator on 2020/3/14.
 */
public class Template {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> rows = new ArrayList<>();
        int num = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.contains(",")) rows.add(line);
            else {
                num = Integer.parseInt(line);
                break;
            }
        }
        int[] result = new int[num];
        for (String row:rows){
            String[] ids = row.split(",");
            int a = Integer.parseInt(ids[0]);
            int b = Integer.parseInt(ids[1]);
            int c = Integer.parseInt(ids[2]);
            for (int i=a-1;i<b;i++){
                result[i]+=c;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int count:result){
            stringBuilder.append(count);
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        System.out.println(stringBuilder.toString());
    }
}
