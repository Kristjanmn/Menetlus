package io.nqa.menetlus.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menetlus")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menetlus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, length = 11)
    private long personalCode;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private boolean emailDelivered;
    @Column(nullable = false)
    private String reason;
}
