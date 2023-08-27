package com.cedacri.logisticssystem.exceptions.exceptionController;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.cedacri.logisticssystem.exceptions.customExceptions.EntityNotFoundException;
import com.cedacri.logisticssystem.exceptions.customExceptions.InvalidInputException;
import com.cedacri.logisticssystem.exceptions.customExceptions.NoDataFoundException;
import com.cedacri.logisticssystem.exceptions.errorModel.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//ControllerAdvice is an annotation introduced in Spring 3.2, and as the name suggests, is “Advice” for multiple controllers. It is used to enable a single ExceptionHandler to be applied to multiple controllers. This way we can in just one place define how to treat such an exception and this handler will be called when the exception is thrown from classes that are covered by this ControllerAdvice. The subset of controllers affected can defined by using the following selectors on @ControllerAdvice: annotations(), basePackageClasses(), and basePackages(). If no selectors are provided, then the ControllerAdvice is applied globally to all controllers.

    //So by using @ExceptionHandler and @ControllerAdvice, we’ll be able to define a central point for treating exceptions and wrapping them up in an ApiError object with better organization than the default Spring Boot error handling mechanism.

    //@ControllerAdvice is a specialization of the @Component annotation, so is auto-detected via classpath scanning, which allows to handle exceptions across the whole application in one global handling component. It can be viewed as an interceptor of exceptions thrown by methods annotated with @RequestMapping and similar.It declares @ExceptionHandler, @InitBinder, or @ModelAttribute methods to be shared across multiple @Controller classes.

    //ResponseEntityExceptionHandler is a convenient base class for @ControllerAdvice classes that wish to provide centralized exception handling across all @RequestMapping methods through @ExceptionHandler methods. It provides an methods for handling internal Spring MVC exceptions. It returns a ResponseEntity in contrast to DefaultHandlerExceptionResolver which returns a ModelAndView.---It provides exception handlers for internal Spring exceptions. If we don’t extend it, then all the exceptions will be redirected to DefaultHandlerExceptionResolver which returns a ModelAndView object. Since we are on the mission to shape our own error response, we don’t want that.
    //This enables a mechanism that breaks away from the older MVC model and makes use of ResponseEntity along with the type safety and flexibility of @ExceptionHandler:



@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice//promote a unified exception handling throughout a whole application.
//@ResponseStatus
//@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {



    //One thing to keep in mind here is to match the exceptions declared with @ExceptionHandler to the exception used as the argument of the method. If these don't match, the compiler will not complain — no reason it should — and Spring will not complain either. However, when the exception is actually thrown at runtime, the exception resolving mechanism will fail with:
    //java.lang.IllegalStateException: No suitable resolver for argument [0] [type=...] //HandlerMethod details: ...

    //ResponseEntityExceptionHandler as it already provides some basic handling of Spring MVC exceptions, so we’ll be adding handlers for new exceptions while improving the existing ones


//    API with an invalid id the service will throw a NoSuchElementFoundException runtime exception

//    in loc folosim slf4j din lombok
//    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvisor.class);
    //      logger.debug("Debug log message");
    //        logger.info("Info log message");
    //        logger.error("Invalid Input Exception: ");

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ResponseBody//signals that this advice is rendered straight into the response body.
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(NOT_FOUND)//says to issue an HttpStatus.NOT_FOUND
//    String employeeNotFoundHandler(EmployeeNotFoundException ex) {
//        return ex.getMessage();
//    }

    //Also, the annotation @ResponseStatus(HttpStatus.NOT_FOUND) on the handler method is not required as the HTTP status passed into the ResponseEnity will take precedence, but we have kept it anyway for the same readability reasons.
    //
    //Apart from the exception parameter, we can also have HttpServletRequest, WebRequest, or HttpSession types as parameters
//    protected ResponseEntity<ApiError>
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException enfe) {
        logger.error("No Entity Found: "+ enfe.getMessage());

        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(enfe.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NoDataFoundException.class)
    protected ResponseEntity<Object> handleNoDataFound(NoDataFoundException ex){
        log.error("No Data Found: " + ex.getMessage());

        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage("No Data available to retrieve");
        apiError.setDebugMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(InvalidInputException.class)
    protected ResponseEntity<Object> handleInvalidInput(
            InvalidInputException iie){
        ApiError apiError = new ApiError( HttpStatus.BAD_REQUEST );
                apiError.setMessage(iie.getMessage());
                apiError.setDebugMessage("Input should be valid..");
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NumberFormatException.class)
    protected ResponseEntity<Object> handleNumberFormatException(
            NumberFormatException nfe,
            WebRequest request) {
        log.error(nfe.getMessage());
        ApiError apiError = new ApiError( HttpStatus.BAD_REQUEST );
        apiError.setMessage(nfe.getMessage());
        apiError.setDebugMessage("Input should be a valid Integer type!");
        return buildResponseEntity(apiError);
    }


    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex,
            WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }


//    @Override
//    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
//        HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
//    }

//    @Override
//    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleMissingPathVariable(ex, headers, status, request);
    }

    //Pentru validation
    //The handleMethodArgumentNotValid handles the MethodArgumentNotValidException which is thrown when validation on an argument annotated with @Valid fails.
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request) {
//        return super.handleMethodArgumentNotValid(ex, headers, status, request);


//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError)error).getField();
//            String message = error.getDefaultMessage();
//            errors.put(fieldName, message);
//        });
//        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
//    }

    //handleExceptionInternal(): all the handlers in the ResponseEntityExceptionHandler use this function to build the ResponseEntity similar to our buildErrorResponse(). If we don’t override this then the clients will receive only the HTTP status in the response header but since we want to include the HTTP status in our response bodies as well, we have overridden the method.

//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(
//            Exception ex,
//            Object body,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request) {
//        return super.handleExceptionInternal(ex, body, headers, status, request);
//    }


    //HttpMessageNotReadableException
//    @Override
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(
//            HttpMessageNotReadableException ex,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request) {
//        String error = "Malformed JSON request";
//        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,error,ex));
//    }
//}

//// The status property: holds the operation call status. It will be anything from 4xx to signalize client errors or 5xx to mean server errors. A common scenario is a http code 400 that means a BAD_REQUEST, when the client, for example, sends an improperly formatted field, like an invalid email address.
//    // The timestamp property: holds the date-time instance of when the error happened.
//    // The message property: holds a user-friendly message about the error.
//    // The debugMessage property: holds a system message describing the error in more detail.
//    // The subErrors property: holds an array of sub-errors that happened. This is used for representing multiple errors in a single call. An example would be validation errors in which multiple fields have failed the validation. The ApiSubError class is used to encapsulate those.

//@ResponseBody- signals that this advice is rendered straight into the response body.,this tells a controller that the object returned is automatically serialized into JSON and passed it to the HttpResponse object. This way does not require ResponseEntity but you need to use @ResponseStatus to set the HTTP status code for that exception.
        //@ControllerAdvice
        //@ResponseBody
        //public class ControllerExceptionHandler {
        //  @ExceptionHandler(ResourceNotFoundException.class)
        //  @ResponseStatus(value = HttpStatus.NOT_FOUND)
        //  public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        //    ErrorMessage message = new ErrorMessage(...);
        //    return message;
        //  }
        //}
//@ExceptionHandler- configures the advice to only respond if an EmployeeNotFoundException is thrown.type safety and flexible

//@ResponseStatus- says to issue an HttpStatus.NOT_FOUND, i.e. an HTTP 404.
//The body of the advice generates the content. In this case, it gives the message of the exception.,.. It can be applied in the following places:
// On the exception class itself, Along with the @ExceptionHandler annotation on methods, Along with the @ControllerAdvice annotation on classes
    //Another way(@ResponseStatus) to achieve the same is by extending the ResponseStatusException class

//create a method called handleEntityNotFound() and annotate it with @ExceptionHandler, passing the class object EntityNotFoundException.class to it. This signalizes Spring that every time EntityNotFoundException is thrown, Spring should call this method to handle it. When annotating a method with @ExceptionHandler, it will accept a wide range of auto-injected parameters like WebRequest, Locale and others... We’ll just provide the exception EntityNotFoundException itself as a parameter for this handleEntityNotFound method.


//Spring 5 introduced the ResponseStatusException class.
    //@GetMapping(value = "/{id}")
    //public Foo findById(@PathVariable("id") Long id, HttpServletResponse response) {
    //    try {
    //        Foo resourceById = RestPreconditions.checkFound(service.findOne(id));
    //
    //        eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, response));
    //        return resourceById;
    //     }
    //    catch (MyResourceNotFoundException exc) {
    //         throw new ResponseStatusException(
    //           HttpStatus.NOT_FOUND, "Foo Not Found", exc);
    //    }
    //}
//What are the benefits of using ResponseStatusException?
    //Excellent for prototyping: We can implement a basic solution quite fast.
    //One type, multiple status codes: One exception type can lead to multiple different responses. This reduces tight coupling compared to the @ExceptionHandler.
    //We won't have to create as many custom exception classes.
    //We have more control over exception handling since the exceptions can be created programmatically.
//And what about the tradeoffs?
    //There's no unified way of exception handling: It's more difficult to enforce some application-wide conventions as opposed to @ControllerAdvice, which provides a global approach.
    //Code duplication: We may find ourselves replicating code in multiple controllers.
//We should also note that it's possible to combine different approaches within one application. For example, we can implement a @ControllerAdvice globally but also ResponseStatusExceptions locally.
//However, we need to be careful: If the same exception can be handled in multiple ways, we may notice some surprising behavior. A possible convention is to handle one specific kind of exception always in one way.

    //We will annotate @ResponseBody to the getUser() method. Now spring behind the scenes, uses HTTP message converter to convert a User object to HTTP response body with the help of jackson-databind
    //@Controller
    //public class UserController {
    //
    //	@GetMapping("/users")
    //	public @ResponseBody User getUser() {
    //		User user = new User();
    //		user.setFirstName("Bushan");
    //		user.setLastName("Sirgur");
    //		user.setAge(28L);
    //		return user;
    //	}
    //}
