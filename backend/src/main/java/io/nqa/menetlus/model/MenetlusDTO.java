package io.nqa.menetlus.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenetlusDTO {
    private long id;
    private String name;
    private long personalCode;
    private String email;
    private boolean emailDelivered;
    private String reason;
}
