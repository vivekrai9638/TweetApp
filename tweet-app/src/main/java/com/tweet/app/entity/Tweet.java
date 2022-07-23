package com.tweet.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tweet_seq")
    @SequenceGenerator(name = "tweet_seq", sequenceName = "tweet_seq_table")
    private Integer tweetId;

    @NonNull
    private String content;

    @NonNull
    private Integer likeCount = 0;

    @NonNull
    private Integer shareCount = 0;

    @NonNull
    private Integer replyCount = 0;

    @NonNull
    private Date dateOfCreation = new Date(System.currentTimeMillis());

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    @ToString.Exclude
    private Users users;

    @OneToMany(mappedBy = "tweet",fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    private Set<Tag> tags = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return likeCount == tweet.likeCount && shareCount == tweet.shareCount && replyCount == tweet.replyCount && Objects.equals(tweetId, tweet.tweetId) && content.equals(tweet.content) && Objects.equals(tags, tweet.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tweetId, content, likeCount, shareCount, replyCount);
    }

    @ManyToMany(mappedBy = "tweetsLiked",fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JsonIgnore
    @ToString.Exclude
    private Set<Users> usersLiked = new HashSet<>();

    @OneToMany(mappedBy = "tweet",fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JsonIgnore
    @ToString.Exclude
    private Set<Reply> replies = new HashSet<>();

}
