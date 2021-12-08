package com.progressSoft.taskProgressSoft.exception;


import com.progressSoft.taskProgressSoft.config.MyResourceBundle;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.Locale;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private HttpStatus status;
    private String message;
    private Locale locale;


    public BusinessException(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public BusinessException(HttpStatus status, String message, Locale locale) {
        super();
        this.status = status;
        this.message = MyResourceBundle.getString(message, locale);
        this.locale = locale;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
