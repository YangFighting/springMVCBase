package com.yang.mvc.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhangyang
 * @date 2022/07/15 0:07
 **/

@Controller
public class HelloController {

    private static final Logger logger = LogManager.getLogger(HelloController.class);

    @RequestMapping(name = "/")
    public String index() {

        logger.warn("hello world");
        return "index";
    }


}
