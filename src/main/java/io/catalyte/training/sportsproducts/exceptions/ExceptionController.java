package io.catalyte.training.sportsproducts.exceptions;

import static io.catalyte.training.sportsproducts.constants.StringConstants.BAD_REQUEST;
import static io.catalyte.training.sportsproducts.constants.StringConstants.CONFLICT;
import static io.catalyte.training.sportsproducts.constants.StringConstants.NOT_FOUND;
import static io.catalyte.training.sportsproducts.constants.StringConstants.SERVER_ERROR;
import static io.catalyte.training.sportsproducts.constants.StringConstants.SERVICE_UNAVAILABLE;

import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * A controller advice allows you to use exactly the same exception handling techniques but apply
 * them across the whole application, not just to an individual controller. You can think of them as
 * an annotation driven interceptor. More info: https://www.baeldung.com/exception-handling-for-rest-with-spring
 *
 * <p>Handles exception responses for HTTP codes 400 (Bad Request), 404(Not Found), 409 (Conflict),
 * and 500(Server Error).
 */
@ControllerAdvice
public class ExceptionController {

  /**
   * @param exception response thrown
   * @return string NOT_FOUND, date, and exception message
   */
  @ExceptionHandler(ResourceNotFound.class)
  protected ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFound exception) {
    ExceptionResponse response =
        new ExceptionResponse(NOT_FOUND, new Date(), exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  /**
   * @param exception response thrown
   * @return string constant BAD_REQUEST, date, and exception message
   */
  @ExceptionHandler(BadRequest.class)
  protected ResponseEntity<ExceptionResponse> badRequest(BadRequest exception) {
    ExceptionResponse response =
        new ExceptionResponse(BAD_REQUEST, new Date(), exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  /**
   * @param ex response thrown
   * @return string constant BAD_REQUEST, date, and exception message
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ExceptionResponse> argumentsNotValid(
      MethodArgumentNotValidException ex) {
    String message = parseMessage(ex);
    ExceptionResponse response = new ExceptionResponse(BAD_REQUEST, new Date(), message);

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  /**
   * @param exception response thrown
   * @return string constant CONFLICT, date, and exception message
   */
  @ExceptionHandler(Conflict.class)
  protected ResponseEntity<ExceptionResponse> conflict(Conflict exception) {
    ExceptionResponse response =
        new ExceptionResponse(CONFLICT, new Date(), exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }

  /**
   * @param exception response thrown
   * @return string constant SERVER_ERROR, date, and exception message
   */
  @ExceptionHandler(ServerError.class)
  protected ResponseEntity<ExceptionResponse> serverError(ServerError exception) {
    ExceptionResponse response =
        new ExceptionResponse(SERVER_ERROR, new Date(), exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * @param exception response thrown
   * @return string constant SERVICE_UNAVAILABLE, date, and exception message
   */
  @ExceptionHandler(ServiceUnavailable.class)
  protected ResponseEntity<ExceptionResponse> serviceUnavailable(ServiceUnavailable exception) {
    ExceptionResponse response =
        new ExceptionResponse(SERVICE_UNAVAILABLE, new Date(), exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
  }

  /**
   * @param ex exception response.
   * @return the fields that caused the response as a string.
   */
  private String parseMessage(MethodArgumentNotValidException ex) {
    List<FieldError> errors = ex.getBindingResult().getFieldErrors();
    StringBuilder message = new StringBuilder();
    for (FieldError err : errors) {
      message.append(err.getDefaultMessage());
      message.append(" ");
    }
    return message.toString().trim();
  }
}
