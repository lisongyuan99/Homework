package cn.lsy99.middleware1.entity;

public class ResponseMessageFactory {

    // 注册、订阅或新建主题成功
    public static ResponseMessage success() {
        return new ResponseMessage(true, null, null);
    }

    // 注册、订阅或新建主题失败
    public static ResponseMessage fail(String failMessage) {
        return new ResponseMessage(false, failMessage, null);
    }

    public static ResponseMessage responseWithMessage(String failMessage, MessageQueue messageQueue) {
        return new ResponseMessage(true, failMessage, messageQueue);
    }
}
