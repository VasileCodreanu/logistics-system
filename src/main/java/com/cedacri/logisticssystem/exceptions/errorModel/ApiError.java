package com.cedacri.logisticssystem.exceptions.errorModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
public class ApiError {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;//debug message
    private List<ApiSubError> subErrors; // property holds an array of sub-errors that happened. This is used for representing multiple errors in a single call. An example would be validation errors in which multiple fields have failed the validation. The ApiSubError class is used to encapsulate those.
    //(ValidationError)
    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiError(HttpStatus status, Throwable ex) {
        this(status);
//        this();
//        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiError(HttpStatus status, String message, String debugMessage) {
        this.status = status;
        this.message = message;
        this.debugMessage = debugMessage;
    }
    //    private void addSubError(ApiSubError subError) {
//        if (subErrors == null) {
//            subErrors = new ArrayList<>();
//        }
//        subErrors.add(subError);
//    }
//
//    private void addValidationError(String object, String field, Object rejectedValue, String message) {
//        addSubError(new ApiValidationError(object, field, rejectedValue, message));
//    }
//
//    private void addValidationError(String object, String message) {
//        addSubError(new ApiValidationError(object, message));
//    }
//
//    private void addValidationError(FieldError fieldError) {
//        this.addValidationError(
//                fieldError.getObjectName(),
//                fieldError.getField(),
//                fieldError.getRejectedValue(),
//                fieldError.getDefaultMessage());
//    }
//
//    public void addValidationErrors(List<FieldError> fieldErrors) {
//        fieldErrors.forEach(this::addValidationError);
//    }
//
//    private void addValidationError(ObjectError objectError) {
//        this.addValidationError(
//                objectError.getObjectName(),
//                objectError.getDefaultMessage());
//    }
//
//    public void addValidationError(List<ObjectError> globalErrors) {
//        globalErrors.forEach(this::addValidationError);
//    }
//
//    /**
//     * Utility method for adding error of ConstraintViolation. Usually when a @Validated validation fails.
//     *
//     * @param cv the ConstraintViolation
//     */
//    private void addValidationError(ConstraintViolation<?> cv) {
//        this.addValidationError(
//                cv.getRootBeanClass().getSimpleName(),
//                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
//                cv.getInvalidValue(),
//                cv.getMessage());
//    }
//
//    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
//        constraintViolations.forEach(this::addValidationError);
//    }


}
