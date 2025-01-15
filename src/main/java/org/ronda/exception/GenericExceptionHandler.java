package org.ronda.exception;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GenericExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> handleException(Exception exception) {

    log.error("Something unexpected happened", exception);

    var body = getErrorBody(exception.getMessage());

    if (exception instanceof Exception) { // TODO tbd
      return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<>(getErrorBody("unknown"), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private static Map<String, String> getErrorBody(String exception) {
    return Optional.ofNullable(exception)
        .map(message -> Map.of("error", message))
        .orElse(Collections.emptyMap());
  }
}
