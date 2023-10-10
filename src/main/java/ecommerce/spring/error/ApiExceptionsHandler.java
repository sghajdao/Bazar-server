package ecommerce.spring.error;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiBaseException.class)
    public ResponseEntity<ErrorDetails> handleApiException(NotFoundException ex, WebRequest request) {
        ErrorDetails details = new ErrorDetails(ex.getMessage(), request.getDescription(false), new Date());
        return new ResponseEntity<ErrorDetails>(details, ex.getStatusCode());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ValidationError validationError = new ValidationError();
        validationError.setUri(request.getDescription(false));

        List<FieldError> fieldError = ex.getBindingResult().getFieldErrors();
        for (FieldError f : fieldError) {
            validationError.addError(f.getDefaultMessage());
        }
        return new ResponseEntity<Object>(validationError, HttpStatus.BAD_REQUEST);
    }
}