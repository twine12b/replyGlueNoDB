package com.replyglue.app.domain;

import lombok.*;
import org.springframework.lang.Nullable;
import java.time.LocalDate;

@Getter  @Setter  @ToString
@NoArgsConstructor  @AllArgsConstructor
public class User {
    private String username;
    private String password;
    private String email;
    private LocalDate dob;
    @Nullable
    private Long card;

    public User(String username, String password, String email, LocalDate dob) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
    }
}
