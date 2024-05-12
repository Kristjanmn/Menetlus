package io.nqa.menetlus.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "menetlus")
public class Menetlus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private long isikukood;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private boolean emailDelivered;
    @Column(nullable = false)
    private String reason;
}
