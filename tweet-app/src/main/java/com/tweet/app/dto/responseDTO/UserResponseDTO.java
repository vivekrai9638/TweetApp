package com.tweet.app.dto.responseDTO;

import com.tweet.app.entity.Reply;
import com.tweet.app.entity.Tweet;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserResponseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String contact;
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDTO userResponseDTO = (UserResponseDTO) o;
        return Objects.equals(userId, userResponseDTO.userId) && Objects.equals(userName, userResponseDTO.userName) && Objects.equals(firstName, userResponseDTO.firstName) && Objects.equals(lastName, userResponseDTO.lastName) && Objects.equals(contact, userResponseDTO.contact) && Objects.equals(email, userResponseDTO.email) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, firstName, lastName, contact, email);
    }

    public boolean validate() {
        boolean validityCheck = userId!=null && firstName!=null && lastName!=null;
        validityCheck = validityCheck && email!=null && contact!=null;
        return validityCheck;
    }

}
