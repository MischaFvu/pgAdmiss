package com.webapp.pgadmiss.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapp.pgadmiss.dto.MessageDto;
import com.webapp.pgadmiss.entity.Application;
import com.webapp.pgadmiss.entity.Approval;
import com.webapp.pgadmiss.entity.Message;
import com.webapp.pgadmiss.entity.Application.AppStatus;
import com.webapp.pgadmiss.entity.Message.MessageType;
import com.webapp.pgadmiss.entity.User;
import com.webapp.pgadmiss.mapper.MessageMapper;
import com.webapp.pgadmiss.repository.FormSpecification;
import com.webapp.pgadmiss.repository.MessageRepository;
import com.webapp.pgadmiss.repository.UserRepository;
import com.webapp.pgadmiss.service.IMessageService;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageServiceImpl implements IMessageService{


    @Autowired
    private MessageRepository msgRepository;
    @Autowired
    private UserServiceImpl userService;
    
    @Autowired
    private ObjectMapper objMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public MessageServiceImpl(KafkaTemplate<String, String> kafkaTemplate, MessageRepository msgRepository, UserServiceImpl userService) {
        this.kafkaTemplate = kafkaTemplate;
        this.msgRepository = msgRepository;
        this.userService = userService;
    }
 
    

    /**
     * After student submits application
     * 1. Send to-do message to director
     * 2. Send to-read message to student
     * @param application
     */
    @Override
    public void submitApplicationMsg(Application application) {
        try {
            MessageDto todoMsg = createMsg(application, MessageType.AUDIT_TODO);
            kafkaTemplate.send("audit", objMapper.writeValueAsString(todoMsg));
            MessageDto toReadMsg = createMsg(application, MessageType.REMINDER_TODO);
            kafkaTemplate.send("reminder", objMapper.writeValueAsString(toReadMsg));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * After approval
     * 1. Update to-do msg to done msg
     * 2. Send to-read message to student
     * @param approval
     */
    @Override
    public void approvalMsg(Approval approval) {
        Application app = approval.getApplication();
        Message todoMsg = msgRepository.findByMsgId(app.getAppId());
        if (todoMsg != null) {
            updateMsgStatus(todoMsg, MessageType.AUDIT_DONE);
        }
        MessageDto toReadMsg = createMsg(app, MessageType.REMINDER_TODO);
        try {
            kafkaTemplate.send("reminder", objMapper.writeValueAsString(toReadMsg));
        } catch (JsonProcessingException e) {
            log.info("Unable to convert reminder message to json:" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void updateMsgStatus(Message message, MessageType msgType) {
        message.setMsgType(msgType);
        message.setUpdateDate(LocalDateTime.now());
        msgRepository.save(message);
    }

    public String createMsgText(MessageType msgType, String billNo, AppStatus appStatus) {
        switch (msgType.name()) {
            case "AUDIT_TODO":
                return String.format("[Action Required] Application #%s is awaiting your review.", billNo);
            case "REMINDER_TODO":
                return String.format("[Reminder]: The status of Application #%s has been changed: #%s.", billNo, appStatus.name());
            default:
                throw new IllegalArgumentException("Unsupported notification type.");
            }
    }

    /**
     * Create new msg
     * @param application
     * @param msgType
     * @return
     */
    public MessageDto createMsg(Application application, MessageType msgType) {
        Message msg = new Message();
        msg.setApplication(application);
        msg.setMsgType(msgType);
        msg.setCreateDate(LocalDateTime.now());
        msg.setUpdateDate(LocalDateTime.now());
        msg.setMsgText(createMsgText(msgType, application.getAppNo(), application.getAppStatus()));
        msg.setSender(application.getStudent().getUser());
        if (msgType.equals(MessageType.AUDIT_TODO)) {
            msg.setReceiver(application.getMajor().getUser());
        } else {
            msg.setReceiver(application.getStudent().getUser());
        }
        msgRepository.save(msg);
        return MessageMapper.mapToMessageDto(msg);
    }

    @Override
    public Page<MessageDto> findAllMsgBySpec(MessageDto msgDto) {
        Specification<Message> spec = fetchMsgSpec(msgDto);
        Pageable pageable = PageRequest.of(msgDto.getPageNo()-1, msgDto.getPageSize(), Sort.by(Sort.Direction.DESC, "createDate"));

        // Pageable pageable = PageRequest.of(msgDto.getPageNo(), msgDto.getPageSize(), Sort.by(Sort.Direction.DESC, "updateDate"));
        Page<Message> messagePage = msgRepository.findAll(spec, pageable);
        // List<Message> messagePage = msgRepository.findAll(spec, Sort.by(Sort.Direction.DESC, "updateDate"));
        List<Message> messagePageList = messagePage.getContent();
        List<MessageDto> messageDtos = messagePageList.stream()
                .map(message -> MessageMapper.mapToMessageDto(message))
                .collect(Collectors.toList());
        
        return new PageImpl<MessageDto>(messageDtos, pageable, messagePage.getTotalElements());
    }

    public Specification<Message> fetchMsgSpec(MessageDto msgDto) {
        return (Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (msgDto.getMsgType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("msgType"), MessageType.valueOf(msgDto.getMsgType())));
                // Predicate reminderTodoPredicate = criteriaBuilder.or(
                        //     criteriaBuilder.equal(root.get("msgType"), 3),
                        //     criteriaBuilder.equal(root.get("msgType"), 4)
                        // );
            }
            if (null != msgDto.getReceiverName()) {
                predicates.add(criteriaBuilder.equal(root.get("receiver").get("userName"), msgDto.getReceiverName()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        
    }
}

