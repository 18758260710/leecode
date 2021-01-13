package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wengtao
 * @date 2020/3/27
 **/
public class JsonPath {
    public static void main(String[] args) {
        String a = "{\"item_id_list\":[5221680,4081454,2531716,5633702,4430768,4638895,3638588,2755648],\"applets_link_url\":\"pages/module/web/webpage?src=https%3A%2F%2Fact.mockuai.com%2Fmkcp%2F4160.html\",\"app_link_url\":\"https://act.mockuai.com/mkcp/4160.html\",\"img_url\":\"https://mktv-in.oss-cn-hangzhou.aliyuncs.com/15769083237647073.gif\",\"shop_id\":null,\"skip_type\":0}";
        String b = "[{\"id\":\"1585390146316488\",\"item_id\":347575,\"recommendation\":\"1\"},{\"id\":\"158539014631677\",\"item_id\":347568,\"recommendation\":\"2\"},{\"id\":\"158539014631656\",\"item_id\":347559,\"recommendation\":\"34\"},{\"id\":\"158539014631663\",\"item_id\":347555,\"recommendation\":\"4\"},{\"id\":\"1585390146316883\",\"item_id\":347551,\"recommendation\":\"5\"}]";
        String c = "{\"item_idaa\":347575,\"recommendation\":\"1\"}";
        Object object = JSON.parse(a);
        Object object2 = JSON.parse(b);
        Object object3 = JSON.parse(c);
        List<Long> ids = (List<Long>) JSONPath.eval(object,"$.item_id_list");
        System.out.println(ids);
        List<Long> ids2 = (List<Long>) JSONPath.eval(object2,"$..item_id");
        System.out.println(ids2);
        List<Long> ids3 = new ArrayList<>();
        Object evalResult = JSONPath.eval(object3, "$.item_idaa");
        if (evalResult instanceof List) {
            ids3 = (List<Long>) evalResult;
        }else if (evalResult instanceof Integer){
            ids3.add(Long.valueOf(evalResult.toString()));
        }
        System.out.println(ids3);
    }
}
