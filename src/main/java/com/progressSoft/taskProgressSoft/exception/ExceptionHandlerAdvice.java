package com.progressSoft.taskProgressSoft.exception;

import com.progressSoft.taskProgressSoft.config.MessageBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Al-Shwayyat
 * For Exceptioin Handler
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger logger = LogManager.getLogger("");


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<MessageBody> handleException(BusinessException exception) {
        MessageBody messageBody = new MessageBody();
        messageBody.setStatus(exception.getStatus().value() + "");
        messageBody.setText(exception.getMessage());
        return new ResponseEntity<>(messageBody, exception.getStatus());
    }


//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<MessageBody> handleError(Exception exception) {
//        MessageBody messageBody = MessageBody.getInstance();
//        messageBody.setStatus("error");
//        messageBody.setText("Internal Error");
//        return new ResponseEntity<>(messageBody, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
