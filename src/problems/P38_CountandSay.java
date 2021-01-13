package problems;

public class P38_CountandSay {
    //my solution bad question
    public String countAndSay(int n) {
        String start = "1";
        for (int i=1;i<n;i++){
            char[] array = start.toCharArray();
            StringBuilder sb= new StringBuilder();
            char startchar = array[0];
            int count=1;
            for (int j=1;j<array.length;j++){
                if (startchar==array[j]){
                    count++;
                }else {
                    sb.append(count);
                    sb.append(startchar);
                    startchar=array[j];
                    count=1;
                }
            }
            sb.append(count);
            sb.append(startchar);
            start = sb.toString();
        }
        return start;
    }

    public static void main(String[] args) {
        new P38_CountandSay().countAndSay(30);
    }
}
