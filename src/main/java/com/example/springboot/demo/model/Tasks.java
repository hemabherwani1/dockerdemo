package com.example.springboot.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Tasks {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer taskId;

    private String title;

    private Date start_date;

    private Date due_date;

    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tasks tasks = (Tasks) o;
        return Objects.equals(taskId, tasks.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId);
    }

    public Integer getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getDue_date() {
        return due_date;
    }

    public String getStatus() {
        return status;
    }
}
