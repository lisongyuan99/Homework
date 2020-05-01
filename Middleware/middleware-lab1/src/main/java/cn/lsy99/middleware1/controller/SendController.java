package cn.lsy99.middleware1.controller;

import cn.lsy99.middleware1.entity.ResponseMessage;
import cn.lsy99.middleware1.service.SendService;
import cn.lsy99.middleware1.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/middleware1")
public class SendController {
    @Autowired
    private SendService sendService;

    @PostMapping(value = "/send")
    public ResponseMessage create(@RequestBody SendInput input) {
        String name = input.getName();
        String topic = input.getTopic();
        String message = input.getMessage();
//        System.out.printf("name:%s  topic:%s message:%s\n", name, topic, message);
//        System.out.println("");
        return sendService.send(name, topic, message);
    }
}

class SendInput{
    private String name;
    private String topic;
    private String message;

    public SendInput(String name, String topic, String message) {
        this.name = name;
        this.topic = topic;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getTopic() {
        return topic;
    }

    public String getMessage() {
        return message;
    }
}