package com.github.sandokandias.advice;

import com.github.sandokandias.collector.DataCollector;
import com.github.sandokandias.collector.PrinterDataCollector;
import com.github.sandokandias.servlet.AgentHttpServletRequestWrapper;
import net.bytebuddy.asm.Advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class HttpServletAdvice {

    @Advice.OnMethodEnter
    public static void before(@Advice.Argument(0) HttpServletRequest request,
                              @Advice.Argument(1) HttpServletResponse response) {
        System.out.println("before serving the request...");
        try {
            AgentHttpServletRequestWrapper wrapper = new AgentHttpServletRequestWrapper(request);
            Map<String, String> headers = new HashMap<>();
            Enumeration<String> headerNames = wrapper.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String header = wrapper.getHeader(headerName);
                headers.put(headerName, header);
            }
            DataCollector dataCollector = new PrinterDataCollector();
            dataCollector.collect(headers, wrapper.getBody());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Advice.OnMethodExit
    public static void after(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("after serving the request...");
    }
}
