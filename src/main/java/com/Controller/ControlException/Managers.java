package com.Controller.ControlException;

import com.Controller.IManagerTask;
import com.Controller.ManagerTaskInMemory;

public class Managers {
    public static IManagerTask getDefault() {
        return new ManagerTaskInMemory();
    }
}
