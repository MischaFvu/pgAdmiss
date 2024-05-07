package com.webapp.pgadmiss.dto;



import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper = false)
@Getter @Setter @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDto {

    @JsonProperty("pageNo")
    private int pageNo;

    @JsonProperty("pageSize")
    private int pageSize;

    @JsonProperty("msgId")
    private UUID msgId;

    @JsonProperty("msgText")
    private String msgText;

    @JsonProperty("senderTime")
    private LocalDateTime senderTime;

    @JsonProperty("senderName")
    private String senderName;  

    @JsonProperty("receiverName")
    private String receiverName;
    
    @JsonProperty("auditTime")
    private LocalDateTime auditTime;

    @JsonProperty("msgType")
    private String msgType;

    @JsonProperty("appId")
    private UUID appId;

}
