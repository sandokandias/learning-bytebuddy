package com.github.sandokandias;

import com.github.sandokandias.advice.AdviceMap;
import com.github.sandokandias.advice.TargetByAdvice;
import net.bytebuddy.agent.builder.AgentBuilder;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.RETRANSFORMATION;
import static net.bytebuddy.asm.Advice.to;
import static net.bytebuddy.matcher.ElementMatchers.hasSuperType;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class GodfatherAgent {

    private static final AdviceMap ADVICE_MAP = new AdviceMap();

    public static void premain(String agentArgs, Instrumentation inst) throws Exception {
        TargetByAdvice target = ADVICE_MAP.getTargetByClass(agentArgs);

        new AgentBuilder.Default().disableClassFormatChanges()
                .with(RETRANSFORMATION)
                .type(hasSuperType(named(target.clazz)))
                .transform((builder, typeDescription, classLoader, module) ->
                        builder.visit(to(target.adviceClazz).on(named(target.method)))
                ).installOn(inst);
    }

    public static void agentmain(String agentArgs, Instrumentation inst) throws Exception {
        premain(agentArgs, inst);
    }
}
