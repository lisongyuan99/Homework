package cn.lsy99.middleware1.controller;

import cn.lsy99.middleware1.entity.ResponseMessage;
import cn.lsy99.middleware1.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/middleware1")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping(value = "/subscription")
    public ResponseMessage subscription(@RequestBody SubscriptionInput subscriptionInput) {
        return subscriptionService.subscription(subscriptionInput.getName(), subscriptionInput.getTopic());
    }
}

class SubscriptionInput{
    private String name;
    private String topic;

    public SubscriptionInput(String name, String topic) {
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

