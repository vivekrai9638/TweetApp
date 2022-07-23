package com.tweet.app.security.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
public class Users {
    @Id
    Integer userId;
    @NonNull
    private String userName;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String contact;

    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private String role = ERole.ROLE_USER.toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return  userName.equals(users.userName) && firstName.equals(users.firstName) && lastName.equals(users.lastName) && contact.equals(users.contact) && email.equals(users.email) && password.equals(users.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, firstName, lastName, contact, email, password);
    }

}
