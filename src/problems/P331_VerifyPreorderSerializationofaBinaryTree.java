package problems;

/**
 * @author wengtao
 * @date 2021/2/18
 **/
public class P331_VerifyPreorderSerializationofaBinaryTree {
    //从后往前，两个#和一个数字可以替换成一个#，当无法替换或者最后多出#就不是正确的
    public boolean isValidSerialization(String preorder) {
        String[] order = preorder.split(",");
        int length = order.length;
        int count = 0;
        for (int i = length - 1; i >= 0; i--) {
            if ("#".equals(order[i])) {
                count++;
            } else {
                if (count < 2) return false;
                count--;
            }
        }

        return count == 1;

    }

    //用char更快
    public boolean isValidSerialization2(String preorder) {
        int slot = 1;
        char[] chars = preorder.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] == ',') {
                --slot;
                if (slot < 0) {
                    return false;
                }
                if (chars[i - 1] != '#') {
                    slot += 2;
                }
            }
        }
        if (chars[chars.length - 1] == '#') {
            --slot;
        }else {
            ++slot;
        }
        return slot == 0;

    }
}
