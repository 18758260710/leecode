package test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wengtao
 * @date 2020/3/27
 **/
public class ItemDTO implements BaseDTO {
    public static void main(String[] args) {
        List<ItemDTO> list = new ArrayList<>();
        List<BaseDTO> a = new ArrayList<>(list);
    }
}
