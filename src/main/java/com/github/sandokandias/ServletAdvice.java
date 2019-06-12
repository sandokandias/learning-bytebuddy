package com.github.sandokandias;

import net.bytebuddy.asm.Advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletAdvice {

    @Advice.OnMethodEnter
    public static void before(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("before serving the request...");
    }

    @Advice.OnMethodExit
    public static void after(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("after serving the request...");
    }
}
