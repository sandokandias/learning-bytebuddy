package com.github.sandokandias.advice;

import java.util.HashMap;
import java.util.Map;

import static com.github.sandokandias.transformer.FakeTarget.*;
import static com.github.sandokandias.transformer.SpringWebTarget.*;
import static com.github.sandokandias.transformer.SpringWebTestTarget.*;

public class AdviceMap {

    private final Map<String, TargetByAdvice> MAP = new HashMap<>(2);

    public AdviceMap() {
        MAP.put(FAKE, new TargetByAdvice(FAKE_CLASS, FAKE_METHOD, FakeAdvice.class));
        MAP.put(SPRING_WEB, new TargetByAdvice(SPRING_WEB_CLASS, SPRING_WEB_METHOD, HttpServletAdvice.class));
        MAP.put(SPRING_WEB_TEST, new TargetByAdvice(SPRING_WEB_TEST_CLASS, SPRING_WEB_TEST_METHOD, HttpServletAdvice.class));
    }

    public TargetByAdvice getTargetByClass(String key) {
        return MAP.get(key);
    }
}
