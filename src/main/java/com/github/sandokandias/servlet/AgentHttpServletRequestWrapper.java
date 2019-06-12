package com.github.sandokandias.servlet;

import com.github.sandokandias.stream.InputStreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AgentHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] body;

    public AgentHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.body = InputStreamUtils.readByteArray(request.getInputStream());
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return byteArrayInputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public byte[] getBody() {
        return body;
    }
}
