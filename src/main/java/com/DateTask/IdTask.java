package com.DateTask;

public abstract class IdTask {
    static int id =  0;
    public IdTask(){
        id++;
    }
    public int getID(){
        return id;
    }
}
