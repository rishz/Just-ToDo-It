package com.rishabhshukla.justtodoit.Models;

/**
 * Created by rishabhshukla on 10/02/17.
 */


public class ToDo {
    String taskName;
    Integer id;
    Boolean done;

    public Boolean getDone() {
        return done;
    }

    public ToDo(int id, String taskName, boolean done) {
        this.taskName = taskName;
        this.id = id;
        this.done = done;
    }

    public String getTaskName() {
        return taskName;
    }

    public Integer getId() {
        return id;
    }
}
