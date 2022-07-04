package com.deloitte.project.service;

import com.deloitte.project.model.Todolist;
import com.deloitte.project.repository.Todolistrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
         list.forEach(e-> todolistrepository.save(e));
         return null;

    }



    /*
     * Get Job By id from H2 DB
     */
    public List<Todolist> getJobById(long id) throws Exception {
        List<Todolist> taskNew = todolistrepository.findAllTasks(id);
        if(taskNew.isEmpty()) {
            throw new Exception("Resource Not Found!!!!");
        }else {
            return taskNew;
        }

    }

    public void deleteTask(long id) throws Exception {
        todolistrepository.deleteById(id);
    }


    public Todolist updateJob(Todolist List,long taskID) throws Exception {
        Optional<Todolist> newTask= todolistrepository.findByUserId(List.getuserId(),taskID);
        if(!newTask.isPresent()) {
            throw new Exception("Resource Not Found!!!!");

        }else {
            Todolist todolist2 = newTask.get();
            todolist2.setUserId(List.getuserId());
            todolist2.setDescription(List.getdescription());
            todolist2.setTask(List.getTask());
            todolist2.setisChecked(List.getisChecked());
            todolist2.setlastUpdatedBy(List.getlastUpdatedBy());
            todolist2.setlastUpdatedTime(List.getlastUpdatedTime());
            todolistrepository.save(todolist2);
            return todolist2;
        }

    }

}
