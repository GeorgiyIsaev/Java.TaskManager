package consoleView;

import com.consoleView.ConsoleNotification;
import com.consoleView.ConsoleUtils;
import com.consoleView.ConsoleView;
import com.controller.IManagerTask;
import com.controller.Managers;
import com.dateTask.CreateID;
import com.dateTask.Task;
import com.dateTask.TaskStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

public class ConsoleInteractionWithEpicTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    public void setUp(String command) {
        System.setIn(new ByteArrayInputStream(command.getBytes()));
    }
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    public boolean isExistInConsole(String allContent, String findContent){
        return allContent.toUpperCase().contains(findContent.toUpperCase());
    }

    /// ТЕСТ ВЗАИМОДЕЙСТВИЯ С EPIC ЗАДАЧАМИ
    @Test
    void addEpicTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String command = "addEPIC Название\nОписание\nexit";
        String findContent = "Add EpicTask";
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size());

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent);

        final int ONE_TASK = 1;
        Assertions.assertEquals(ONE_TASK, managerTask.getTasks().size());
    }

    @Test
    void reNameTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String newName = "Новое имя";
        String command = "addEPIC Название\nОписание\nreNameID "+ CreateID.INSTANCE.getCurrentID() +"\n" + newName + "\nexit";
        String findContent = ConsoleNotification.RENAME;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех отображен в консоли

        Task lastTask = null; //она тут одна
        for(Map.Entry<Integer, Task> entry  :  managerTask.getTasks().entrySet()){
            lastTask =  entry.getValue();
        }

        Assertions.assertNotNull(lastTask);
        Assertions.assertEquals(newName, lastTask.getName());
        Assertions.assertEquals("EPIC", lastTask.getTypeTask());
    }

    @Test
    void reDescTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String newDescription = "Новое описание";
        String command = "addEPIC Название\nОписание\nreDescID "+  CreateID.INSTANCE.getCurrentID() +"\n" + newDescription + "\nexit";
        String findContent = ConsoleNotification.REDESC;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех отображен в консоли

        Task lastTask = null; //она тут одна
        for(Map.Entry<Integer, Task> entry  :  managerTask.getTasks().entrySet()){
            lastTask =  entry.getValue();
        }

        Assertions.assertNotNull(lastTask); //Проверяем что Задача существует
        Assertions.assertEquals(newDescription, lastTask.getDescription()); //Проверяем что описание действительно изменилось
        Assertions.assertEquals("EPIC", lastTask.getTypeTask());
    }

    @Test
    void reStatusPROGTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String command = "addEPIC Название\nОписание\nnewStatusId "+  CreateID.INSTANCE.getCurrentID() +" PROG\nexit";
        String findContent = ConsoleNotification.NOT_CHANGE_STATUS;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех отображен в консоли

        Task lastTask = null; //она тут одна
        for(Map.Entry<Integer, Task> entry  :  managerTask.getTasks().entrySet()){
            lastTask =  entry.getValue();
        }

        Assertions.assertNotNull(lastTask); //Проверяем что Задача существует
        Assertions.assertEquals("EPIC", lastTask.getTypeTask());
    }

    @Test
    void reStatusNEWTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String command = "addEPIC Название\nОписание\nnewStatusId "+  CreateID.INSTANCE.getCurrentID() +" NEW\nexit";
        String findContent = ConsoleNotification.NOT_CHANGE_STATUS;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех отображен в консоли

        Task lastTask = null; //она тут одна
        for(Map.Entry<Integer, Task> entry  :  managerTask.getTasks().entrySet()){
            lastTask =  entry.getValue();
        }

        Assertions.assertNotNull(lastTask); //Проверяем что Задача существует
        Assertions.assertEquals("EPIC", lastTask.getTypeTask());
    }

    @Test
    void reStatusDONETaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String command = "addEPIC Название\nОписание\nnewStatusId "+  CreateID.INSTANCE.getCurrentID() +" DONE\nexit";
        String findContent = ConsoleNotification.NOT_CHANGE_STATUS;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех отображен в консоли

        Task lastTask = null; //она тут одна
        for(Map.Entry<Integer, Task> entry  :  managerTask.getTasks().entrySet()){
            lastTask =  entry.getValue();
        }

        Assertions.assertNotNull(lastTask); //Проверяем что Задача существует
        Assertions.assertEquals("EPIC", lastTask.getTypeTask());
    }

    @Test
    void reStatusEXCEPTIONTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String command = "addEPIC Название\nОписание\nnewStatusId "+  CreateID.INSTANCE.getCurrentID() +" EXCEPTION\nexit";
        String findContent = ConsoleNotification.NOT_CHANGE_STATUS;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех  отображен в консоли
    }

    @Test
    void reStatusNONTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String command = "addEPIC Название\nОписание\nnewStatusId "+  CreateID.INSTANCE.getCurrentID() +"\nexit";
        String findContent = ConsoleNotification.NOT_CHANGE_STATUS;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех  отображен в консоли
    }

    @Test
    void reSubInTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String command = "addEPIC Название\nОписание\naddSubTaskToID "+  CreateID.INSTANCE.getCurrentID() +"\nНазвание SUB\nОписание SUB\nexit";
        String findContent = "Add SubTask";
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех  отображен в консоли
    }

    @Test
    void printIDTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String command = "addEPIC Название\nОписание\nprintID "+  CreateID.INSTANCE.getCurrentID() +"\nexit";
        String findContent = ConsoleUtils.CONSOLE_TITLE;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExistTitle = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExistTitle, consoleContent); //Успех отображен в консоли


        Task lastTask = null; //она тут одна
        for(Map.Entry<Integer, Task> entry  :  managerTask.getTasks().entrySet()){
            lastTask =  entry.getValue();
        }
        Assertions.assertNotNull(lastTask); //Проверяем что Задача существует
        boolean isExistTask = isExistInConsole(consoleContent, ConsoleUtils.getTaskString(lastTask));
        Assertions.assertTrue(isExistTask, consoleContent); //Успех отображен в консоли
    }


    @Test
    void deleteIDTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String command = "addEPIC Название\nОписание\ndeleteID "+  CreateID.INSTANCE.getCurrentID() +"\nexit";
        String findContent = ConsoleNotification.DELETE_TASK;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех  отображен в консоли
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Cписок пустой
    }

    @Test
    void deleteIDTaskContainsSubTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        int idEpic = CreateID.INSTANCE.getCurrentID();
        String notificationAddSub = "Add SubTask";
        String command = "addEPIC Название\nОписание\n" +
                "addSubTaskToID " + idEpic + "\nСаб Название\nСаб Описание\n" +
                "deleteID "+  idEpic +"\n" +
                "exit";
        String findContent = ConsoleNotification.DELETE_TASK;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExistAddSub = isExistInConsole(consoleContent, notificationAddSub);
        Assertions.assertTrue(isExistAddSub, consoleContent); //Успех  отображен в консоли
        boolean isExistDeleteTask = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExistDeleteTask, consoleContent); //Успех  отображен в консоли

        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Cписок пустой
    }
}
