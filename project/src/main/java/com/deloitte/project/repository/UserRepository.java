package com.deloitte.project.repository;

import com.deloitte.project.model.Todolist;
import com.deloitte.project.model.Userdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

//@Repository
public interface UserRepository extends JpaRepository<Userdetails, Long> {


    UserDetails findByUsername(String username);


    @Query(value = "SELECT id FROM Userdetails WHERE username=?1 and password=?2", nativeQuery = true)
    long findIdbyUserName(String userName,String password);

    @Query(value = "SELECT * FROM Userdetails WHERE username=?1 ", nativeQuery = true)
    List<String> checkForUserName(String userName);
}