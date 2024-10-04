package org.example.order.interfaces.rest;

import lombok.extern.slf4j.Slf4j;
import org.example.order.domain.exception.OrderDomainException;
import org.example.order.domain.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

@Slf4j
@RestControllerAdvice
public class OrderExceptionHandler {

    /**
     * 400
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, WebExchangeBindException.class})
    public ResponseEntity<OrderErrorResponse> badRequestHandler(BindingResult bindingResult){
        String msg = "";
        for(FieldError fieldError :bindingResult.getFieldErrors()){
            msg = fieldError.getDefaultMessage();
        }
        OrderErrorResponse apiResult = new OrderErrorResponse("400", msg);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResult);
    }


    /**
     * 404
     */
    @ExceptionHandler({OrderNotFoundException.class,})
    public ResponseEntity<OrderErrorResponse> notFoundHandler(RuntimeException ex){
        OrderErrorResponse apiResult = new OrderErrorResponse("400", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResult);
    }

    /**
     * 422
     */
    @ExceptionHandler({OrderDomainException.class})
    public ResponseEntity<OrderErrorResponse> unprocessableEntityHandler(RuntimeException ex){
        OrderErrorResponse apiResult = new OrderErrorResponse("400", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(apiResult);
    }

    /**
     * 500
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<OrderErrorResponse> internalServerErrorExceptionHandler(RuntimeException ex){
        OrderErrorResponse apiResult = new OrderErrorResponse("500", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResult);
    }


}
