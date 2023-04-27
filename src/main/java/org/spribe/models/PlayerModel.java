package org.spribe.models;

import lombok.*;

@Data
@Builder
public class PlayerModel {
    Integer id;
    String age;
    String editor;
    String gender;
    String login;
    String password;
    String role;
    String screenName;
}
