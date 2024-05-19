package io.nqa.menetlus.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MqEmailPayload implements Serializable {
    private long id;
    private String email;
    private boolean sent;
}
