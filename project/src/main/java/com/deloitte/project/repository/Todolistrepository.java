package com.deloitte.project.repository;

import com.deloitte.project.model.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface Todolistrepository extends JpaRepository<Todolist, Long>{


    @Query(value = "SELECT userid,task_Id,task,description,is_Checked,last_Updated_By,last_Updated_Time  FROM Todolist WHERE userid=?1", nativeQuery = true)
    List<Todolist> findAllTasks(long userId);

    @Query(value = "SELECT userid,task_Id,task,description,is_Checked,last_Updated_By,last_Updated_Time  FROM Todolist WHERE userid=?1 and task_Id=?2 ", nativeQuery = true)
    Optional<Todolist> findByUserId(long userId,long TaskId);

    @Modifying(clearAutomatically = true)
    @Query(value = "update Todolist set is_Checked=?1,last_Updated_Time=?2 where task_Id=?3 ", nativeQuery = true)
     void updateForIschecked(boolean isChecked,String lastUpdate,long TaskId);
}
