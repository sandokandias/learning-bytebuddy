package com.github.sandokandias.advice;

import com.github.sandokandias.io.InputStreamUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;

import java.io.IOException;
import java.io.InputStream;

public class HttpServletAdviceTest {

    private byte[] contentAsByteArray;

    @Before
    public void setUp() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("./payloads/4k.json");
        contentAsByteArray = InputStreamUtils.readByteArray(inputStream);
    }

    @Test
    public void shouldCollectDataBeforeServiceMethod() {
        MockHttpServletRequest request = new MockHttpServletRequest(new MockServletContext(), "POST", "/simple");
        request.setContentType("application/json");
        request.setContent(contentAsByteArray);
        MockHttpServletResponse response = new MockHttpServletResponse();
        HttpServletAdvice.before(request, response);
    }
}
