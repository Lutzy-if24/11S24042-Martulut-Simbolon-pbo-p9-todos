package org.delcom.app.configs;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public ResponseEntity<Map<String, Object>> handleError(ServletWebRequest webRequest) {
        Map<String, Object> attributes;
        try {
            attributes = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
        } catch (Exception e) {
            attributes = Collections.emptyMap();
        }

        int status = (int) attributes.getOrDefault("status", 500);
        String path = (String) attributes.getOrDefault("path", "unknown");

        Map<String, Object> body = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status == 500 ? "error" : "fail",
                "error", attributes.getOrDefault("error", "Unknown Error"),
                "message", "Endpoint tidak ditemukan atau terjadi error",
                "path", path
        );

        return new ResponseEntity<>(body, HttpStatus.valueOf(status));
    }
}
