package cn.lsy99.middleware1.dao;

import cn.lsy99.middleware1.entity.MessageQueue;
import cn.lsy99.middleware1.entity.MessageQueueExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component

public interface MessageQueueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message_queue
     *
     * @mbggenerated
     */
    int countByExample(MessageQueueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message_queue
     *
     * @mbggenerated
     */
    int deleteByExample(MessageQueueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message_queue
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message_queue
     *
     * @mbggenerated
     */
    int insert(MessageQueue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message_queue
     *
     * @mbggenerated
     */
    int insertSelective(MessageQueue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message_queue
     *
     * @mbggenerated
     */
    List<MessageQueue> selectByExample(MessageQueueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message_queue
     *
     * @mbggenerated
     */
    MessageQueue selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message_queue
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") MessageQueue record, @Param("example") MessageQueueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message_queue
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") MessageQueue record, @Param("example") MessageQueueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message_queue
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MessageQueue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table message_queue
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MessageQueue record);
}