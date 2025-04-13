package com.Controller.Memorys;

import com.Controller.ManagerTaskMap;

import java.awt.color.ICC_ColorSpace;
import java.io.IOException;

public abstract class Memory {
    ManagerTaskMap managerTaskMap;

    public void setManagerTaskMap(ManagerTaskMap managerTaskMap) {
        this.managerTaskMap = managerTaskMap;
    }
    public void save() throws IOException {}
    public void load() throws IOException {}

}

