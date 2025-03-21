package com.smart.smartcontactmanager.dao;

import com.smart.smartcontactmanager.Entities.USER;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<USER,Integer> {
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    @Query("select u from USER u where u.userName=:userName")
    USER findUSERByUserName(@Param("userName") String userName);

}


