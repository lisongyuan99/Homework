package cn.lsy99.middleware1.controller;

import cn.lsy99.middleware1.entity.ResponseMessage;
import cn.lsy99.middleware1.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/middleware1")
public class TestController {
    @Autowired
    private MessageService messageService;
    @RequestMapping("/")
    public String index(){
        return "Hello Spring Boot";
    }

    @PostMapping(value = "/post")
    public String post(@RequestBody Input input) {
        System.out.println("post成功");
        String id = input.getId();
        String password = input.getPassword();
        System.out.printf("id:%s  password:%s\n", id, password);
        return "success";
    }
}

class Input{
    private final String id;
    private final String password;

    public Input(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}