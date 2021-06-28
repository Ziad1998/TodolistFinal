package com.example.todolistfinal.data.todolist;

public class model {
    private String task;
    private String description;
    private String userid;
    private String day;
    private String year;
    private String month;

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    private String taskid;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public model(String task, String description, String id, String day, String month, String year,String taskid) {
        this.task = task;
        this.description = description;
        this.userid = id;
        this.day=day;
        this.month=month;
        this.year=year;
        this.taskid=taskid;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserid() {
        return userid;
    }

    public void setId(String userid) {
        this.userid = userid;
    }



    public model()
    {

    }
}
