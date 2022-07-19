package com.yang.mvc.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.MessageFormat;
import java.util.Arrays;

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
    public String getCookie(@CookieValue(name = "cookie", defaultValue = "0") String cookie) {
        logger.warn(MessageFormat.format("get Mapping cookie: {0}", cookie));
        return "getCookie " + cookie;
    }

    @PostMapping("/postUser")
    @ResponseBody
    public String postUser(@RequestParam(name="file",required = false) MultipartFile file, String name) {
        // 解析 Content-Type为 multipart/form-data 的 POST请求, 需要配置 commons-fileupload
        // curl -L -X POST 'http://localhost:8080/helloWorld/postUser' -H 'Content-Type: multipart/form-data' -F 'name=xixi' -F 'file=test.xlsx'

        // 参数key名
        String keyName = file.getName();
        logger.warn("keyName: " + keyName);
        // 文件名
        String fileName = file.getOriginalFilename();

        logger.warn("name: " + name);
        String msg = MessageFormat.format("postUser: {0}", fileName);
        logger.warn(msg);
        return msg;
    }

    @PostMapping(value = "/postUser/files", headers = "content-type=multipart/form-data")
    @ResponseBody
    public String postUserFiles(@RequestParam(name="file",required = false) MultipartFile[] multipartFiles,
                                @RequestParam(name="name") String[] name) {
        // 解析 Content-Type为 multipart/form-data 的 POST请求, 需要配置 commons-fileupload
        // curl -L -X POST 'http://localhost:8080/helloWorld/postUser/files' -H 'Content-Type: multipart/form-data' -F 'name=xixi' -F 'file=@a.xlsx' -F 'file=@b.docx'

        for (MultipartFile file :
                multipartFiles) {
            // 参数key名
            String keyName = file.getName();
            logger.warn("keyName: " + keyName);
            // 文件名
            String fileName = file.getOriginalFilename();
            logger.warn("fileName: " + fileName);
        }


        logger.warn("name: " + Arrays.toString(name));
        String msg = MessageFormat.format("postUser: {0}", Arrays.toString(name));
        logger.warn(msg);
        return msg;
    }

    @PostMapping(value = "/postUserFormUrlEncoded", headers = "content-type=application/x-www-form-urlencoded")
    @ResponseBody
    public String postUserFormUrlEncoded(@RequestParam(name="name") String name) {
        // 解析 Content-Type为 application/x-www-form-urlencoded 的 POST请求
        //curl -L -X POST 'http://localhost:8080/helloWorld/postUserFormUrlEncoded' -H 'Content-Type: application/x-www-form-urlencoded' --data-urlencode 'name=zz'

        // curl -L -X POST 'http://localhost:8080/helloWorld/postUserFormUrlEncoded?name=12' -H 'Content-Type: application/x-www-form-urlencoded' --data-urlencode 'name=zz'
        logger.warn("name: " + name);
        String msg = MessageFormat.format("postUserFormUrlEncoded: {0}", name);
        logger.warn(msg);
        return msg;
    }

    @DeleteMapping("/delUser")
    @ResponseBody
    public String delUser(@RequestBody String reqBoy) {
        // curl -L -X DELETE 'http://localhost:8080/helloWorld/delUser' --data-raw '{
        //    "test": 1
        //}'
        String msg = MessageFormat.format("delUser: {0}", reqBoy);
        logger.warn(msg);
        return msg;
    }

    @GetMapping("/testInterceptor")
    @ResponseBody
    public String testInterceptor() {
        logger.warn("testInterceptor");
        return "testInterceptor";
    }


}
