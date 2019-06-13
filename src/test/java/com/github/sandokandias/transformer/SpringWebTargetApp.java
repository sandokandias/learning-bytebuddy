package com.github.sandokandias.transformer;

import com.github.sandokandias.GodfatherAgent;
import net.bytebuddy.agent.ByteBuddyAgent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.github.sandokandias.transformer.SpringWebTarget.SPRING_WEB;

@SpringBootApplication
public class SpringWebTargetApp {

    public static void main(String[] args) {
        try {
            ByteBuddyAgent.install();
            GodfatherAgent.premain(SPRING_WEB, ByteBuddyAgent.getInstrumentation());
            SpringApplication.run(SpringWebTargetApp.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
