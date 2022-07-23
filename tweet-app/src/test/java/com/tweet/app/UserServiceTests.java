package com.tweet.app;

import com.tweet.app.entity.Tweet;
import com.tweet.app.entity.Users;
import com.tweet.app.repository.TweetRepository;
import com.tweet.app.repository.UserRepository;
import com.tweet.app.service.TweetService;
import com.tweet.app.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepository userRepo;

    @AfterEach
    void dbFlush() {
        userRepo.deleteAll();
    }

        @Test
        void getAllUsers() {
            Users user = new Users();
            userRepo.save(user);
            UserService userService = new UserService(userRepo);

            List<Users> userList = userService.getAllUsers();
            Users lastUser = userList.get(userList.size() - 1);

            assertEquals(user.getUserName(), lastUser.getUserName());
            assertEquals(user.getUserId(), lastUser.getUserId());
        }

        @Test
        void saveAUser() {
            UserService userService = new UserService(userRepo);
            Users user = new Users();
            userRepo.save(user);
            assertEquals(1.0, userRepo.count());
        }


    }


