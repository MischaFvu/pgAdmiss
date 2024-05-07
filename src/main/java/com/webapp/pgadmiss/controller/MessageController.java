package com.webapp.pgadmiss.controller;

import org.springframework.web.bind.annotation.RestController;

import com.webapp.pgadmiss.constant.ErrorResponse;
import com.webapp.pgadmiss.dto.MessageDto;
import com.webapp.pgadmiss.entity.Application;
import com.webapp.pgadmiss.repository.MessageRepository;
import com.webapp.pgadmiss.service.IApplicationService;
import com.webapp.pgadmiss.service.IMessageService;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.xml.transform.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@Slf4j
@RequestMapping(path = "/msg", method=RequestMethod.GET, produces = "application/json")
public class MessageController implements Serializable{

    private static final long serialVersionUID = 1L;

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IApplicationService appService;
    @PostMapping("/query")
    public ResponseEntity<Page<MessageDto>> queryMsg(@RequestBody MessageDto msgDto) {
        Page<MessageDto> allMsg = messageService.findAllMsgBySpec(msgDto);
        return ResponseEntity.status(HttpStatus.OK).body(allMsg);
    }

    @PostMapping("/link")
    public String linkToApplication(UUID appId) {
        Application app = appService.findAppById(appId);
        if (null != app) {
            return "redirect:/application/edit/" + appId;
        } 
        return ErrorResponse.MSG_NOT_FOUND;
    }
    



    

}
