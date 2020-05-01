package cn.lsy99.middleware1.service;

import cn.lsy99.middleware1.dao.MessageQueueMapper;
import cn.lsy99.middleware1.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PullMessageService {
    @Autowired
    private MessageQueueMapper messageQueueMapper;

    public ResponseMessage pullMessage(String consumerName) {
        MessageQueueExample messageQueueExample = new MessageQueueExample();
        MessageQueueExample.Criteria messageQueueExampleCriteria = messageQueueExample.createCriteria();
        messageQueueExampleCriteria.andConsumerNameEqualTo(consumerName);
        List<MessageQueue> message = messageQueueMapper.selectByExample(messageQueueExample);
        if (message.size() == 0) {
            return ResponseMessageFactory.fail("没有消息");
        } else if (message.size() == 1) {
            MessageQueue removeMessage = message.get(0);
            messageQueueMapper.deleteByPrimaryKey(message.get(0).getId());
            return ResponseMessageFactory.responseWithMessage("目前已读取所有消息", removeMessage);

        } else {
            int size = message.size();
            // 按时间排序
            message.sort(Comparator.comparing(MessageQueue::getTime));
            MessageQueue removeMessage = message.get(0);
            messageQueueMapper.deleteByPrimaryKey(removeMessage.getId());
            return ResponseMessageFactory.responseWithMessage("目前还有" + (size - 1) + "条消息", removeMessage);
        }
    }
}


