package com.deloitte.project.controller;

import com.deloitte.project.model.Todolist;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

public class Testcontroller {



    @RestController
    public class TestController {

        @RequestMapping(value = "/saveTodoList", method = RequestMethod.POST)
        public Todolist firstPage() {
            Todolist emp = new Todolist();
            emp.setUserId(Long.valueOf(1));
            emp.setTask("New task creation");
            emp.setDescription("Nre task");
            emp.setisChecked(false);
            emp.setlastUpdatedBy("rahul");
            emp.setlastUpdatedTime("3");
            return emp;
        }

    }
}
