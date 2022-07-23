package com.tweet.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.*;

enum ERole {
    ROLE_USER
}

@Entity
@Data
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq_table")
    private Integer userId;
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

    @OneToMany(mappedBy = "users",fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JsonIgnore
    private Set<Tweet> tweets = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(userId, users.userId) && userName.equals(users.userName) && firstName.equals(users.firstName) && lastName.equals(users.lastName) && contact.equals(users.contact) && email.equals(users.email) && password.equals(users.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, firstName, lastName, contact, email, password);
    }

    @OneToMany(mappedBy = "users",fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JsonIgnore
    private Set<Reply> replies = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tweet_likes",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "tweetId"))
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JsonIgnore
    private Set<Tweet> tweetsLiked = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "reply_likes",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "replyId"))
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JsonIgnore
    private Set<Reply> repliesLiked = new HashSet<>();

}
