package com.yang.mvc.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

/**
 * @author zhangyang
 * @date 2022/07/15 0:07
 **/

@Controller
public class HelloController {

    private static final Logger logger = LogManager.getLogger(HelloController.class);

    @RequestMapping(name = "/", method = {RequestMethod.POST})
    public String index() {

        logger.warn("hello world");
        return "index";
    }

    @GetMapping("/")
    @ResponseBody
    public String getMapping(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "id", defaultValue = "0") Long id,
                             @RequestHeader(value = "cookie", defaultValue = "0") String cookie,
                             @CookieValue(name = "cookie", defaultValue = "0") String cookieTest) {

        logger.warn(MessageFormat.format("get Mapping name: {0}", name));
        logger.warn(MessageFormat.format("get Mapping id: {0}", id));
        logger.warn(MessageFormat.format("get Mapping  cookie: {0}", cookie));
        logger.warn(MessageFormat.format("get Mapping cookie_1: {0}", cookieTest));
        return "getMapping " + id;
    }

    @GetMapping("/cookie")
    @ResponseBody
    public String getcookie(@CookieValue(name = "cookie", defaultValue = "0") String cookie) {
        logger.warn(MessageFormat.format("get Mapping cookie: {0}", cookie));
        return "getMapping " + cookie;
    }


}
