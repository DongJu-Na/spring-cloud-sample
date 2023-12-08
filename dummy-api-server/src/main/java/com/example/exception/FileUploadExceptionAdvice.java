package com.example.exception;

import com.example.dto.ResponseMessage;
import org.springframework.core.io.buffer.DataBufferLimitException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataBufferLimitException.class)
    public ResponseEntity<ResponseMessage> handleMaxSizeException(DataBufferLimitException exc) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("서버에 업로드 할 수 있는 파일 용량을 초과 하였습니다."));
    }

}
