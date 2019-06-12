package com.github.sandokandias;

import net.bytebuddy.asm.Advice;

public class FakeAdvice {

    @Advice.OnMethodEnter
    public static void before(String param) {
        System.out.println("before fake method with original param:  " + param);
    }

    @Advice.OnMethodExit
    public static void after(String param) {
        System.out.println("after fake method with changed param:  " + param);
    }
}
