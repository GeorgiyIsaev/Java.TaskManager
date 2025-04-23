package com;


import com.ConsolView.ConsoleView;
import com.Controller.ManagerFile;
import com.Controller.ManagerTaskInMemory;
import com.DateTask.TaskStatus;

public class MyTest3 {
    public static void main(String[] args) {

        ManagerTaskInMemory managerTaskInMemoryMap = new ManagerTaskInMemory();
        ManagerFile.load(managerTaskInMemoryMap);
        ConsoleView consoleView = new ConsoleView(managerTaskInMemoryMap);

        createTask(managerTaskInMemoryMap);
        consoleView.printTaskMap();

        changeTask(managerTaskInMemoryMap);
        System.out.println("            Демонстрация Изменений");
        consoleView.printTaskMap();

        System.out.println("            Сохранение");
        ManagerFile.save(managerTaskInMemoryMap);


        System.out.println("            Загрузка");
        ManagerFile.load(managerTaskInMemoryMap);
        consoleView.printTaskMap();

        System.out.println("            Новая задача после заргузки");
        managerTaskInMemoryMap.addTask("Задача после загрузки", "Описание простой задачи");
        consoleView.printTaskMap();

    }
    public static void createTask(ManagerTaskInMemory managerTaskInMemory) {
        managerTaskInMemory.addTask("Первая задача", "Описание простой задачи");
        managerTaskInMemory.addTask("Вторая задача", "Другое описание простой задачи");

        managerTaskInMemory.addEpic("Эпичная задача", "Очень эпичное описание");
        managerTaskInMemory.addSubTaskToEpicID("Подзадача 01", "Для тестирования 01", 2);
        managerTaskInMemory.addSubTaskToEpicID("Подзадача 02", "Для тестирования 02", 2);
        managerTaskInMemory.addSubTaskToEpicID("Подзадача 03", "Для тестирования 03", 2);
    }

    public static void changeTask(ManagerTaskInMemory managerTaskInMemory) {
        managerTaskInMemory.reNameToIDTask(5, "Новое крутое название");
        managerTaskInMemory.reDescToIDTask(3, "Новое крутое описание");
       //managerTask.deleteIDTask(4);
        managerTaskInMemory.reStatus(3, TaskStatus.IN_PROGRESS);

    }
}
