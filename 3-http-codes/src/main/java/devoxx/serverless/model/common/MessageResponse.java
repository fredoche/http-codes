package devoxx.serverless.model.common;

import lombok.Data;

@Data
public class MessageResponse {

    public static MessageResponse fromMessage(String input) {
        MessageResponse message = new MessageResponse();
        message.setMessage(input);
        return message;
    }

    private String message;

}
