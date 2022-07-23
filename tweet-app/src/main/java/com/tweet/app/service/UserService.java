package com.tweet.app.service;

import com.tweet.app.dto.requestDTO.LoginDTO;
import com.tweet.app.dto.requestDTO.PasswordDTO;
import com.tweet.app.dto.requestDTO.UserRequestDTO;
import com.tweet.app.dto.responseDTO.LoginResponseDTO;
import com.tweet.app.dto.responseDTO.TweetResponseDTO;
import com.tweet.app.dto.responseDTO.UserResponseDTO;
import com.tweet.app.entity.Tweet;
import com.tweet.app.entity.Users;
import com.tweet.app.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private TweetService tweetService;
    @Autowired
    private UserRepository repo;

    public UserService(UserRepository userRepo) {
        this.repo = userRepo;
    }

    public List<Users> getAllUsers() {
        System.out.println(repo.findAll());
        return repo.findAll();
    }
    public Users getUserObjectByUserName(String userName) {
        return repo.findByUserName(userName).get(0);
    }

    public boolean validateTweetByUserName(String userName, Integer tweetId) {
        Tweet tweet = tweetService.getTweetById(tweetId).orElseGet(null);
        Users users = this.getUserByUserName(userName);
        if(tweet==null || users ==null) return false;

        return Objects.equals(users.getUserId(), tweet.getUsers().getUserId());
    }

    public Users getUserByUserName(String userName) {
        return repo.findByUserName(userName).get(0);
    }

    public LoginResponseDTO getUserByCredentials(LoginDTO loginDTO) {
        if(!this.validateUserByUserName(loginDTO.getUserName())) return null;
        Users users = this.getUserByUserName(loginDTO.getUserName());
        LoginResponseDTO res = modelMapper.map(users,LoginResponseDTO.class);
        List<Tweet> tweets = tweetService.getTweetsByUserName(users.getUserName());
        res.setTweets(tweets
                      .stream()
                      .map(tweet->modelMapper.map(tweet,TweetResponseDTO.class))
                      .collect(Collectors.toSet()));

        if(users.getPassword().equals(loginDTO.getPassword())) return res;
        return modelMapper.map(loginDTO, LoginResponseDTO.class);
    }

    public void updateUserPassword(String userName, PasswordDTO passwordDTO) {
        Users users = this.getUserByUserName(userName);
        users.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
        repo.save(users);
    }

    public boolean validateUserByUserName(String userName) {
        return repo.findByUserName(userName).size()>0;
    }
    public UserResponseDTO addNewUser(UserRequestDTO userRequestDTO) {
        Users users = modelMapper.map(userRequestDTO, Users.class);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        if(!this.validateUserByUserName(userRequestDTO.getUserName())) {
            repo.save(users);
            return modelMapper.map(users,UserResponseDTO.class);
        }

        return new UserResponseDTO();
    }

    public List<Users> getAllUsersByMatchingUserName(String userName) {
        return repo.findByUserNamePartial(userName);
    }
}
