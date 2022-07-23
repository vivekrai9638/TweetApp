package com.tweet.app.dto.responseDTO;

import com.tweet.app.entity.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
public class TweetResponseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer tweetId;
    private String content;
    private Integer likeCount=0;
    private Integer shareCount=0;
    private Integer replyCount=0;
    private Set<Tag> tags;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TweetResponseDTO tweetResponseDTO = (TweetResponseDTO) o;
        return Objects.equals(tweetId, tweetResponseDTO.tweetId) && Objects.equals(content, tweetResponseDTO.content) && Objects.equals(likeCount, tweetResponseDTO.likeCount) && Objects.equals(shareCount, tweetResponseDTO.shareCount) && Objects.equals(replyCount, tweetResponseDTO.replyCount) &&  Objects.equals(tags, tweetResponseDTO.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tweetId, content, likeCount, shareCount, replyCount, tags);
    }

    public boolean validate() {
        return content.length()>0 && content.length()<=800;
    }
}
