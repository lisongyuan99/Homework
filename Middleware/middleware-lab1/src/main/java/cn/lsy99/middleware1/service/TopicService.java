package cn.lsy99.middleware1.service;

import cn.lsy99.middleware1.dao.IdListMapper;
import cn.lsy99.middleware1.dao.ProviderMapper;
import cn.lsy99.middleware1.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    @Autowired
    private IdListMapper idListMapper;
    @Autowired
    private ProviderMapper providerMapper;

    public ResponseMessage create(String name, String topic) {
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
        if (providerList.size() != 0) {
            return ResponseMessageFactory.fail("topic已存在");
        }

        Provider provider = new Provider();
        provider.setName(name);
        provider.setTopic(topic);
        providerMapper.insert(provider);

        return ResponseMessageFactory.success();
    }
}
