package com.tweet.app.dto.responseDTO;

import com.tweet.app.entity.Tag;
import com.tweet.app.entity.Tweet;
import com.tweet.app.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
public class ReplyResponseDTO {

    private Integer replyId;
    private String replyContent;
    private Integer likeCount;
    private Date timeOfCreation;

    private Set<Tag> tags = new HashSet<>();
    private Tweet tweet;
    private Users users;
    private Set<UserResponseDTO> usersReplied = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReplyResponseDTO replyResponseDTO = (ReplyResponseDTO) o;
        return Objects.equals(replyId, replyResponseDTO.replyId) && Objects.equals(replyContent, replyResponseDTO.replyContent) && Objects.equals(likeCount, replyResponseDTO.likeCount) && Objects.equals(timeOfCreation, replyResponseDTO.timeOfCreation) && Objects.equals(tags, replyResponseDTO.tags) && Objects.equals(tweet, replyResponseDTO.tweet) && Objects.equals(users, replyResponseDTO.users) && Objects.equals(usersReplied, replyResponseDTO.usersReplied);
    }

    @Override
    public int hashCode() {
        return Objects.hash(replyId, replyContent, likeCount, timeOfCreation, tags, tweet, users, usersReplied);
    }


    public boolean validate() {
        boolean validityCheck = replyContent.length()>0 && replyContent.length()<=200 ;
        validityCheck = validityCheck && likeCount!=null;
        validityCheck = validityCheck && timeOfCreation!=null;
        validityCheck = validityCheck && tweet!=null;
        validityCheck = validityCheck && users !=null;
        if(validityCheck) return true;
        else return false;
    }

}
