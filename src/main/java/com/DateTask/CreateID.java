package com.DateTask;

public enum CreateID {
    INSTANCE;
    private int id = 0;

    public Integer createID(){
        return id++;
    }

    public void setId(int newID){
        if(id <=newID) {
            id = newID;
            id++;
        }
    }
}
