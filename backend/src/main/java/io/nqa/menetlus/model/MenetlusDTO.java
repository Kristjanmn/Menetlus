package io.nqa.menetlus.model;

import lombok.Data;

@Data
public class MenetlusDTO {
    private long id;
    private String name;
    private long isikukood;
    private String email;
    private boolean emailDelivered;
    private String reason;
}
