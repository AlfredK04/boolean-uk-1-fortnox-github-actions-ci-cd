package dev.wows.buk.JavaCiCd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class StatusController {
    @GetMapping ("/api/status")
    public String getStatus() {
        return "Service is Active";
    }
}
