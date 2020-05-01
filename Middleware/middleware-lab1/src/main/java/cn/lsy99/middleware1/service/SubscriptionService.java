package cn.lsy99.middleware1.service;

import cn.lsy99.middleware1.dao.ConsumerMapper;
import cn.lsy99.middleware1.dao.IdListMapper;
import cn.lsy99.middleware1.dao.ProviderMapper;
import cn.lsy99.middleware1.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    private IdListMapper idListMapper;
    @Autowired
    private ConsumerMapper consumerMapper;
    @Autowired
    private ProviderMapper providerMapper;

    public ResponseMessage subscription(String name, String topic) {
        // 检测name是否存在
        IdListExample idListExample = new IdListExample();
        IdListExample.Criteria idListExampleCriteria = idListExample.createCriteria();
        idListExampleCriteria.andNameEqualTo(name);
        List<IdList> idListsResult = idListMapper.selectByExample(idListExample);
        if (idListsResult.size() == 0) {
            return ResponseMessageFactory.fail("name不存在");
        }

        ProviderExample providerExample = new ProviderExample();
        ProviderExample.Criteria providerExampleCriteria = providerExample.createCriteria();
        providerExampleCriteria.andTopicEqualTo(topic);
        List<Provider> providerList = providerMapper.selectByExample(providerExample);
        if (providerList.size() == 0) {
            return ResponseMessageFactory.fail("topic不存在");
        }

        ConsumerExample consumerExample = new ConsumerExample();
        ConsumerExample.Criteria consumerExampleCriteria = consumerExample.createCriteria();
        consumerExampleCriteria.andNameEqualTo(name).andTopicEqualTo(topic);
        List<Consumer> consumerList = consumerMapper.selectByExample(consumerExample);
        if (consumerList.size() != 0) {
            return ResponseMessageFactory.fail("已经订阅");
        }

        //添加订阅
        Consumer consumer = new Consumer();
        consumer.setName(name);
        consumer.setTopic(topic);
        consumerMapper.insert(consumer);

        return ResponseMessageFactory.success();
    }

}
