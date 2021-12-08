package com.progressSoft.taskProgressSoft.controllers;

import com.progressSoft.taskProgressSoft.config.MessageBody;
import com.progressSoft.taskProgressSoft.config.MyResourceBundle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Locale;

public interface Controllable {

    default ResponseEntity<MessageBody> responseMessage(String text, HttpStatus status, Object body, Locale locale) {
        MessageBody messageBody = new MessageBody();
        messageBody.setText(MyResourceBundle.getString(text, locale));
        messageBody.setBody(body);
        messageBody.setStatus(status.value() + "");
        return new ResponseEntity<>(messageBody, status);
    }

}
