package com.tweet.app.dto.responseDTO;

import java.util.HashSet;
import java.util.Set;

public class LoginResponseDTO extends UserResponseDTO{
    private Set<TweetResponseDTO> tweets = new HashSet<>();

    public Set<TweetResponseDTO> getTweets() {
        return tweets;
    }

    public void setTweets(Set<TweetResponseDTO> tweets) {
        this.tweets = tweets;
    }
}
