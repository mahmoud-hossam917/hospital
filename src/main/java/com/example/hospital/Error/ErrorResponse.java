package com.example.hospital.Error;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String message;
    private boolean success;



    private LocalDateTime dateTime;
    private List<String> details;

    public ErrorResponse(String message, List<String> details) {
        this.message = message;
        this.details = details;
        this.success =Boolean.FALSE;
        this.dateTime = LocalDateTime.now();
    }
}
