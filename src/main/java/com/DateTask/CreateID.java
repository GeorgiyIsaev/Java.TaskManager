package com.DateTask;

public enum CreateID {
    CreateID01;
    static int staticId =  0;
    public static Integer getNewID(){
        return staticId++;
    }
    public static void newStartID(int newID){
        if(staticId<newID) {
            staticId = newID;
        }
    }
}
