package com.tweet.app.dto.requestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class TagRequestDTO {
    private String tag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagRequestDTO tagResponseDTO = (TagRequestDTO) o;
        return Objects.equals(tag, tagResponseDTO.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }


}
