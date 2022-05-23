/*
 * InstaRem Overseas Money Transfer.
 * https://www.instarem.com/en-in/
 *
 * Copyright (c) 2014-2019 InstaReM
 *
 * InstaRem is an acronym of Instant Remittance.
 * InstaRem Software is designed and developed to ease the Overseas Money Transfer.
 * It allows you to transfer your money overseas with minimum cost and time.
 *
 *
 * This file is licensed and cannot be accessed by outside InstaRem.
 * It can only be accessed and modified by the authorized InstaRem Technical Teams.
 * If any unauthorized, outside of the InstaRem, user found to be unlawfully modified
 * the content of this file,  will be prosecuted under the Copyright Act
 *
 */
package com.wallet.zokudo.config;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wallet.zokudo.dto.response.ApiError;
import com.wallet.zokudo.dto.response.TransactionResponse;
import com.wallet.zokudo.exceptions.*;
import com.wallet.zokudo.mappers.TransactionResponseMapper;
import com.wallet.zokudo.util.CommonUtil;
import com.wallet.zokudo.util.M2PEncryption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class WebExceptionHandler {

    private final TransactionResponseMapper transactionResponseMapper;
    private final M2PEncryption m2PEncryption;
    private final String encryptToken;

    @Autowired
    public WebExceptionHandler(final TransactionResponseMapper transactionResponseMapper, final M2PEncryption m2PEncryption,
                               @Value(value = "${token}") final String token) {
        this.transactionResponseMapper = transactionResponseMapper;
        this.m2PEncryption = m2PEncryption;
        this.encryptToken = token;
    }

    @ExceptionHandler(BizException.class)
    public ResponseEntity<Object> bizException(final BizException e) {
        log.error(e.getMessage(), e);
        final ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getErrorMessage(), e.getAdditionalMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> dataNotFoundException(final DataNotFoundException e) {
        log.error(e.getMessage(), e);
        final ApiError error = new ApiError(HttpStatus.NOT_FOUND, e.getErrorMessage(), e.getAdditionalMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badRequestException(final BadRequestException e) {
        log.error(e.getMessage(), e);
        final ApiError error = new ApiError(HttpStatus.BAD_REQUEST, e.getErrorMessage(), e.getAdditionalMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        List<String> errors = Lists.newArrayList();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            errors.add(fieldError.getDefaultMessage());
        }
        final ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Arguments are invalid", errors);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> httpMessageNotReadableException(final HttpMessageNotReadableException e) {
        log.error(e.getMessage(), e);
        final ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMostSpecificCause().getMessage(), e.getMostSpecificCause().getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex) {
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
                    + violation.getMessage());
        }
        final ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), errors);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> exception(final Exception e) {
        log.error(e.getMessage(), e);
        final ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TransactionAuthException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> exception(final TransactionAuthException e, HttpServletRequest request) {

            if(request.getRequestURI().matches(".*\\bplaintext\\b.*"))
                return transactionResponseMapper.map(e);
            else{
                log.error(e.getMessage(), e);
                ResponseEntity<?> transactionResponse = transactionResponseMapper.map(e);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonOutput = gson.toJson(transactionResponse.getBody());
                log.error("Error message {}", jsonOutput);
                return new ResponseEntity<>(m2PEncryption.encodeRequest(jsonOutput, CommonUtil.randomString(16,""), encryptToken).toString(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
/*
        return new ResponseEntity<>(TransactionResponse.builder()
                .txnRefNo(e.getTransactionId())
                .status(e.getErrorCode())
                .description(e.getMessage())
                .errorCode(e.getErrorCode()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
*/
      //  return transactionResponseMapper.map(e);

    }

   /* @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> apiException(final ApiException e) {
        log.error(e.getMessage(), e);
        final ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> apiException(final ApiException e) {
        log.error(e.getMessage(), e);
        final ApiError error = new ApiError(e.getStatus(), e.getMessage(), e.getLocalizedMessage());
        return new ResponseEntity<>(error, e.getStatus());
    }
}
