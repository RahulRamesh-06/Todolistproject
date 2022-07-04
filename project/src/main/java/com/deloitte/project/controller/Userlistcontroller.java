package com.deloitte.project.controller;

import com.deloitte.project.model.*;
import com.deloitte.project.repository.Todolistrepository;
import com.deloitte.project.repository.UserRepository;
import com.deloitte.project.service.Authservice;
import com.deloitte.project.service.UserlistService;
import com.deloitte.project.util.Jwtutil;
import jdk.nashorn.api.scripting.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class Userlistcontroller {

    @Autowired
    private UserlistService userlistService;




    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Authservice authservice;

    @Autowired
    private Jwtutil jwtutil;


    private Logger logger = Logger.getLogger(" Userlistcontroller.class");

    /*
    Posting the task
    */

    @PostMapping("/saveTodoList")
    public  ResponseEntity<Todolist> saveList(@RequestBody List<Todolist> list){
        return new ResponseEntity<Todolist>(userlistService.saveList(list),HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public  ResponseEntity<Responseuserid> createAuthenticationToken(@RequestBody Authenticationrequest authenticationrequest) throws Exception {
    try {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationrequest.getUsername(), authenticationrequest.getPassword()));
    }
    catch (BadCredentialsException credentialsException) {
         throw new Exception("Incorrect Credentials used",credentialsException);
    }
    final UserDetails userDetails=authservice.loadUserByUsername(authenticationrequest.getUsername());
    final String jwt= jwtutil.generateToken(userDetails);
    long list=userRepository.findIdbyUserName(authenticationrequest.getUsername(),authenticationrequest.getPassword());
        Responseuserid responseuserid=new Responseuserid();
        responseuserid.setJwt(jwt);
        responseuserid.setUserID(list);
    return new ResponseEntity<Responseuserid>(responseuserid, HttpStatus.OK);
    }


    @GetMapping("/getTodoList/{id}")
    public ResponseEntity<List> getJobsById(@PathVariable("id") long id) throws Exception{
        return new ResponseEntity<List>(userlistService.getJobById(id),HttpStatus.ACCEPTED);
    }

    @PostMapping("/updateTodoList/{id}")
    public ResponseEntity<Todolist> updateJobs(@RequestBody Todolist list, @PathVariable("id") long id) throws Exception{
//        list.setUserId(id);
        return new ResponseEntity<Todolist>(userlistService.updateJob(list,id),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteTodoListT/{id}")
    public ResponseEntity<?> deleteTodo( @PathVariable("id") long id) throws Exception{
        return  ResponseEntity.ok(HttpStatus.OK);
    }



    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody Authenticationrequest user) throws Exception {
        return ResponseEntity.ok(authservice.save(user));
    }

//    private void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }


}
