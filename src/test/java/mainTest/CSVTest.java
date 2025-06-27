package mainTest;

import com.consoleView.ConsoleView;
import com.controller.Managers;
import com.controller.files.CreatePath;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;

import java.nio.file.Path;

public class CSVTest {

    public static void main(String[] args) {
       // Path filePath= CreatePath.of().generatePath("csv.csv");

        Path filePath = CreatePath.of().generatePathToPakDateAndSave("csv.csv");
        TaskManager managerTaskInMemoryMap = Managers.getFileBacked(filePath);
        ConsoleView consoleView = new ConsoleView(managerTaskInMemoryMap);

        createTask(managerTaskInMemoryMap);

        System.out.println(" Вывод Задач на консоль");
        consoleView.printTask();

        System.out.println("Очистка");
        //managerTaskInMemoryMap.deleteALL();
        consoleView.printTask();
    }
    public static void createTask(TaskManager managerTaskInMemory) {
        managerTaskInMemory.addTask("Первая задача", "Описание простой задачи");
        Task task =  managerTaskInMemory.addTask("Вторая, задача", "Другое описание, простой задачи");
        Task epic = managerTaskInMemory.addEpic("Эпичная задача", "Очень //\\эпичное описание");
        managerTaskInMemory.addSubTaskToEpicID(epic.getID(), "Подзадача 01", "Для тестирования 01");
        managerTaskInMemory.addSubTaskToEpicID(epic.getID(),"Подзадача 02", "Для тестирования 02");
        managerTaskInMemory.addSubTaskToEpicID(epic.getID(),"Подзадача 03", "Для тестирования 03");
        managerTaskInMemory.getTask(epic.getID());
        managerTaskInMemory.getTask(task.getID());
    };




}
