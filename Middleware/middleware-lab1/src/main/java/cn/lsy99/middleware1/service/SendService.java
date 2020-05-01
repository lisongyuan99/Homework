package cn.lsy99.middleware1.service;

import cn.lsy99.middleware1.dao.ConsumerMapper;
import cn.lsy99.middleware1.dao.MessageQueueMapper;
import cn.lsy99.middleware1.dao.ProviderMapper;
import cn.lsy99.middleware1.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class SendService {
    @Autowired
    private ProviderMapper providerMapper;
    @Autowired
    private ConsumerMapper consumerMapper;
    @Autowired
    private MessageQueueMapper messageQueueMapper;

    public ResponseMessage send(String name, String topic, String message) {
        // 数据库的时间字段
        Date date = new Date();
        ProviderExample providerExample = new ProviderExample();
        ProviderExample.Criteria providerExampleCriteria = providerExample.createCriteria();
        providerExampleCriteria.andNameEqualTo(name).andTopicEqualTo(topic);
        List<Provider> providerList = providerMapper.selectByExample(providerExample);
        if (providerList.size() == 0) {
            return ResponseMessageFactory.fail("topic不存在");
        }
        else {
            ConsumerExample consumerExample = new ConsumerExample();
            ConsumerExample.Criteria consumerExampleCriteria = consumerExample.createCriteria();
            consumerExampleCriteria.andTopicEqualTo(topic);
            List<Consumer> consumerList = consumerMapper.selectByExample(consumerExample);
            System.out.println(consumerList.size());
            if (consumerList.size() != 0) {
                for (Consumer e:consumerList){
                    MessageQueue messageQueue = new MessageQueue();
                    messageQueue.setProviderName(name);
                    messageQueue.setConsumerName(e.getName());
                    messageQueue.setTopic(topic);
                    messageQueue.setMessage(message);
                    messageQueue.setTime(date);
                    messageQueueMapper.insert(messageQueue);
                }
            }
            return ResponseMessageFactory.success();
        }
    }
}
