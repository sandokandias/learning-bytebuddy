package com.github.sandokandias;

import org.junit.Assert;
import org.junit.Test;

import static com.github.sandokandias.FakeTarget.*;
import static com.github.sandokandias.SpringWebTarget.*;

public class AdviceMapTest {

    @Test
    public void shouldReturnAValidTargetBySpringWeb() {
        AdviceMap.TargetByClass target = new AdviceMap().getTargetByClass(SPRING_WEB);
        Assert.assertNotNull(target);
        Assert.assertEquals(SPRING_WEB_CLASS, target.clazz);
        Assert.assertEquals(SPRING_WEB_METHOD, target.method);
    }

    @Test
    public void shouldReturnAValidTargetByFake() {
        AdviceMap.TargetByClass target = new AdviceMap().getTargetByClass(FAKE);
        Assert.assertNotNull(target);
        Assert.assertEquals(FAKE_CLASS, target.clazz);
        Assert.assertEquals(FAKE_METHOD, target.method);
    }
}
