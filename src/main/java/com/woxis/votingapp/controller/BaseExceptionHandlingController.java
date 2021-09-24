package com.woxis.votingapp.controller;

import com.woxis.votingapp.dto.ErrorDTO;
import com.woxis.votingapp.exception.AlreadyExistsException;
import com.woxis.votingapp.exception.AlreadyPerformedException;
import com.woxis.votingapp.exception.IllegalEntityStateException;
import com.woxis.votingapp.exception.NotFoundException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
public abstract class BaseExceptionHandlingController {

  @ExceptionHandler(AlreadyExistsException.class)
  public ResponseEntity<ErrorDTO> handleAlreadyExistsException(AlreadyExistsException e) {
    log.warn(e.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDTO("conflict", e.getMessage()));
  }

  @ExceptionHandler(AlreadyPerformedException.class)
  public ResponseEntity<ErrorDTO> handleAlreadyPerformedException(AlreadyPerformedException e) {
    log.warn(e.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDTO("conflict", e.getMessage()));
  }

  @ExceptionHandler(IllegalEntityStateException.class)
  public ResponseEntity<ErrorDTO> handleIllegalStateException(IllegalEntityStateException e) {
    log.warn(e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("bad_request", e.getMessage()));
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorDTO> handleNotFoundException(NotFoundException e) {
    log.warn(e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("not_found", e.getMessage()));
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorDTO> handleRuntimeException(RuntimeException e) {
    log.warn(e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorDTO("internal_server_error", "something went wrong"));
  }

}
