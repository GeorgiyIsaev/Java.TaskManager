package com.DateTask;

public abstract class IdTask {
    Integer id;
    static int staticId =  0;
    public IdTask(){
        id = staticId;
        staticId++;
    }
    public Integer getID(){
        return id;
    }
}
