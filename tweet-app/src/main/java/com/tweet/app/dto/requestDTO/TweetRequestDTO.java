package com.tweet.app.dto.requestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class TweetRequestDTO {
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TweetRequestDTO tweetResponseDTO = (TweetRequestDTO) o;
        return Objects.equals(content, tweetResponseDTO.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

}
