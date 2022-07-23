package com.tweet.app.dto.responseDTO;

import com.tweet.app.entity.Reply;
import com.tweet.app.entity.Tweet;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
public class TagResponseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer tagId;
    private String tag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagResponseDTO tagResponseDTO = (TagResponseDTO) o;
        return Objects.equals(tagId, tagResponseDTO.tagId) && Objects.equals(tag, tagResponseDTO.tag) && Objects.equals(tweets, tagResponseDTO.tweets) && Objects.equals(replies, tagResponseDTO.replies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, tag, tweets, replies);
    }

    private Set<Tweet> tweets;
    private Set<Reply> replies;
}
