package com.tweet.app.service;

import com.tweet.app.dto.requestDTO.ReplyRequestDTO;
import com.tweet.app.dto.requestDTO.TweetRequestDTO;
import com.tweet.app.dto.responseDTO.ReplyResponseDTO;
import com.tweet.app.dto.responseDTO.TweetResponseDTO;
import com.tweet.app.dto.responseDTO.UserResponseDTO;
import com.tweet.app.entity.Reply;
import com.tweet.app.entity.Tweet;
import com.tweet.app.entity.Users;
import com.tweet.app.repository.ReplyRepository;
import com.tweet.app.repository.TweetRepository;
import com.tweet.app.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TweetService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private TweetRepository repo;

    @Autowired
    private ReplyRepository replyRepo;

    @Autowired
    private UserRepository userRepo;

    public TweetService(TweetRepository tweetRepo) {
        this.repo=tweetRepo;
    }

    public List<Tweet> getAllTweets() {
        return repo.findAll();
    }

    public List<Tweet> getTweetsByUserName(String userName) {
        return repo.findByUsersUserName(userName);
    }

    public TweetResponseDTO addNewTweet(TweetRequestDTO tweetRequestDTO, Users users) {
        Tweet tweet = modelMapper.map(tweetRequestDTO,Tweet.class);
        tweet.setUsers(users);
        repo.save(tweet);
        return modelMapper.map(tweet,TweetResponseDTO.class);
    }

    public TweetResponseDTO updateTweet(TweetRequestDTO tweetRequestDTO, Integer tweetId) {
        Tweet tweet = repo.findById(tweetId).orElseGet(null);
        if(tweet!=null) {
            tweet.setContent(tweetRequestDTO.getContent());
            repo.save(tweet);
        }
        return modelMapper.map(tweet,TweetResponseDTO.class);
    }

    public String deleteTweet(Integer tweetId) {
        repo.deleteById(tweetId);
        return "Deleted";
    }

    public Optional<Tweet> getTweetById(Integer tweetId) {
        return repo.findById(tweetId);
    }

    public Integer updateLikeCountOfTweetByTweetId(Integer tweetId, Users user) {
        Tweet tweet = repo.findById(tweetId).orElseGet(null);
        if(tweet==null || tweet.getUsersLiked().contains(user)) return -1;
        tweet.setLikeCount(tweet.getLikeCount()+1);
        user.getTweetsLiked().add(tweet);
        userRepo.save(user);
        repo.save(tweet);
        return tweet.getLikeCount();
    }

    public ReplyResponseDTO postReplyToTweet(Integer tweetId, ReplyRequestDTO replyRequestDTO, Users replier) {
        Tweet tweet = repo.findById(tweetId).orElseGet(null);
        Reply reply = modelMapper.map(replyRequestDTO,Reply.class);
        reply.setUsers(replier);
        reply.setTweet(tweet);

        if(tweet!=null) {
            tweet.getReplies().add(reply);
            repo.save(tweet);
            replyRepo.save(reply);
        }

        ReplyResponseDTO response = modelMapper.map(reply,ReplyResponseDTO.class);

        response.setUsersReplied(reply
                .getUsersReplied()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toSet()));

        return response;
    }
}
