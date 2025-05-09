package com.controller.controlException;

import com.controller.IManagerTask;
import com.controller.ManagerTaskInMemory;

public class Managers {
    public static IManagerTask getDefault() {
        return new ManagerTaskInMemory();
    }
}
