package problems;


public class ReverseWordsinaString_151 {
    //my solution1 3ms
    public String reverseWords(String s) {
        s = s.trim();
        String[] res = s.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i=0;i<res.length;i++){
            if (!res[i].isEmpty()){
                result.insert(0, res[i] + " ");
            }
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(new ReverseWordsinaString_151().reverseWords("a good   example"));
    }

    //1ms
    public String reverseWords2(String s) {

        StringBuilder sb = new StringBuilder();
        int i = s.length()-1;
        while(i>=0){
            if(s.charAt(i) ==' '){
                i--;
                continue;
            }
            int start = s.lastIndexOf(' ',i);
            sb.append(' ');
            sb.append(s.substring(start+1,i+1));
            i = start -1 ;
        }
        if(sb.length()>0){
            sb.deleteCharAt(0);
        }
        return sb.toString();

    }
}
