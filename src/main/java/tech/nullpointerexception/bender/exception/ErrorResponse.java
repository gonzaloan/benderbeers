package tech.nullpointerexception.bender.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private Integer code;
    private String message;
    private List<?> details;

    public ErrorResponse(LocalDateTime timestamp, Integer code, String message, List<?> details) {
        this.timestamp = timestamp;
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getDetails() {
        return details;
    }

    public void setDetails(List<?> details) {
        this.details = details;
    }
}
