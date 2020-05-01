package cn.lsy99.middleware1.service;

import cn.lsy99.middleware1.dao.IdListMapper;
import cn.lsy99.middleware1.entity.IdList;
import cn.lsy99.middleware1.entity.IdListExample;
import cn.lsy99.middleware1.entity.ResponseMessage;
import cn.lsy99.middleware1.entity.ResponseMessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private IdListMapper idListMapper;

    public ResponseMessage register(String name) {
        // 保证不重复
        IdListExample idListExample = new IdListExample();
        IdListExample.Criteria criteria = idListExample.createCriteria();
        criteria.andNameEqualTo(name);
        List<IdList> result = idListMapper.selectByExample(idListExample);
        // System.out.println(result.size());
        if (result.size() == 0) {
            // 注册name
            IdList idList = new IdList();
            idList.setName(name);
            idListMapper.insert(idList);
            return ResponseMessageFactory.success();
        } else {
            return ResponseMessageFactory.fail("name已存在");
        }
    }
}
