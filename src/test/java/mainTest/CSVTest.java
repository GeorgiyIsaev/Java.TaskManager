package mainTest;

import com.consoleView.ConsoleView;
import com.controller.FileBackedCSV;
import com.controller.FileManager;
import com.controller.Managers;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;
import com.dateTask.TaskStatus;

public class CSVTest {

    public static void main(String[] args) {

        TaskManager managerTaskInMemoryMap = Managers.getFileBacked();
        ConsoleView consoleView = new ConsoleView(managerTaskInMemoryMap);

        createTask(managerTaskInMemoryMap);
        System.out.println(" Вывод Задач на консоль");
        consoleView.printTask();

        System.out.println("Сохранение");
        FileBackedCSV fileBackedCSV = new FileBackedCSV("MyCSV.csv");
        fileBackedCSV.save(managerTaskInMemoryMap);
        System.out.println("Сохранение завершено");

        System.out.println("Очистка");
        managerTaskInMemoryMap.deleteALL();
        consoleView.printTask();

        System.out.println("Загрузка");
        fileBackedCSV.load(managerTaskInMemoryMap);
        consoleView.printTask();
    }
    public static void createTask(TaskManager managerTaskInMemory) {
        managerTaskInMemory.addTask("Первая задача", "Описание простой задачи");
        managerTaskInMemory.addTask("Вторая, задача", "Другое описание, простой задачи");
        Task epic = managerTaskInMemory.addEpic("Эпичная задача", "Очень //\\эпичное описание");
        managerTaskInMemory.addSubTaskToEpicID(epic.getID(), "Подзадача 01", "Для тестирования 01");
        managerTaskInMemory.addSubTaskToEpicID(epic.getID(),"Подзадача 02", "Для тестирования 02");
        managerTaskInMemory.addSubTaskToEpicID(epic.getID(),"Подзадача 03", "Для тестирования 03");
    }




}
