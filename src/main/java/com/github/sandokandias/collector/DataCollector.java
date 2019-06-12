package com.github.sandokandias.collector;

import java.util.Map;

public interface DataCollector {
    void collect(Map<String, String> headers, byte[] content);
}
