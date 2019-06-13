package com.github.sandokandias.transformer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class SpringWebTargetController {

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PostMapping(value = "/", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Map<String, String> post(@RequestBody Map<String, Object> body) {
        return new HashMap<String, String>() {{
            put("id", UUID.randomUUID().toString());
        }};
    }
}
