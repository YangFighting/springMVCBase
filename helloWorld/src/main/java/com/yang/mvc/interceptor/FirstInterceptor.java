package com.yang.mvc.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;

/**
 * @author zhangyang
 * @date 2022/07/19 22:56
 **/
@Component
public class FirstInterceptor implements HandlerInterceptor {

    private static final Logger logger = LogManager.getLogger(FirstInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.warn(" ---- preHandle ---- ");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry :
                parameterMap.entrySet()) {
            String format = MessageFormat.format("parameterMap key: {0}, value: {1}", entry.getKey(), Arrays.toString(entry.getValue()));
            logger.warn(format);
        }
        logger.warn(" ---- postHandle ---- ");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.warn(" ---- afterCompletion ---- ");

    }
}
