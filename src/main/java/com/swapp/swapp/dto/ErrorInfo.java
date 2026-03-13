package com.swapp.swapp.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public record ErrorInfo(
    int code,
    String message,
    String details,
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    LocalDateTime time
) {
    public ErrorInfo(int code, String message) {
        this(code, message, null, LocalDateTime.now());
    }
}
