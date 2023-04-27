package org.spribe.dto;

import lombok.Data;

@Data
public class PlayerDto {
    private int id;
    private String login;
    private String password;
    private String screenName;
    private String gender;
    private int age;
    private String role;
}
