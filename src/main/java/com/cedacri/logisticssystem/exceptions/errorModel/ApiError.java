package com.cedacri.logisticssystem.exceptions.errorModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

//    The status property: holds the operation call status. It will be anything from 4xx to signalize client errors or 5xx to mean server errors.
//          A common scenario is a http code 400 that means a BAD_REQUEST, when the client, for example, sends an improperly formatted field, like an invalid email address.
//    The timestamp property: holds the date-time instance of when the error happened.
//    The message property: holds a user-friendly message about the error.
//    The debugMessage property: holds a system message describing the error in more detail.


@Data
@AllArgsConstructor
public class ApiError {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private String requestedPath;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiError(HttpStatus status, String message, String debugMessage) {
        this(status);
        this.message = message;
        this.debugMessage = debugMessage;
    }

    public ApiError(HttpStatus status, String message, Throwable ex) {
        this(status, message, ex.getLocalizedMessage());
    }
}
