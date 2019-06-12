package com.github.sandokandias;

import java.util.HashMap;
import java.util.Map;

import static com.github.sandokandias.FakeTarget.*;
import static com.github.sandokandias.SpringWebTarget.*;

public class AdviceMap {

    private final Map<String, TargetByClass> MAP = new HashMap<>(2);

    public AdviceMap() {
        MAP.put(FAKE, new TargetByClass(FAKE_CLASS, FAKE_METHOD, FakeAdvice.class));
        MAP.put(SPRING_WEB, new TargetByClass(SPRING_WEB_CLASS, SPRING_WEB_METHOD, ServletAdvice.class));
    }

    public TargetByClass getTargetByClass(String key) {
        return MAP.get(key);
    }

    static class TargetByClass {
        public final String clazz;
        public final String method;
        public final Class<?> adviceClazz;

        TargetByClass(String clazz, String method, Class adviceClazz) {
            this.clazz = clazz;
            this.method = method;
            this.adviceClazz = adviceClazz;
        }
    }
}
