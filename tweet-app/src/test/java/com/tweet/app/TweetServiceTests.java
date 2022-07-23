package com.tweet.app;

import com.tweet.app.entity.Tweet;
import com.tweet.app.repository.TweetRepository;
import com.tweet.app.service.TweetService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TweetServiceTests {
    @Autowired
    private TweetRepository tweetRepo;

    @AfterEach
    void dbFlush() {
        tweetRepo.deleteAll();
    }

    @Test
    void getAllTweets() {
        Tweet tweet = new Tweet();
        tweetRepo.save(tweet);
        TweetService tweetService = new TweetService(tweetRepo);

        List<Tweet> tweetList = tweetService.getAllTweets();
        Tweet lastTweet = tweetList.get(tweetList.size() - 1);

        assertEquals(tweet.getUsers(), lastTweet.getUsers());
        assertEquals(tweet.getTweetId(), lastTweet.getTweetId());
    }

    @Test
    void saveATweet() {
        TweetService tweetService = new TweetService(tweetRepo);
        Tweet tweet = new Tweet();
        tweetRepo.save(tweet);
        assertEquals(1.0, tweetRepo.count());
    }


}
