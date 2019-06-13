package com.github.sandokandias.advice;

import org.junit.Assert;
import org.junit.Test;

import static com.github.sandokandias.transformer.FakeTarget.*;
import static com.github.sandokandias.transformer.SpringWebTarget.*;

public class AdviceMapTest {

    @Test
    public void shouldReturnAValidTargetBySpringWeb() {
        TargetByAdvice target = new AdviceMap().getTargetByClass(SPRING_WEB);
        Assert.assertNotNull(target);
        Assert.assertEquals(SPRING_WEB_CLASS, target.clazz);
        Assert.assertEquals(SPRING_WEB_METHOD, target.method);
    }

    @Test
    public void shouldReturnAValidTargetByFake() {
        TargetByAdvice target = new AdviceMap().getTargetByClass(FAKE);
        Assert.assertNotNull(target);
        Assert.assertEquals(FAKE_CLASS, target.clazz);
        Assert.assertEquals(FAKE_METHOD, target.method);
    }
}
