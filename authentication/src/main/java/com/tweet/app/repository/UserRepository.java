package com.tweet.app.repository;

import com.tweet.app.security.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    public List<Users> findByUserName(String userName);

    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE user_name LIKE %?1%")
    public List<Users> findByUserNamePartial(String userName);
}
