package com.webapp.pgadmiss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class NavigationController {


    @GetMapping("/index")
    public String index() {
        return "index.html";
    }
    @GetMapping("/navigation")
    public String navigation() {
        return "index/navigation.html";
    }

    @GetMapping("/courseList")
    public String courseList() {
        return "index/list.html";
    }

    @GetMapping("/approvalList")
    public String approvalList() {
        return "approval/approval.html";
    }

    @GetMapping("/approvalEdit")
    public String approvalEdit() {
        return "approval/approvalEditing.html";
    }

    @GetMapping("/applicationList")
    public String applicationList() {
        return "application/applicationList.html";
    }

    @GetMapping("/applicationEdit")
    public String applicationEdit() {
        return "application/applicationEdit.html";
    }


    @GetMapping("/message")
    public String message() {
        return "message/message.html";
    }


}
