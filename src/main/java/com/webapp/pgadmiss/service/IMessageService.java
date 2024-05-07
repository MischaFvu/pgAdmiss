package com.webapp.pgadmiss.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.webapp.pgadmiss.dto.MessageDto;
import com.webapp.pgadmiss.entity.Application;
import com.webapp.pgadmiss.entity.Approval;
import com.webapp.pgadmiss.entity.Message;
import com.webapp.pgadmiss.entity.Message.MessageType;

public interface IMessageService {


    void submitApplicationMsg(Application application);

    
    void approvalMsg(Approval approval);


    void updateMsgStatus(Message message, MessageType msgType);


    Page<MessageDto> findAllMsgBySpec(MessageDto msgDto);
}
