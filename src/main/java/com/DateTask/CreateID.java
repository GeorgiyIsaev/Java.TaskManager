package com.DateTask;

public enum CreateID {
    CreateID01;
    static int staticId =  0;
    public static Integer getNewID(){
        return staticId++;
    }
    public static void newStartID(int newID) throws Exception {
        if(staticId>newID){
            throw new Exception("ERROR: Новый стартовый ID не может быть меньше существующего!");
        }
        staticId = newID;
    }
}
