package org.example.singularpoint.ui;

import org.example.singularpoint.common.ExternalException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping
    public void throwException() {
        throw new RuntimeException("test exception");
    }

    @GetMapping("/external-exception")
    public void throwExternalException() throws ExternalException {
        throw new ExternalException("test external exception");
    }
}
