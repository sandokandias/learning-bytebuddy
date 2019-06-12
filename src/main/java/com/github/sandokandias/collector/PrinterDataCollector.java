package com.github.sandokandias.collector;

import java.util.Map;

public class PrinterDataCollector implements DataCollector {

    @Override
    public void collect(Map<String, String> headers, byte[] content) {
        System.out.println("PrinterDataCollector - collecting data...");
        System.out.println("PrinterDataCollector - headers = " + headers);
        System.out.println("PrinterDataCollector - content = " + new String(content));
    }
}
