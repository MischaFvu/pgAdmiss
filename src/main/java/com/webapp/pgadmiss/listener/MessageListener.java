package com.webapp.pgadmiss.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.webapp.pgadmiss.dto.MessageDto;
import com.webapp.pgadmiss.entity.Application;
import com.webapp.pgadmiss.entity.Approval;
import com.webapp.pgadmiss.entity.Message;
import com.webapp.pgadmiss.entity.Message.MessageType;
import com.webapp.pgadmiss.service.IMessageService;
import com.webapp.pgadmiss.service.serviceImpl.MessageServiceImpl;

@Component
public class MessageListener {

    @Autowired
    private IMessageService messageService;
    @KafkaListener(topics = "audit")
    public void handleTodoMsg(ConsumerRecord<String, MessageDto> record) {


    }

    /**
     * After consumer the reminder message, the message status will be updated
     * @param reminderMsg
     */
    @KafkaListener(topics = "reminder")
    public void handleReminderMsg(Message reminderMsg) {
        messageService.updateMsgStatus(reminderMsg, MessageType.REMINDER_DONE);
    }
}
