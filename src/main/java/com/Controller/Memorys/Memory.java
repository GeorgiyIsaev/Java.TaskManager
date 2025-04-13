package com.Controller.Memorys;

import com.Controller.ManagerTaskMap;

import java.awt.color.ICC_ColorSpace;

public abstract class Memory {
    ManagerTaskMap managerTaskMap;

    public void setManagerTaskMap(ManagerTaskMap managerTaskMap) {
        this.managerTaskMap = managerTaskMap;
    }
    public void save(){}
    public void load(){}

}

