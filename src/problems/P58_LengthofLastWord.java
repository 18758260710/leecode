package problems;

public class P58_LengthofLastWord {
    //my solution1 1ms
    public int lengthOfLastWord(String s) {
        String[] list = s.split(" ");
        if (list.length==0)return 0;
        return list[list.length-1].length();
    }

    //my solution2 0sm
    public int lengthOfLastWord2(String s) {
        boolean empty=true;
        int i = s.length()-1;
        int count=0;

        while (i>=0){
            if (s.charAt(i)!=' '){
                empty=false;
                count++;
            }else if (!empty){
                return count;
            }
            i--;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new P58_LengthofLastWord().lengthOfLastWord2(" "));
        System.out.println("11111".split("11").length);
        System.out.println("111".split("11")[1]);
    }
}
