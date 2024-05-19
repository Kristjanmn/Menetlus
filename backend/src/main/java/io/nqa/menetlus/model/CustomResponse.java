package io.nqa.menetlus.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomResponse {
    private boolean success;
    private Object data;
    private String message;

    public CustomResponse(boolean success) {
        this.success = success;
    }

    public CustomResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public CustomResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
