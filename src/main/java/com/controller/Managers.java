package com.controller;

public class Managers {
    public static IManagerTask getDefault() {
        return new ManagerTaskInMemory();
    }
}
