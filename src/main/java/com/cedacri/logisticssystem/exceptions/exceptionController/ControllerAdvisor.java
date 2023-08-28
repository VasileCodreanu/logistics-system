package com.cedacri.logisticssystem.exceptions.exceptionController;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.cedacri.logisticssystem.exceptions.customExceptions.EntityNotFoundException;
import com.cedacri.logisticssystem.exceptions.customExceptions.NoDataFoundException;
import com.cedacri.logisticssystem.exceptions.errorModel.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

//The subset of controllers affected can defined by using the following selectors on @ControllerAdvice: annotations(), basePackageClasses(),
    // and basePackages(). If no selectors are provided, then the ControllerAdvice is applied globally to all controllers.

//So by using @ExceptionHandler and @ControllerAdvice, we’ll be able to define a central point for treating exceptions
    // and wrapping them up in an ApiError object with better organization than the default Spring Boot error handling mechanism.

//@ControllerAdvice is a specialization of the @Component annotation, so is auto-detected via classpath scanning,
    // which allows to handle exceptions across the whole application in one global handling component.
    // It can be viewed as an interceptor of exceptions thrown by methods annotated with @RequestMapping and similar.
    // It declares @ExceptionHandler, @InitBinder, or @ModelAttribute methods to be shared across multiple @Controller classes.

//ResponseEntityExceptionHandler is a convenient base class for @ControllerAdvice classes that wish to provide centralized exception handling
    // across all @RequestMapping methods through @ExceptionHandler methods. It provides methods for handling internal Spring MVC exceptions.
    // It returns a ResponseEntity in contrast to DefaultHandlerExceptionResolver which returns a ModelAndView.
    // It provides exception handlers for internal Spring exceptions. If we don’t extend it, then all the exceptions will be redirected
    // to DefaultHandlerExceptionResolver which returns a ModelAndView object. Since we are on the mission to shape our own error response, we don’t want that.
    //This enables a mechanism that breaks away from the older MVC model and makes use of ResponseEntity along with the type safety and
    // flexibility of @ExceptionHandler:

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(annotations = RestController.class)//target all Controllers annotated with @RestController
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    //One thing to keep in mind here is to match the exceptions declared with @ExceptionHandler to the exception used as the argument of the method.
    // If these don't match, the compiler will not complain — no reason it should — and Spring will not complain either.
    // However, when the exception is actually thrown at runtime, the exception resolving mechanism will fail with:
    //java.lang.IllegalStateException: No suitable resolver for argument [0] [type=...] //HandlerMethod details: ...

    @ExceptionHandler(EntityNotFoundException.class)//API with an invalid id the service will throw a NoSuchElementFoundException runtime exception
    @ResponseStatus(NOT_FOUND)
    //The annotation @ResponseStatus(HttpStatus.NOT_FOUND) on the handler method is not required as the HTTP status passed into the ResponseEntity
    // will take precedence, but we have kept it anyway for readability reasons.
    //Apart from the exception parameter, we can also have HttpServletRequest, WebRequest, Locale, or HttpSession types as parameters

    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException enfe,
            final HttpServletRequest request) {
        logger.error("EntityNotFoundException: "+ enfe.getMessage());

        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage("No such element available to retrieve.");
        apiError.setDebugMessage(enfe.getMessage());
        apiError.setRequestedPath(request.getRequestURI());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NoDataFoundException.class)
    protected ResponseEntity<Object> handleNoDataFound(NoDataFoundException ex){
        log.error("NoDataFoundException" + ex.getMessage());

        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage("No Data available to retrieve");
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NumberFormatException.class)
    protected ResponseEntity<Object> handleNumberFormatException(
            NumberFormatException nfe,
            WebRequest request) {
        log.error("NumberFormatException" + nfe.getMessage());

        ApiError apiError = new ApiError( HttpStatus.BAD_REQUEST );
        apiError.setMessage(nfe.getMessage());
        apiError.setDebugMessage("Input should be a valid Integer type!");
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex,
            WebRequest request) {
        log.error("IllegalArgumentException/IllegalStateException" + ex.getMessage());

        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
    //
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(
            MissingPathVariableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        return super.handleMissingPathVariable(ex, headers, status, request);
    }

    //For validation  // MethodArgumentNotValidException is thrown when validation on an argument annotated with @Valid fails.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    //handleExceptionInternal(): all the handlers in the ResponseEntityExceptionHandler use this function to build the ResponseEntity
// similar to our buildErrorResponse(). If we don’t override this then the clients will receive only the HTTP status in the response header
// but since we want to include  the HTTP status in our response bodies as well, we have overridden the method.
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers,
            HttpStatusCode statusCode, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }
}