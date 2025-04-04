package com.DateTask;

public abstract class IdTask {
    int id;
    static int staticId =  0;
    public IdTask(){
        id = staticId;
        staticId++;
    }
    public int getID(){
        return id;
    }
}
