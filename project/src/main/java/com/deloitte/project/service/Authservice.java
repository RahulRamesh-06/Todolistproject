package com.deloitte.project.service;

import com.deloitte.project.model.Authenticationrequest;
import com.deloitte.project.model.Userdetails;
import com.deloitte.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@Service

public class Authservice implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + userName);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());

    }



    public Userdetails save(Authenticationrequest user) {
        Userdetails newUser = new Userdetails();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        return userRepository.save(newUser);
    }

     public boolean isUserExists(Authenticationrequest user) {
        List<String> details=userRepository.checkForUserName(user.getUsername());

        System.out.println("checking for list");
        System.out.println(details);
        if(details.isEmpty()) {
            return false;
        }
       return true;
    }

}
