package com.consoleViewStrategy.commands;

public class Command1 implements ICommand{
    @Override
    public void start(Commands commands) {
        System.out.println("Команда 1");
    }
}