package com.project.juntas.exception.handler;

import com.project.juntas.exception.ResourceAlreadyExistsException;
import com.project.juntas.exception.ResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;

import static com.project.juntas.exception.handler.ResponseBuilder.responseBuilder;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({ ChangeSetPersister.NotFoundException.class,
            ResourceNotFoundException.class
    })
    public ResponseEntity<?> notFoundHandler(HttpServletRequest request, Exception exception) {
        return responseBuilder(NOT_FOUND, request.getRequestURI(), new ErrorDetail(exception));
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler({ HttpClientErrorException.Forbidden.class })
    public ResponseEntity<?> forbiddenHandler(HttpServletRequest request, Exception exception) {
        return responseBuilder(FORBIDDEN, request.getRequestURI(), new ErrorDetail(exception));
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler({ HttpClientErrorException.Unauthorized.class })
    public ResponseEntity<?> unauthorizedHandler(HttpServletRequest request, Exception exception) {
        return responseBuilder(FORBIDDEN, request.getRequestURI(), new ErrorDetail(exception));
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler({ResourceAlreadyExistsException.class,
            BadCredentialsException.class})
    public ResponseEntity<?> alreadyExistsHandler(HttpServletRequest request, Exception exception) {
        return responseBuilder(CONFLICT, request.getRequestURI(), new ErrorDetail(exception));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({ ArithmeticException.class,
            MissingRequestHeaderException.class,
            MethodArgumentNotValidException.class,
            NullPointerException.class,
            IllegalArgumentException.class,
            IndexOutOfBoundsException.class,
    })
    public ResponseEntity<?> badRequestHandler(HttpServletRequest request, Exception exception) {
        return responseBuilder(BAD_REQUEST, request.getRequestURI(), new ErrorDetail(exception));
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> internalServerErrorHandler(HttpServletRequest request, Exception exception) {
        return responseBuilder(INTERNAL_SERVER_ERROR, request.getRequestURI(), new ErrorDetail(exception));
    }
}
