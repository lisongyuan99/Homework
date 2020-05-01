package cn.lsy99.middleware1.entity;

public class ResponseMessage {
    private boolean success;
    private String failMessage;
    private MessageQueue messageQueue;

    public ResponseMessage(boolean success, String failMessage, MessageQueue messageQueue) {
        this.success = success;
        this.failMessage = failMessage;
        this.messageQueue = messageQueue;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFailMessage() {
        return failMessage;
    }

    public MessageQueue getMessageQueue() {
        return messageQueue;
    }
}
