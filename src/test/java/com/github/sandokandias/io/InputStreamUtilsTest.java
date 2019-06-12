package com.github.sandokandias.io;

import com.github.sandokandias.Timer;
import com.github.sandokandias.io.InputStreamUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamUtilsTest {

    private Timer timer;
    private InputStream inputStream;


    @Before
    public void setUp() {
        timer = new Timer();
        inputStream = getClass().getClassLoader().getResourceAsStream("./payloads/4k.json");
    }

    @Test
    public void testReadInputStreamWithByteArrayOutputStream() throws IOException {
        timer.start();

        byte[] byteArray = InputStreamUtils.readByteArray(inputStream);

        timer.stopAndPrint("InputStream read");

        Assert.assertNotNull(byteArray);
    }

    @Test
    public void testReadInputStreamWithReadableByteChannel() throws IOException {
        timer.start();

        byte[] byteArray = InputStreamUtils.readByteArrayByChannel(inputStream);

        timer.stopAndPrint("ReadableByteChannel read");

        Assert.assertNotNull(byteArray);
    }
}
