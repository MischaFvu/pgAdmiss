package com.webapp.pgadmiss.mapper;


import com.webapp.pgadmiss.dto.MessageDto;
import com.webapp.pgadmiss.entity.Application;
import com.webapp.pgadmiss.entity.Application.AppStatus;
import com.webapp.pgadmiss.entity.Message.MessageType;
import com.webapp.pgadmiss.service.IApplicationService;
import com.webapp.pgadmiss.service.serviceImpl.ApplicationServiceImpl;
import com.webapp.pgadmiss.service.serviceImpl.UserServiceImpl;
import com.webapp.pgadmiss.entity.Message;
import com.webapp.pgadmiss.entity.User;

public class MessageMapper {

    /**
     * Convert message entity to message dto to send to client list.
     * @param message
     * @return
     */
    public static MessageDto mapToMessageDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setMsgId(message.getMsgId());
        messageDto.setMsgText(message.getMsgText());
        messageDto.setSenderTime(message.getCreateDate());
        messageDto.setSenderName(message.getSender().getUserName());
        Application app = message.getApplication();
        if (null != app) {
            messageDto.setAppId(app.getAppId());   
            if (AppStatus.APPROVED == app.getAppStatus())   {
                messageDto.setAuditTime(app.getUpdateDate());
            }   
        } 
        messageDto.setMsgType(message.getMsgType().name());
        return messageDto;
    }

    public static Message mapToMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setMsgId(messageDto.getMsgId());
        message.setMsgText(messageDto.getMsgText());
        message.setCreateDate(messageDto.getSenderTime());
        message.setUpdateDate(messageDto.getAuditTime());
        // UserServiceImpl userService = new UserServiceImpl();
        // if (!messageDto.getSenderName().isEmpty()) {
        //     User user = userService.findByUserName(messageDto.getSenderName());
        //     message.setSender(user);
        // }
        // if (null != messageDto.getMsgType()) {
        //     message.setMsgType(MessageType.valueOf(messageDto.getMsgType()));
        // }
        // if (!messageDto.getReceiverName().isEmpty()) {
        //     User user = userService.findByUserName(messageDto.getReceiverName());
        //     message.setReceiver(user);
        // }
        if (messageDto.getAppId() != null) {
            ApplicationServiceImpl appService = new ApplicationServiceImpl();
            Application app = appService.findAppById(messageDto.getAppId());
            message.setApplication(app);

        }
        return message;
    }

}
