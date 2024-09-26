package org.example;

import java.lang.reflect.Method;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("nihao", "asdasd");
        String ex = map.get("asd");
        System.out.println(ex);

        int i = 2;
        int i2 = 3;
        System.out.println(i / (double) i2);
        //        1
        //        LambdaQueryWrapper<CustomerRemarkPo> wrapper = new LambdaQueryWrapper<CustomerRemarkPo>()
        //                .eq(CustomerRemarkPo::getCustomerAccountId, params.get("qp-customerAccountId-eq"))
        //                .eq(CustomerRemarkPo::getCustomerAccountType, Short.parseShort((String)params.get("qp-customerAccountType-eq")))
        //                .nested(i -> i.eq(CustomerRemarkPo::getSourceId, params.get("qp-sourceId-eq")).or().isNull(CustomerRemarkPo::getSourceId));
        //
        //        Query<CustomerRemarkPo> query = new Query<>(params);
        //
        //        Page<CustomerRemarkPo> page = customerRemarkPoService.page(query.getPage(), wrapper);
        //
        //        return new Pager<>(page);
//        List<Integer> a = new ArrayList<>();
//
//        System.out.println("displaying b: " + b[0]);
    }

}