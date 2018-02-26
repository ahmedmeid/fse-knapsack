package com.maerskdigital.task.rest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.maerskdigital.task.domain.OperationResponse;
import com.maerskdigital.task.domain.OperationResponse.ResponseStatusEnum;
import com.maerskdigital.task.exceptions.ResourceNotFoundException;

@ControllerAdvice
@RestController
public class ResourceNotFoundExceptionHandler {
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public OperationResponse handleBaseException(ResourceNotFoundException e){
        OperationResponse resp = new OperationResponse();
        resp.setOperationStatus(ResponseStatusEnum.ERROR);
        resp.setOperationMessage(e.getMessage());
        return resp;
    }

}