package com.github.sandokandias;

import net.bytebuddy.agent.ByteBuddyAgent;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.servlet.DispatcherServlet;

import static com.github.sandokandias.FakeTarget.FAKE;
import static com.github.sandokandias.SpringWebTarget.SPRING_WEB;

public class GodfatherAgentTest {

    @Test
    public void givenFakeTarget_whenFakeMethodIsCalled_thenExecuteCodeOnEnterAndExitMethod() throws Exception {
        ByteBuddyAgent.install();
        GodfatherAgent.premain(FAKE, ByteBuddyAgent.getInstrumentation());

        FakeTarget fakeTarget = new FakeTarget();
        fakeTarget.fake("buddy byte");
    }

    @Test
    public void givenSpringServletDispatcher_whenServiceMethodIsCalled_thenExecuteCodeOnEnterAndExitMethod() throws Exception {
        ByteBuddyAgent.install();
        GodfatherAgent.premain(SPRING_WEB, ByteBuddyAgent.getInstrumentation());

        MockServletConfig servletConfig = new MockServletConfig(new MockServletContext(), "simple");

        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setContextClass(SimpleWebApplicationContext.class);
        dispatcherServlet.init(servletConfig);

        MockHttpServletRequest request = new MockHttpServletRequest(dispatcherServlet.getServletContext(), "POST", "/simple");
        MockHttpServletResponse response = new MockHttpServletResponse();
        dispatcherServlet.service(request, response);
    }
}
