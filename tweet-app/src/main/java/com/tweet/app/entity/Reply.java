package com.tweet.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_seq")
    @SequenceGenerator(name = "reply_seq", sequenceName = "reply_seq_table")
    private Integer replyId;

    @NonNull
    private String replyContent;

    @NonNull
    private Integer likeCount=0;

    @NonNull
    private Date dateOfCreation = new Date(System.currentTimeMillis());

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reply reply = (Reply) o;
        return Objects.equals(replyId, reply.replyId) && replyContent.equals(reply.replyContent) && likeCount.equals(reply.likeCount) && dateOfCreation.equals(reply.dateOfCreation) && Objects.equals(tags, reply.tags) && Objects.equals(tweet, reply.tweet) && Objects.equals(users, reply.users) && Objects.equals(usersReplied, reply.usersReplied);
    }

    @Override
    public int hashCode() {
        return Objects.hash(replyId, replyContent, likeCount, dateOfCreation, tags, tweet, users, usersReplied);
    }

    @OneToMany(mappedBy = "reply",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "tweetId")
    @JsonIgnore
    private Tweet tweet;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private Users users;

    @ManyToMany(mappedBy = "repliesLiked",fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Users> usersReplied = new HashSet<>();

}
