package com.tweet.app.controller;

import com.tweet.app.dto.requestDTO.ReplyRequestDTO;
import com.tweet.app.dto.requestDTO.TweetRequestDTO;
import com.tweet.app.dto.responseDTO.ReplyResponseDTO;
import com.tweet.app.dto.responseDTO.TweetResponseDTO;
import com.tweet.app.entity.Tweet;
import com.tweet.app.entity.Users;
import com.tweet.app.service.TweetService;
import com.tweet.app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("tweets")
public class TweetController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private TweetService tweetService;
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    List<TweetResponseDTO> getAllTweets() {
        List<Tweet> allTweets = tweetService.getAllTweets();
        if(allTweets.size()==0) return List.of();
        return allTweets
                .stream()
                .map(tweet -> modelMapper.map(tweet,TweetResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{username}")
    List<TweetResponseDTO> getTweetsByUserName(@PathVariable("username") String userName) {
        List<Tweet> tweetsByUserName = tweetService.getTweetsByUserName(userName);
        if(tweetsByUserName.size()==0) return List.of();
        return tweetsByUserName
                .stream()
                .map(tweet -> modelMapper.map(tweet,TweetResponseDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/{username}/add")
    TweetResponseDTO addNewTweet(@RequestBody TweetRequestDTO tweetRequestDTO, @PathVariable("username") String userName) {
        if(userService.validateUserByUserName(userName)) {
            Users users = userService.getUserByUserName(userName);
            return tweetService.addNewTweet(tweetRequestDTO, users);
        }
        return modelMapper.map(tweetRequestDTO,TweetResponseDTO.class);
    }

    @PutMapping("/{username}/update/{id}")
    TweetResponseDTO updateTweet(@PathVariable Map<String,String> pathVars,
                                 @RequestBody TweetRequestDTO tweetRequestDTO) {

        String userName = pathVars.get("username");
        Integer tweetId = Integer.valueOf(pathVars.get("id"));

        if(userService.validateTweetByUserName(userName,tweetId)) {
            return tweetService.updateTweet(tweetRequestDTO, tweetId);
        }
            return new TweetResponseDTO();
    }

    @DeleteMapping("/{username}/delete/{id}")
    String deleteTweet(@PathVariable Map<String,String> pathVars) {

        String userName = pathVars.get("username");
        Integer tweetId = Integer.valueOf(pathVars.get("id"));

        if(userService.validateTweetByUserName(userName,tweetId)) {
            return tweetService.deleteTweet(tweetId);
        } else {
            return "Failed";
        }
    }

    @PutMapping("/{username}/like/{id}")
    Integer updateLikeCountsOfTweetByTweetId(@PathVariable Map<String,String> pathVars) {

        String userName = pathVars.get("username");
        Integer tweeItd = Integer.valueOf(pathVars.get("id"));

        if(userService.validateUserByUserName(userName)) {
            Users user = userService.getUserByUserName(userName);
            return tweetService.updateLikeCountOfTweetByTweetId(tweeItd,user);
        } else return -1;
    }

    @PostMapping("/{username}/reply/{id}")
    ReplyResponseDTO postReplyToTweet(@PathVariable Map<String,String> pathVars,
                                      @RequestBody ReplyRequestDTO replyRequestDTO) {

        String userNmme = pathVars.get("username");
        Integer tweetId = Integer.valueOf(pathVars.get("id"));

        if(userService.validateUserByUserName(userNmme)) {
            Users replier = userService.getUserByUserName(userNmme);
            return tweetService.postReplyToTweet(tweetId, replyRequestDTO, replier);
        }

        return modelMapper.map(replyRequestDTO,ReplyResponseDTO.class);
    }
}
