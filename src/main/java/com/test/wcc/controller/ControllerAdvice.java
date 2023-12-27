package com.test.wcc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.test.wcc.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.test.wcc.controller")
public class ControllerAdvice {

    private final MessageSource messageSource;

    public ControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @ExceptionHandler(BusinessException.class)
    ResponseEntity handleBusinessException(BusinessException ex) {
        log.trace("handling exception::" + ex);
        ObjectNode node = new ObjectMapper().createObjectNode();
        node.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(node);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity handleIllegalArgumentException(IllegalArgumentException ex) {
        log.trace("Handle InvalidArgumentException", ex);
        ObjectNode node = new ObjectMapper().createObjectNode();
        node.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(node);
    }


    @ExceptionHandler(RuntimeException.class)
    ResponseEntity handleRuntimeException(RuntimeException ex) {
        log.debug("handling exception::" + ex);
        ObjectNode node = new ObjectMapper().createObjectNode();
        node.put("message", "Internal server error!");
        return ResponseEntity.internalServerError().body(node);
    }

}
