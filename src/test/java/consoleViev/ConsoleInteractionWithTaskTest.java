package consoleViev;

import com.consoleViewStrategy.Main;
import com.consoleViewStrategy.utils.Notification;
import com.dateTask.CreateID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class ConsoleInteractionWithTaskTest {

    /// Ввод и вывод в консоль
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

    /// Вспомоготалеьные методы
    public boolean isExistInConsole(String allContent, String findContent){
        return allContent.toUpperCase().contains(findContent.toUpperCase());
    }
    public void startMain(){
        String[] args = {""};
        Main.main(args);
    }
    public void reset(){
        String command = "\nexit";
        setUp(command);
        outContent.reset();
    }

    ///  ТЕСТЫ (ПРОСТЫЕ ЗАДАЧИ - TASK)

    @Test
    void addTaskTest() {
        reset();
        String command = "addTAsk Название\nОписание\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, Notification.ADD_TASK.toString());
        Assertions.assertTrue(isExist, consoleContent);
    }

    @Test
    void reNameTaskTest() {
        reset();
        String newName = "Новое имя";
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "addTask Название\nОписание\nreName "+ id +"\n" + newName +
                "\nprintID " + id +"\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, Notification.RENAME.toString());
        Assertions.assertTrue(isExist, consoleContent);

        boolean isExistNewName = isExistInConsole(consoleContent, newName);
        Assertions.assertTrue(isExistNewName, consoleContent);
    }

    @Test
    void reDescTaskTest() {
        reset();
        String newDescription = "Новое описание";
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "addTask Название\nОписание\nreDesc "+ id +"\n" + newDescription +
                "\nprintID " + id +"\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.REDESC.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        boolean isExistNewDesc = isExistInConsole(consoleContent, newDescription);
        Assertions.assertTrue(isExistNewDesc, consoleContent);
    }

    @Test
    void reStatusPROGTaskTest() {
        reset();
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "addTASK Название\nОписание\nreStatus "+ id +" PROG\n" +
                "\nprintID " + id +"\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.RESTATUS.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        String status = "IN_PROG";
        boolean isExistNewStatus = isExistInConsole(consoleContent, status);
        Assertions.assertTrue(isExistNewStatus, consoleContent);
    }

    @Test
    void reStatusNEWTaskTest() {
        reset();
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "addTASK Название\nОписание\nreStatus "+ id +" NEW\n" +
                "\nprintID " + id +"\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.RESTATUS.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        String status = "NEW";
        boolean isExistNewStatus = isExistInConsole(consoleContent, status);
        Assertions.assertTrue(isExistNewStatus, consoleContent);
    }

    @Test
    void reStatusDONETaskTest() {
        reset();
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "addTASK Название\nОписание\nreStatus "+ id +" DONE\n" +
                "\nprintID " + id +"\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.RESTATUS.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        String status = "DONE";
        boolean isExistNewStatus = isExistInConsole(consoleContent, status);
        Assertions.assertTrue(isExistNewStatus, consoleContent);
    }

    @Test
    void reStatusEXCEPTIONTaskTest() {
        reset();
        String newStatus = "ОШИБКА";
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "addTASK Название\nОписание\nreStatus "+ id +" " + newStatus + "\n" +
                "\nprintID " + id +"\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.STATUS_INCORRECTLY.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);
    }

    @Test
    void reStatusNONTaskTest() {
        reset();
        String nonStatus = "";
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "addTASK Название\nОписание\nreStatus "+ id + nonStatus + "\n" +
                "\nprintID " + id +"\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.STATUS_INCORRECTLY.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);
    }

    @Test
    void reSubInTaskTest() {
        reset();
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "addTASK Название\nОписание\naddSubTaskToID "+ id +"\nНазвание SUB\nОписание SUB\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.NOT_EPIC.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);
    }

    @Test
    void printIDTaskTest() {
        reset();
        String nameTask = "Тестовое имя задачи";
        String descTask = "Тестовое описание задачи";
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "addTask "+nameTask+"\n"+descTask+"\n\nprintID "+ id + "\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.PRINT_ID.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        boolean isExistName = isExistInConsole(consoleContent, nameTask);
        Assertions.assertTrue(isExistName, consoleContent);

        boolean isExistDesc= isExistInConsole(consoleContent, descTask);
        Assertions.assertTrue(isExistDesc, consoleContent);
    }

    @Test
    void deleteIDTaskTest() {
        reset();
        String nameTask = "Тестовое имя задачи";
        String descTask = "Тестовое описание задачи";
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "help\naddTask "+nameTask+"\n"+descTask+"\ndeleteID "+ id + "\nprintID "  + id + "\nexit";
        setUp(command);
        startMain();
        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.DELETE_ByID.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        boolean isExistNotID =  isExistInConsole(consoleContent, Notification.ID_NOT_EXIST.toString());
        Assertions.assertTrue(isExistNotID, consoleContent);
    }


    @Test
    void printOnlyTasksTest() {
        reset();

        String nameTask1 = "ЗАДАЧА1";
        String descTask1 = "Описание первой задачи";
        String nameTask2 = "ЗАДАЧА2";
        String descTask2 = "Описание второй задачи";
        String command = "addTask "+nameTask1+"\n"+descTask1+"\n" +
                "addTask "+nameTask2+"\n"+descTask2+"\n" +
                "printTask\n" +
                "exit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.PRINT_onlyTASK.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);


        reset();
        String commandPrintTask ="printTask\n" + "exit";
        setUp(commandPrintTask);
        startMain();

        consoleContent = outContent.toString();
        boolean isExistName = isExistInConsole(consoleContent, nameTask1);
        Assertions.assertTrue(isExistName, consoleContent);

        boolean isExistDesc= isExistInConsole(consoleContent, nameTask2);
        Assertions.assertTrue(isExistDesc, consoleContent);
    }
}
