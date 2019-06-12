package com.github.sandokandias.advice;

import com.github.sandokandias.collector.DataCollector;
import com.github.sandokandias.collector.PrinterDataCollector;
import com.github.sandokandias.servlet.AgentHttpServletRequestWrapper;
import net.bytebuddy.asm.Advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpServletAdvice {

    @Advice.OnMethodEnter
    public static void before(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("before serving the request...");
        try {
            AgentHttpServletRequestWrapper wrapper = new AgentHttpServletRequestWrapper(request);
            byte[] contentAsByteArray = wrapper.getBody();
            Map<String, String> headers = new HashMap<>();
            headers.put("contentType", wrapper.getContentType());

            DataCollector dataCollector = new PrinterDataCollector();
            dataCollector.collect(headers, contentAsByteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Advice.OnMethodExit
    public static void after(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("after serving the request...");
    }
}
