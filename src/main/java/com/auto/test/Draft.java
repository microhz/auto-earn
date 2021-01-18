package com.auto.test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author : jihai
 * @date : 2020/8/24
 * @description :
 */
public class Draft {

    public static void main(String[] args) {

//        System.out.println(1805508 % 1024);
       /* String s = "[应用:group_stock-center] [主机:item-stock-center-02] [文件:error.log] ===> [2020-10-30 09:26:55] [ERROR] [] [TxId:,SpanId:] [ConsumeMessageThread_3]  c.g.i.s.p.o.i.BaseMessageProcessor.88    - failed to process stock receive message: {\"refundNumber\":2050341140840690000,\"shopOrderId\":307970068252931,\"itemOrderId\":307970068272931,\"orderStatusSnapshot\":2,\"sellerShopId\":40719,\"itemId\":1805508,\"skuId\":10221554,\"applyCount\":1,\"refundStatus\":50,\"extra\":\"{\\\"relationRefund\\\":2050341140775690000}\",\"orderMarketplace\":11,\"buyerUserId\":1284702931,\"type\":1,\"applyAmount\":1989,\"applyTime\":1603941941000,\"orderType\":1,\"payOrderId\":307970068242931} , mertricName refund-message-consume-error , error com.ggj.item.stock.common.exception.StockCenterException: activityUpdateOperateBOList:[ActivityUpdateOperateBO(skuActivityId=10221554_12251099)]待发货退款，操作库存失败\n" +
                " \n" +
                "[应用:group_stock-center] [主机:item-stock-center-02] [文件:error.log] ===> [2020-10-30 09:26:55] [ERROR] [] [TxId:,SpanId:] [ConsumeMessageThread_3]  c.g.i.s.p.o.i.BaseMessageProcessor.88    - failed to process stock receive message: {\"refundNumber\":2050341140840690000,\"shopOrderId\":307970068252931,\"itemOrderId\":307970068272931,\"orderStatusSnapshot\":2,\"sellerShopId\":40719,\"itemId\":1805508,\"skuId\":10221554,\"applyCount\":1,\"refundStatus\":50,\"extra\":\"{\\\"relationRefund\\\":2050341140775690000}\",\"orderMarketplace\":11,\"buyerUserId\":1284702931,\"type\":1,\"applyAmount\":1989,\"applyTime\":1603941941000,\"orderType\":1,\"payOrderId\":307970068242931} , mertricName refund-message-consume-error , error com.ggj.item.stock.common.exception.StockCenterException: activityUpdateOperateBOList:[ActivityUpdateOperateBO(skuActivityId=10221554_12251099)]待发货退款，操作库存失败\n" +
                " \n" +
                "[应用:group_stock-center] [主机:item-stock-center-02] [文件:error.log] ===> [2020-10-30 09:26:54] [ERROR] [] [TxId:a8c01b08^1602577156262^7523740,SpanId:2477122086584819991] [ConsumeMessageThread_3]  c.g.i.s.p.o.i.BaseMessageProcessor.88    - failed to process stock receive message: {\"refundNumber\":2050341140840690000,\"shopOrderId\":307970068252931,\"itemOrderId\":307970068272931,\"orderStatusSnapshot\":2,\"sellerShopId\":40719,\"itemId\":1805508,\"skuId\":10221554,\"applyCount\":1,\"refundStatus\":50,\"extra\":\"{\\\"relationRefund\\\":2050341140775690000}\",\"orderMarketplace\":11,\"buyerUserId\":1284702931,\"type\":1,\"applyAmount\":1989,\"applyTime\":1603941941000,\"orderType\":1,\"payOrderId\":307970068242931} , mertricName refund-message-consume-error , error com.ggj.item.stock.common.exception.StockCenterException: activityUpdateOperateBOList:[ActivityUpdateOperateBO(skuActivityId=10221554_12251099)]待发货退款，操作库存失败\n" +
                " \n" +
                "[应用:group_stock-center] [主机:item-stock-center-02] [文件:error.log] ===> [2020-10-30 09:26:54] [ERROR] [] [TxId:a8c01b08^1602577156262^7523740,SpanId:2477122086584819991] [ConsumeMessageThread_3]  c.g.i.s.p.o.i.BaseMessageProcessor.88    - failed to process stock receive message: {\"refundNumber\":2050341140840690000,\"shopOrderId\":307970068252931,\"itemOrderId\":307970068272931,\"orderStatusSnapshot\":2,\"sellerShopId\":40719,\"itemId\":1805508,\"skuId\":10221554,\"applyCount\":1,\"refundStatus\":50,\"extra\":\"{\\\"relationRefund\\\":2050341140775690000}\",\"orderMarketplace\":11,\"buyerUserId\":1284702931,\"type\":1,\"applyAmount\":1989,\"applyTime\":1603941941000,\"orderType\":1,\"payOrderId\":307970068242931} , mertricName refund-message-consume-error , error com.ggj.item.stock.common.exception.StockCenterException: activityUpdateOperateBOList:[ActivityUpdateOperateBO(skuActivityId=10221554_12251099)]待发货退款，操作库存失败";


        String[] split = s.split("group_stock-center");
        List<Long> orderIdList = Lists.newArrayList();

        Long skuId = 10221554L;
        for (String s2 : split) {
            if (! s2.contains("ConsumeMessageThread")) continue;
            int i = s2.indexOf("message: ");
            int index1 = i + 9;
            int index2 = s2.indexOf(" , mertricName");
            String substring = s2.substring(index1, index2);
//            System.out.println(substring);

            Map map = JSONObject.parseObject(substring, Map.class);
            if (Long.valueOf(map.get("skuId").toString()).equals(skuId)) {
                Long itemOrderId = Long.valueOf(map.get("itemOrderId").toString());
                if (! orderIdList.contains(itemOrderId)) {
                    orderIdList.add(itemOrderId);
                }
            }
        }

        System.out.println(orderIdList);*/

       /*String s3 = "307970068272931, 307966636733381, 307969133298159, 307969059604642, 307970069432931, 307970068272931, 307966641711886, 307968718876545, 307968905092516, 307966636733381 ,307970289224019, 307968792193406, 307970269117010, 307966815094685,  307966758006355, 307970201166355, 307969428138054, 307970288917322, 307970289224019, 307966846330241, 307969221000034, 307969239030034, 307968652773823, 307966758006355, 307968680533720, 307969101223720, 307970275248925, 307966846330241, 307970285233828, 307970212820684, 307968654292861, 307969431300053, 307969431300053, 307969172944245, 307970036574876, 307968811697417, 307969131113185, 307969131113185, 307968739088242, 307966665231762, 307968681321762, 307970165453936, 307970165453936, 307969103803796, 307969395946379, 307969107603720";
        String[] split = s3.split(",");
        Set<String> set = new HashSet<>();
        for (String s2 : split) {
            set.add(s2.replace(" ", ""));
        }
        System.out.println(set);
        StringBuffer sb = new StringBuffer();
        for (String s : set) {
            sb.append(s).append(",");
        }
        System.out.println(sb);*/
//        System.out.println(Lists.newArrayList(307970068272931, 307966636733381, 307969133298159, 307969059604642, 307970069432931, 307970068272931, 307966641711886, 307968718876545, 307968905092516, 307966636733381 ,307970289224019, 307968792193406, 307970269117010, 307966815094685,  307966758006355, 307970201166355, 307969428138054, 307970288917322, 307970289224019, 307966846330241, 307969221000034, 307969239030034, 307968652773823, 307966758006355, 307968680533720, 307969101223720, 307970275248925, 307966846330241, 307970285233828, 307970212820684, 307968654292861, 307969431300053, 307969431300053, 307969172944245, 307970036574876, 307968811697417, 307969131113185, 307969131113185, 307968739088242, 307966665231762, 307968681321762, 307970165453936, 307970165453936, 307969103803796, 307969395946379, 307969107603720));

        msg(1, "a", 1);
    }


    public static void msg(int a, Object ... params) {
        System.out.println(params);
    }
    // 307970068272931, 307966636733381, 307969133298159, 307969059604642, 307970069432931, 307970068272931, 307966641711886, 307968718876545, 307968905092516, 307966636733381 ,307970289224019, 307968792193406, 307970269117010, 307966815094685,  307966758006355, 307970201166355, 307969428138054, 307970288917322, 307970289224019, 307966846330241, 307969221000034, 307969239030034, 307968652773823, 307966758006355, 307968680533720, 307969101223720, 307970275248925, 307966846330241, 307970285233828, 307970212820684, 307968654292861, 307969431300053, 307969431300053, 307969172944245, 307970036574876, 307968811697417, 307969131113185, 307969131113185, 307968739088242, 307966665231762, 307968681321762, 307970165453936, 307970165453936, 307969103803796, 307969395946379, 307969107603720

}


