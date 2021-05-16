package com.auto.test;

import com.ggj.platform.adonis.springboot.rocketmq.model.Message;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : jihai
 * @date : 2021/3/18
 * @description :
 */
@SpringBootApplication
public class ChainTest implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplication(ChainTest.class).run(args);
    }

    @Autowired
    private BinlogHandlerChain binlogHandlerChain;

    @Override
    public void run(String... args) throws Exception {
        Message message = new Message();

        /*
        构造消息
         */
        BinlogDTO binlogDTO = new BinlogDTO();
        binlogDTO.setTableName("tableA");
        message.setBody(SerializationUtils.serialize(binlogDTO));

        /*
        处理消息
         */
//        BinlogHandlerChain binlogHandlerChain = new BinlogHandlerChain();
        // tag is null
        binlogHandlerChain.doSubmit(message);

        // tag is not null
        message.setTag("tagA");
        binlogHandlerChain.doSubmit(message);
    }
}


class BinlogDTO implements Serializable {
    private String tableName;
    //....其他省略

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}


interface BinlogHandler {
    /**
     * 处理消息的tag is null
     * @param message
     */
    void processIgnore(Message message);

    /**
     * 处理tag 不为 null
     * @param message
     */
    void processMergeTag(Message message);

    /**
     * 获取表名
     * @return
     */
    String getTableName();
}

abstract class AbstractBinlogHandler implements BinlogHandler {

    public boolean isMatch(Message message) {
        return getTableName().equals(getTableName(message));
    }

    private String getTableName(Message message) {
        // TODO 获取message 里的的table
        BinlogDTO binlogDTO = SerializationUtils.deserialize(message.getBody());
        return binlogDTO.getTableName();
    }
}

@Component
class BinlogHandlerChain {

    @Autowired
    private List<AbstractBinlogHandler> binlogHandlerList;

    /*static {
        // load all handler
        // 可以利用 Spring的注入机制

        TableAHandler tableAHandler = new TableAHandler();
        TableBHandler tableBHandler = new TableBHandler();
        binlogHandlerList.add(tableAHandler);
        binlogHandlerList.add(tableBHandler);
    }*/

    public void doSubmit(Message message) {
        // 找出对应表匹配的handler
        List<AbstractBinlogHandler> handlerList = binlogHandlerList.stream().filter(e -> e.isMatch(message)).collect(Collectors.toList());
        /*
        分别处理tag和不需要的tag
         */
        if (message.getTag() == null) {
            handlerList.forEach(e -> e.processIgnore(message));
        } else {
            handlerList.forEach(e -> e.processMergeTag(message));
        }
    }
}

@Component
class TableAHandler extends AbstractBinlogHandler {

    @Override
    public void processIgnore(Message message) {
        System.out.println("proccsse table a ignore tag");
    }

    @Override
    public void processMergeTag(Message message) {
        System.out.println("processe table a merge tag");
    }

    @Override
    public String getTableName() {
        return "tableA";
    }
}

@Component
class TableBHandler extends AbstractBinlogHandler {
    @Override
    public void processIgnore(Message message) {
        System.out.println("proccsse table b ignore tag");

    }

    @Override
    public void processMergeTag(Message message) {
        System.out.println("processe table b merge tag");
    }

    @Override
    public String getTableName() {
        return "tableB";
    }
}


