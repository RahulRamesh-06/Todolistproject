package com.deloitte.project.model;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.util.logging.Level;

@Entity
@Table(name = "todolist")
public class Todolist {


    @Id
    @GeneratedValue
    private long taskId;
    @Column(name="userid")
    private long userId;
    private String task;
    private String description;
    private boolean isChecked;
    private String lastUpdatedBy;
    private String   lastUpdatedTime;

    public long getTaskId() {
        return taskId;
    }
    public void setTaskId (long taskId) {
        this.taskId=taskId;
    }
    public Long getuserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task=task;
    }
    public String getdescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description=description;
    }

    public boolean getisChecked() {
        return isChecked;
    }
    public void setisChecked(boolean isChecked) {
        this.isChecked=isChecked;
    }

    public String getlastUpdatedBy() {
        return lastUpdatedBy;
    }
    public void setlastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy=lastUpdatedBy;
    }

    public String getlastUpdatedTime() {
        return lastUpdatedTime;
    }
    public void setlastUpdatedTime(String lastUpdatedTime) {
        this.lastUpdatedTime=lastUpdatedTime;
    }
}
