package devoxx.serverless.model;

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
