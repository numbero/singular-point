package org.example.singularpoint.infra;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user",
        indexes = {
                @Index(name = "uk_name", columnList = "name", unique = true),
                @Index(name = "idx_email", columnList = "email")
        })
public class UserDO extends BaseDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(64) comment '用户名'")
    private String name;

    @Column(nullable = false, columnDefinition = "varchar(255) comment '邮箱'")
    private String email;

    @Transient
    private int age;

    private LocalDate birthDay;
}
