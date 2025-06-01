package consoleView;

import com.consoleView.ConsoleNotification;
import com.consoleView.ConsoleUtils;
import com.consoleView.ConsoleView;
import com.controller.IManagerTask;
import com.controller.Managers;
import com.dateTask.Task;
import com.dateTask.TaskStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.stream.Stream;

public class ConsoleInteractionWithTaskTest {
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
    public static int currentID = 0;

    //ТЕСТ ВЗАИМОДЕЙСТВИЯ С ПРОСТЫМИ ЗАДАЧАМИ

    @Test
    void addTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String command = "add Название\nОписание\nexit";
        String findContent = "Add Task";
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size());

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent);

        final int ONE_TASK = 1;
        Assertions.assertEquals(ONE_TASK, managerTask.getTasks().size());
        currentID++;
    }

    @Test
    void reNameTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String newName = "Новое имя";
        String command = "add Название\nОписание\nreNameID "+ currentID +"\n" + newName + "\nexit";
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
        currentID++;
    }

    @Test
    void reDescTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String newDescription = "Новое описание";
        String command = "add Название\nОписание\nreDescID "+ currentID +"\n" + newDescription + "\nexit";
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
        currentID++;
    }

    @Test
    void reStatusPROGTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String command = "add Название\nОписание\nnewStatusId "+ currentID +" PROG\nexit";
        String findContent = ConsoleNotification.RESTATUS;
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
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, lastTask.getStatus()); //Проверяем что описание действительно изменилось
        currentID++;
    }

    @Test
    void reStatusNEWTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String command = "add Название\nОписание\nnewStatusId "+ currentID +" NEW\nexit";
        String findContent = ConsoleNotification.RESTATUS;
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
        Assertions.assertEquals(TaskStatus.NEW, lastTask.getStatus()); //Проверяем что описание действительно изменилось
        currentID++;
    }

    @Test
    void reStatusDONETaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String command = "add Название\nОписание\nnewStatusId "+ currentID +" DONE\nexit";
        String findContent = ConsoleNotification.RESTATUS;
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
        Assertions.assertEquals(TaskStatus.DONE, lastTask.getStatus()); //Проверяем что описание действительно изменилось
        currentID++;
    }

    @Test
    void reStatusEXCEPTIONTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String command = "add Название\nОписание\nnewStatusId "+ currentID +" EXCEPTION\nexit";
        String findContent = ConsoleNotification.STATUS_INCORRECTLY;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех  отображен в консоли
        currentID++;
    }

    @Test
    void reStatusNONTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String command = "add Название\nОписание\nnewStatusId "+ currentID +"\nexit";
        String findContent = ConsoleNotification.STATUS_INCORRECTLY;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех  отображен в консоли
        currentID++;
    }

    @Test
    void reSubInTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String command = "add Название\nОписание\naddSubTaskToID "+ currentID +"\nНазвание SUB\nОписание SUB\nexit";
        String findContent = ConsoleNotification.NOT_EPIC;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех  отображен в консоли
        currentID++;
    }

    @Test
    void printIDTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String command = "add Название\nОписание\nprintID "+ currentID +"\nexit";
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
        currentID++;

    }


    @Test
    void deleteIDTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String command = "add Название\nОписание\ndeleteID "+ currentID +"\nexit";
        String findContent = ConsoleNotification.DELETE_TASK;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех  отображен в консоли
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Cписок пустой
        currentID++;
    }



}
