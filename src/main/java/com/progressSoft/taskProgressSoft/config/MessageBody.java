package com.progressSoft.taskProgressSoft.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Mohammad Al-Shwayyat
 * for resonse message
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageBody {

    private static MessageBody messageBody = null;

    private String status;
    private String text;
    private Object body;


    public static MessageBody getInstance() {
        if (messageBody == null) {
            messageBody = new MessageBody();
        }

        messageBody.setBody(null);

        return messageBody;
    }
}
