package org.example.singularpoint.infra;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.singularpoint.domain.User;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
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

    public UserDO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.age = user.getAge();
        this.birthDay = user.getBirthDay();
    }

    public User toUser() {
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setEmail(this.email);
        user.setAge(this.age);
        user.setBirthDay(this.birthDay);
        return user;
    }
}
