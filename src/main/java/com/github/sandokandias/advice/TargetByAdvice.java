package com.github.sandokandias.advice;

public class TargetByAdvice {
    public final String clazz;
    public final String method;
    public final Class<?> adviceClazz;

    TargetByAdvice(String clazz, String method, Class adviceClazz) {
        this.clazz = clazz;
        this.method = method;
        this.adviceClazz = adviceClazz;
    }
}
