package com.Controller;

import com.DateTask.Task;

import java.util.HashMap;

public class ManagerTaskMap {
    HashMap<Integer, Task> listTask;
    public HashMap<Integer, Task> getListTask() {
        return listTask;
    }

    public ManagerTaskMap (){
        listTask = MemoryTask.ReadTaskList();
    }




}
