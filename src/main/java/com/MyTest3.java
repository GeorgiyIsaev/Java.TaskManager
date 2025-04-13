package com;

import com.ConsolView.ConsoleView;
import com.Controller.ManagerTaskMap;
import com.Controller.MemoryTaskMapToTXT;
import com.Controller.Memorys.MemorySerializable;
import com.Controller.Memorys.MemoryTXT;

public class MyTest3 {
    public static void main(String[] args) {

        try {
            ManagerTaskMap managerTaskMap = new ManagerTaskMap(new MemoryTXT());
            ConsoleView consoleView = new ConsoleView(managerTaskMap);

            managerTaskMap.addTask("Первая задач", "Описание простой задачи");
            managerTaskMap.addTask("Вторая задач", "Другое описание простой задачи");

            managerTaskMap.addEpic("Эпичная задача", "Очень эпичное описание");
            managerTaskMap.addSubTaskToEpicID("Подзадача 01", "Для тестирования 01", (Integer) 2);
            managerTaskMap.addSubTaskToEpicID("Подзадача 02", "Для тестирования 02", 2);
            managerTaskMap.addSubTaskToEpicID("Подзадача 03", "Для тестирования 03", 2);
            System.out.println("Демонстрация 1");
            consoleView.printTaskMap();


            managerTaskMap.reNameToIDTask(5, "Крутое название");
            managerTaskMap.reDescToIDTask(3, "Крутое описание");
            System.out.println("Демонстрация 2 - Изменения");
            consoleView.printTaskMap();


            System.out.println("ID 2");
            consoleView.printID("2");
            consoleView.printDebug();
            consoleView.printTaskMap();
            consoleView.printTaskMap("EPIC");
            System.out.println("Сохранение");
            managerTaskMap.save();
            System.out.println("Конец работы");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
