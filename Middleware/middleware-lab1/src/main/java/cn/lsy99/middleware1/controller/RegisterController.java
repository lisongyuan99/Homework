package cn.lsy99.middleware1.controller;

import cn.lsy99.middleware1.entity.ResponseMessage;
import cn.lsy99.middleware1.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/middleware1")
public class RegisterController {
    @Autowired
    private MessageService messageService;

    @PostMapping(value = "/register")
    public ResponseMessage register(@RequestBody String name) {
        return messageService.register(name);
    }
}
