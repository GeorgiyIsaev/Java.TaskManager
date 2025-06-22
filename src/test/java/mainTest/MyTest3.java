package mainTest;


import com.consoleView.ConsoleView;
import com.controller.Managers;
import com.controller.taskManager.TaskManager;
import com.controller.FileManager;
import com.dateTask.TaskStatus;

public class MyTest3 {
    public static void main(String[] args) {

        TaskManager managerTaskInMemoryMap = Managers.getDefault();
        FileManager.load(managerTaskInMemoryMap);
        ConsoleView consoleView = new ConsoleView(managerTaskInMemoryMap);

        createTask(managerTaskInMemoryMap);
        consoleView.printTask();

        changeTask(managerTaskInMemoryMap);
        System.out.println("            Демонстрация Изменений");
        consoleView.printTask();

        System.out.println("            Сохранение");
        FileManager.save(managerTaskInMemoryMap);


        System.out.println("            Загрузка");
        FileManager.load(managerTaskInMemoryMap);
        consoleView.printTask();

        System.out.println("            Новая задача после загрузки");
        managerTaskInMemoryMap.addTask("Задача после загрузки", "Описание простой задачи");
        consoleView.printTask();

        System.out.println("            История");
        System.out.println(managerTaskInMemoryMap.getHistory());
    }
    public static void createTask(TaskManager managerTaskInMemory) {
        managerTaskInMemory.addTask("Первая задача", "Описание простой задачи");
        managerTaskInMemory.getTask(0);
        managerTaskInMemory.addTask("Вторая задача", "Другое описание простой задачи");
        managerTaskInMemory.getTask(1);
        managerTaskInMemory.addEpic("Эпичная задача", "Очень эпичное описание");
        managerTaskInMemory.addSubTaskToEpicID(2, "Подзадача 01", "Для тестирования 01");
        managerTaskInMemory.addSubTaskToEpicID(2, "Подзадача 02", "Для тестирования 02");
        managerTaskInMemory.addSubTaskToEpicID(2, "Подзадача 03", "Для тестирования 03");

        managerTaskInMemory.getTask(2);
        managerTaskInMemory.getTask(3);
        managerTaskInMemory.getTask(4);
        managerTaskInMemory.getTask(1);
        managerTaskInMemory.getTask(1);
        managerTaskInMemory.getTask(1);

    }

    public static void changeTask(TaskManager managerTaskInMemory) {
        managerTaskInMemory.reNameToIDTask(5, "Новое крутое название");
        managerTaskInMemory.getTask(5);
        managerTaskInMemory.reDescToIDTask(3, "Новое крутое описание");
       //managerTask.deleteIDTask(4);
        managerTaskInMemory.reStatus(3, TaskStatus.IN_PROGRESS);
        managerTaskInMemory.getTask(3);
        managerTaskInMemory.getTask(3);
        managerTaskInMemory.getTask(3);
    }
}
