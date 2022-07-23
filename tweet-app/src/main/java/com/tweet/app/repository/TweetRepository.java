package com.tweet.app.repository;

import com.tweet.app.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet,Integer> {
    public List<Tweet> findByUsersUserName(String userName);
}
