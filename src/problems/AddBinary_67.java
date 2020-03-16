package problems;

public class AddBinary_67 {
    //my solution 1ms
    public String addBinary(String a, String b) {
        StringBuilder bBuilder = new StringBuilder(b);
        while (a.length()> bBuilder.length()){
            bBuilder.insert(0, "0");
        }
        b = bBuilder.toString();
        StringBuilder aBuilder = new StringBuilder(a);
        while (b.length()> aBuilder.length()){
            aBuilder.insert(0, "0");
        }
        a = aBuilder.toString();

        int up = 0;
        char[] as  = a.toCharArray();
        char[] bs  = b.toCharArray();

        for (int i=bs.length-1;i>=0;i--){
            int temp = as[i]-'0'+bs[i]-'0'+up;
            as[i] = (char) (temp%2+'0');
            up = temp/2;
        }
        if (up>0) {
            return "1"+new String(as);
        }return new String(as);


    }

    public static void main(String[] args) {
        System.out.println(3&~3);
        System.out.println(Integer.toBinaryString(2));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(new AddBinary_67().addBinary("11","1"));
    }
}
