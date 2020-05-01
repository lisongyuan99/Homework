package cn.lsy99.middleware1.controller;

import cn.lsy99.middleware1.dao.IdListMapper;
import cn.lsy99.middleware1.entity.ResponseMessage;
import cn.lsy99.middleware1.service.MessageService;
import cn.lsy99.middleware1.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/middleware1")
public class CreateController {
    @Autowired
    private TopicService topicService;
//    @RequestMapping("/")
//    public String index(){
//        return "Hello Spring Boot";
//    }

    @PostMapping(value = "/create")
    public ResponseMessage create(@RequestBody CreateInput input) {
        String name = input.getName();
        String topic = input.getTopic();
        System.out.printf("name:%s  topic:%s\n", name, topic);
        System.out.println("创建成功");
        return topicService.create(name, topic);
    }
}

class CreateInput{
    private String name;
    private String topic;

    public CreateInput(String name, String topic) {
        this.name = name;
        this.topic = topic;
    }

    public String getName() {
        return name;
    }

    public String getTopic() {
        return topic;
    }
}