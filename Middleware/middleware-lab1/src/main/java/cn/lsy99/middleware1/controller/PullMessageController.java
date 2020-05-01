package cn.lsy99.middleware1.controller;

import cn.lsy99.middleware1.entity.ResponseMessage;
import cn.lsy99.middleware1.service.MessageService;
import cn.lsy99.middleware1.service.PullMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/middleware1")
public class PullMessageController {
    @Autowired
    private PullMessageService pullMessageService;

    @PostMapping(value = "/pull-message")
    public ResponseMessage pullMessage(@RequestBody String name) {
        return pullMessageService.pullMessage(name);
    }
}

