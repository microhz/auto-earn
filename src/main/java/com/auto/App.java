package com.auto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : jihai
 * @date : 2020/6/19
 * @description : 专门用来测试各种工具
 */
public class App {

    public static void main(String[] args) {
        List<CampaignCalculate> campaignCalculates = new ArrayList<>();

        List<CampaignCalculate> collect = campaignCalculates.stream()
                .filter(campaignCalculate -> campaignCalculate.getCampaignId() != null).collect(Collectors.toList());
    }
}

@Data
@NoArgsConstructor
class CampaignCalculate {
    private Long sponsorId;

    private Integer campaignId;

    private Long productionId;

    private Integer totalNum;

    private Integer productionNum;

    private Integer applyNum;

    private Integer handleNum;

    private Integer passNum;

    private Integer shipNum;

    private Integer evaluationNum;

    private Double evlCostSum;

    private Double evlCostMin;

    private Date startTime;

    private Date endTime;


}


interface Notify {

    /**
     * 通知
     * @param msg
     */
    void notify(String msg);
}

class Observer implements Notify {
    @Override
    public void notify(String msg) {
        System.out.println("ob1 观察到消息" + msg);
    }
}

class Oberver2 implements Notify {
    @Override
    public void notify(String msg) {
        System.out.println("ob2 观察到消息" + msg);
    }
}

class Producer {

    private List<Notify> notifyList;

    public Producer(List<Notify> notifyList) {
        this.notifyList = notifyList;
    }

    public void sendMsg(String msg) {
        notifyList.forEach(notify -> {
            notify.notify(msg);
        });
    }
}


