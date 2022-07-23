package com.tweet.app.dto.requestDTO;

import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
public class UserRequestDTO {
    private String userName;
    private String firstName;
    private String lastName;
    private String contact;
    private String email;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequestDTO that = (UserRequestDTO) o;
        return  Objects.equals(userName, that.userName) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(contact, that.contact) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, firstName, lastName, contact, email, password);
    }
}
