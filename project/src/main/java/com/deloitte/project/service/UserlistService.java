package com.deloitte.project.service;

import com.deloitte.project.model.Todolist;
import com.deloitte.project.repository.Todolistrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * This class has the main business logic
 *
 * @author Rahul Ramesh
 *
 */


@Service
@Transactional
public class UserlistService {



//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        return new User("foo","foo",new ArrayList<>());
//    }


    @Autowired
    private Todolistrepository todolistrepository;


    /*
     * Post task into database
     */

    public Todolist saveList(List<Todolist> list) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        for(int i=0;i<=list.size();i++) {
            list.get(i).setlastUpdatedTime(String.valueOf(now));
            list.get(i).setlastUpdatedBy(String.valueOf(now));
            list.forEach(e -> todolistrepository.save(e));
            return null;
        }
        return null;

    }



    /*
     * Get Job By id from H2 DB
     */
    public List<Todolist> getJobById(long id) throws Exception {

        List<Todolist> taskNew = todolistrepository.findAllTasks(id);
        for(int i=0;i<taskNew.size();i++) {
            System.out.println(taskNew.get(0).getTaskId());
            if (taskNew.isEmpty()) {
                throw new Exception("Resource Not Found!!!!");
            } else {
                return taskNew;
            }
        }
        return taskNew;
    }

    public void deleteTask(long id) throws Exception {
        todolistrepository.deleteById(id);
    }


    public Todolist updateJob(List<Todolist> List,long taskID) throws Exception {

        LocalDateTime now = LocalDateTime.now();
            for (int i = 0 ;i <= List.size(); i++) {
                Optional<Todolist> newTask = todolistrepository.findByUserId(List.get(i).getuserId(), taskID);
                if (!newTask.isPresent()) {
                    throw new Exception("Resource Not Found!!!!");

                } else {
                      todolistrepository.updateForIschecked(List.get(i).getisChecked(),String.valueOf(now),taskID);
//                    Todolist todolist2 = newTask.get();
//                    List<Todolist> list = new ArrayList<>();
//                    list.add(new Todolist(taskID, List.get(i).getisChecked(), String.valueOf(now)));
//                    list.forEach(e -> todolistrepository.save(e));
                    return null;
                }
            }
        return null;
        }

    }


