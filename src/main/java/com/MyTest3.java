package com;


import com.ConsolView.ConsoleView;
import com.Controller.ManagerFile;
import com.Controller.ManagerTask;
import com.DateTask.TaskStatus;

public class MyTest3 {
    public static void main(String[] args) {

        ManagerTask managerTaskMap = new ManagerTask();
        ManagerFile.load(managerTaskMap);
        ConsoleView consoleView = new ConsoleView(managerTaskMap);

        createTask(managerTaskMap);
        consoleView.printTaskMap();

        changeTask(managerTaskMap);
        System.out.println("            Демонстрация Изменений");
        consoleView.printTaskMap();

        System.out.println("            Сохранение");
        ManagerFile.save(managerTaskMap);


        System.out.println("            Загрузка");
        ManagerFile.load(managerTaskMap);
        consoleView.printTaskMap();

        System.out.println("            Новая задача после заргузки");
        managerTaskMap.addTask("Задача после загрузки", "Описание простой задачи");
        consoleView.printTaskMap();

    }
    public static void createTask(ManagerTask managerTask) {
        managerTask.addTask("Первая задача", "Описание простой задачи");
        managerTask.addTask("Вторая задача", "Другое описание простой задачи");

        managerTask.addEpic("Эпичная задача", "Очень эпичное описание");
        managerTask.addSubTaskToEpicID("Подзадача 01", "Для тестирования 01", 2);
        managerTask.addSubTaskToEpicID("Подзадача 02", "Для тестирования 02", 2);
        managerTask.addSubTaskToEpicID("Подзадача 03", "Для тестирования 03", 2);
    }

    public static void changeTask(ManagerTask managerTask) {
        managerTask.reNameToIDTask(5, "Новое крутое название");
        managerTask.reDescToIDTask(3, "Новое крутое описание");
       //managerTask.deleteIDTask(4);
        managerTask.reStatus(3, TaskStatus.IN_PROGRESS);

    }
}
