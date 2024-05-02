package com.webapp.pgadmiss.controller;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping(path = "/msg", method=RequestMethod.GET, produces = "application/json")
public class MessageController implements Serializable{

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    

}
