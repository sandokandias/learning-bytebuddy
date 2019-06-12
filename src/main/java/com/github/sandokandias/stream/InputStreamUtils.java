package com.github.sandokandias.stream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class InputStreamUtils {

    public static byte[] readByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        buffer.close();
        return buffer.toByteArray();
    }

    public static byte[] readByteArrayByChannel(InputStream inputStream) throws IOException {
        java.nio.ByteBuffer buffer = java.nio.ByteBuffer.allocate(inputStream.available());
        try (ReadableByteChannel channel = Channels.newChannel(inputStream)) {
            channel.read(buffer);
        }
        return buffer.array();
    }
}
