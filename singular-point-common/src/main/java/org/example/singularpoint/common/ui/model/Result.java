package org.example.singularpoint.common.ui.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.example.singularpoint.common.ui.constant.MessageEnum;

@Data
public class Result<T> {

    private int code;
    private String message;
    private T data;

    public static <T> Result<T> success(){
        return success(null);
    }

    public static <T> Result<T> success(T data){
        return new Result<>(MessageEnum.SUCCESS.getCode(), MessageEnum.SUCCESS.getMessage(), data);
    }

    public static Result<Boolean> error(){
        return error(MessageEnum.ERROR);
    }

    public static Result<Boolean> error(MessageEnum message){
        return error(MessageEnum.ERROR.getCode(), message.getMessage());
    }

    public static Result<Boolean> error(String message){
        String resultMessage = StringUtils.isBlank(message) ? MessageEnum.ERROR.getMessage() : message;
        return error(MessageEnum.ERROR.getCode(), resultMessage);
    }

    public static Result<Boolean> error(int code, String message){
        return new Result<>(code, message, false);
    }

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
