package problems;

/**
 * Created by Administrator on 2020/3/4.
 */
public class P393_UTF8Validation {
    //1ms
    public boolean validUtf8(int[] data) {
        int len = data.length;
        if(len == 0){
            return true;
        }
        for(int i=0;i<len; ){
            if((data[i]&128) == 0){
                i++;
            }else if((data[i]&224) == 192){
                if( (i+1) < len && (data[i+1]&192) == 128){
                    i+=2;
                }else{
                    return false;
                }
            }else if((data[i]&240) == 224){
                if( (i+2)<len && (data[i+1]&192) == 128 && (data[i+2]&192) == 128){
                    i+=3;
                }else{
                    return false;
                }
            }else if((data[i]&248) == 240){
                if((i+3)<len && (data[i+1]&192) == 128 && (data[i+2]&192) == 128 && (data[i+3]&192) == 128){
                    i+=4;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new P393_UTF8Validation().validUtf8(new int[]{197,130,1}));
    }
}
