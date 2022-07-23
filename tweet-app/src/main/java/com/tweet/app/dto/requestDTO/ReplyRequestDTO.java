package com.tweet.app.dto.requestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class ReplyRequestDTO {

    private String replyContent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReplyRequestDTO replyResponseDTO = (ReplyRequestDTO) o;
        return Objects.equals(replyContent,
                replyResponseDTO.replyContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(replyContent);
    }

}
