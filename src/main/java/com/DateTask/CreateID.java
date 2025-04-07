package com.DateTask;

public enum CreateID {
    CreateID01;
    static int staticId =  0;
    public static Integer getNewID(){
        return staticId++;
    }
}
