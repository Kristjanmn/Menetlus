package io.nqa.menetlus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "menetlus")
public class Menetlus {
    @Id
    private long id;
    private String name;
    private long isikukood;
    private String email;
    private boolean emailDelivered;
    private String reason;
}
