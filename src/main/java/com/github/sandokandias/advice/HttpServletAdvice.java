package com.github.sandokandias.advice;

import com.github.sandokandias.collector.DataCollector;
import com.github.sandokandias.collector.PrinterDataCollector;
import net.bytebuddy.asm.Advice;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpServletAdvice {

    @Advice.OnMethodEnter
    public static void before(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("before serving the request...");
        ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(request);
        try {
            wrapper.getInputStream().read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] contentAsByteArray = wrapper.getContentAsByteArray();
        Map<String, String> headers = new HashMap<>();
        headers.put("contentType", wrapper.getContentType());

        DataCollector dataCollector = new PrinterDataCollector();
        dataCollector.collect(headers, contentAsByteArray);

    }

    @Advice.OnMethodExit
    public static void after(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("after serving the request...");
    }
}
