package com.webapp.pgadmiss.dto;

import java.time.LocalDateTime;

import com.webapp.pgadmiss.entity.Message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper = false)
@Getter @Setter
public class MessageListDto extends Message {
    private int pageNo;
    private int pageSize;

    public Message covert() {
        Message msg = new Message();
        msg.setApplication(this.getApplication());
        msg.setCreateDate(this.getCreateDate());
        msg.setMsgId(this.getMsgId());
        msg.setMsgText(this.getMsgText());
        msg.setMsgType(this.getMsgType());
        msg.setReceiver(this.getReceiver());
        msg.setSender(this.getSender());
        msg.setUpdateDate(LocalDateTime.now());
        return msg;     
    }

    

    
}
