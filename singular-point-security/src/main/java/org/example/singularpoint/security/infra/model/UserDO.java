package org.example.singularpoint.security.infra.model;

import lombok.Data;

@Data
public class UserDO {

    private Long id;
    private String username;
    private String password;
    private String roles;
}
