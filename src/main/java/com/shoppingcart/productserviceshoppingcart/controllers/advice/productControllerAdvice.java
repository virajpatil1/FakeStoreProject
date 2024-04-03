package com.shoppingcart.productserviceshoppingcart.controllers.advice;

import com.shoppingcart.productserviceshoppingcart.exceptions.ExcpetionDto;
import com.shoppingcart.productserviceshoppingcart.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class productControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    private ExcpetionDto handleProductNotFoundException(ProductNotFoundException e) {

        ExcpetionDto excpetionDto = new ExcpetionDto();
        excpetionDto.setMessage(e.getMessage());
        excpetionDto.setStatus("failure");

        return excpetionDto;

    }
}

