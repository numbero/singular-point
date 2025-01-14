package org.example.detault.ui.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.detault.common.ExternalException;
import org.example.detault.ui.constant.MessageEnum;
import org.example.detault.ui.model.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<Boolean> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(ExternalException.class)
    public Result<Boolean> handleExternalException(ExternalException e) {
        log.error(e.getMessage(), e);
        String message = StringUtils.isBlank(e.getMessage()) ? MessageEnum.EXTERNAL_ERROR.getMessage() : e.getMessage();
        return Result.error(MessageEnum.EXTERNAL_ERROR.getCode(), message);
    }
}
