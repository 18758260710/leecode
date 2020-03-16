package problems;

/**
 * Created by Administrator on 2020/2/12.
 */
public class IntegertoEnglishWords_273 {
    //没啥意思的题
    String[] n1={"","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
    String[] n2={"Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
    String[] n3={"","","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
    String help(int n){
        String s;
        if(n<10)
            s=n1[n];
        else if(n<20)
            s=n2[n-10];
        else if(n<100)
            s=n3[n/10]+" "+n1[n%10];
        else if(n<1000)
            s=help(n/100)+" Hundred "+help(n%100);
        else if(n<1000000)
            s=help(n/1000)+" Thousand "+help(n%1000);
        else if(n<1000000000)
            s=help(n/1000000)+" Million "+help(n%1000000);
        else
            s=help(n/1000000000)+" Billion "+help(n%1000000000);

        return s.trim();
    }
    public String numberToWords(int num) {
        return num==0?"Zero":help(num);
    }

    public String numberToWords2(int num) {
        String[] a1={"Billion","Million","Thousand",""};
        int[] n1={1000000000,1000000,1000,1};
        int[] n2={900,800,700,600,500,400,300,200,100,90,80,70,60,50,40,30,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0};
        String[] a2= {"Nine Hundred","Eight Hundred","Seven Hundred","Six Hundred","Five Hundred", "Four Hundred","Three Hundred","Two Hundred","One Hundred", "Ninety","Eighty","Seventy","Sixty","Fifty","Forty","Thirty","Twenty","Nineteen","Eighteen","Seventeen","Sixteen","Fifteen","Fourteen","Thirteen","Twelve","Eleven","Ten","Nine","Eight","Seven","Six","Five","Four","Three", "Two","One","Zero"};
        if(num==0){
            return "Zero";
        }
        StringBuilder str=new StringBuilder();
        for(int i=0;i<n1.length;i++){
            if(num>=n1[i]){
                int t=num/n1[i];
                if(t>0){
                    int t1=t%1000;
                    for(int j=0;j<n2.length && t1>0;j++){
                        if(t1>=n2[j]){
                            str.append(a2[j]).append(" ");
                            t1=t1-n2[j];
                        }
                    }
                    str.append(a1[i]).append(" ");
                    num-=t*n1[i];
                }
            }
        }
        return str.toString().trim();
    }
}
