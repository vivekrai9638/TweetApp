package com.tweet.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_seq")
    @SequenceGenerator(name = "tag_seq", sequenceName = "tag_seq_table")
    private Integer tagId;

    @NonNull
    private String tag;

    @ManyToOne
    @JoinColumn(name = "tweetId")
    @JsonIgnore
    private Tweet tweet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag1 = (Tag) o;
        return Objects.equals(tagId, tag1.tagId) && tag.equals(tag1.tag) && Objects.equals(tweet, tag1.tweet) && Objects.equals(reply, tag1.reply);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, tag, tweet, reply);
    }

    @ManyToOne
    @JoinColumn(name = "replyId")
    @JsonIgnore
    private Reply reply;

}
