package com.hellstellar.retailGauthorizationserver.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hellstellar.retailGauthorizationserver.models.User;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<User,Integer> {


    Optional<User> findByUsername(String name);

}
