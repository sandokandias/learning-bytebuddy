package com.github.sandokandias.spring;

import com.github.sandokandias.GodfatherAgent;
import com.github.sandokandias.io.InputStreamUtils;
import net.bytebuddy.agent.ByteBuddyAgent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.InputStream;

import static com.github.sandokandias.target.SpringWebTestTarget.SPRING_WEB_TEST;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mvc;

    private String content;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("./payloads/4k.json");
        byte[] contentAsByteArray = InputStreamUtils.readByteArray(inputStream);
        content = new String(contentAsByteArray);

        ByteBuddyAgent.install();
        GodfatherAgent.premain(SPRING_WEB_TEST, ByteBuddyAgent.getInstrumentation());
    }

    @Test
    public void postSomeDataToControllerShouldBeInterceptedByAgent()
            throws Exception {

        mvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()));
    }
}
