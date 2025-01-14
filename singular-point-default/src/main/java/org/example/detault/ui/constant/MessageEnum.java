package org.example.detault.ui.constant;

import lombok.Getter;

@Getter
public enum MessageEnum {

    SUCCESS(0, "OK"),

    ERROR(500, "System Error"),
    EXTERNAL_ERROR(501, "External Error"),
    ;

    private final int code;
    private final String message;

    MessageEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
