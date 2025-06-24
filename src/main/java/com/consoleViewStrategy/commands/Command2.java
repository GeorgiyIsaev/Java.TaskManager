package com.consoleViewStrategy.commands;

public class Command2 implements ICommand{
    @Override
    public void start(Commands commands) {
        System.out.println("Команда 2");
    }
}