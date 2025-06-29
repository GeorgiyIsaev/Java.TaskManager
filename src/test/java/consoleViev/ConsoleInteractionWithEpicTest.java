package consoleViev;

import com.consoleViewStrategy.Main;
import com.consoleViewStrategy.utils.Notification;
import com.dateTask.CreateID;
import com.dateTask.TaskType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleInteractionWithEpicTest {
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
        return allContent.contains(findContent);
    }
    public void startMain(){
        String[] args = {""};
        Main.main(args);
    }
    public void reset(){
        String command = "\nexit";
        setUp(command);
        startMain();
        outContent.reset();
    }

    ///  ТЕСТЫ (Эпик Задачи - EPIC)

    @Test
    void addEpicTest() {
        reset();
        String command = "addEPIC Название\nОписание\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, Notification.ADD_EPIC.toString());
        Assertions.assertTrue(isExist, consoleContent);
    }

    @Test
    void reNameTaskTest() {
        reset();
        String newName = "Новое имя";
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "addEPIC Название\nОписание\nreName "+ id +"\n" + newName +
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
        String command = "addEPIC Название\nОписание\nreDesc "+ id +"\n" + newDescription +
                "\nprintID " + id +"\nexit";
        setUp(command);
        startMain();
    }

    @Test
    void reStatusPROGTaskTest() {
        reset();
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "addEPIC Название\nОписание\nreStatus "+ id +" PROG\n" +
                "\nprintID " + id +"\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.NOT_CHANGE_STATUS.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        reset();
        String commandPrintTask ="printID "+ id + "\nexit";
        setUp(commandPrintTask);
        startMain();

        String status = "NEW";
        boolean isExistNewStatus = isExistInConsole(consoleContent, status);
        Assertions.assertTrue(isExistNewStatus, consoleContent);
    }

    @Test
    void reStatusNEWTaskTest() {
        reset();
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "addEPIC Название\nОписание\nreStatus "+ id +" NEW\n" +
             "\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.NOT_CHANGE_STATUS.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        reset();
        String commandPrintTask ="printID "+ id + "\nexit";
        setUp(commandPrintTask);
        startMain();

        String status = "NEW";
        boolean isExistNewStatus = isExistInConsole(consoleContent, status);
        Assertions.assertTrue(isExistNewStatus, consoleContent);
    }

    @Test
    void reStatusDONETaskTest() {
        reset();
        String newStatus = "DONE";
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "addEPIC Название\nОписание\nreStatus "+ id +" " + newStatus + "\n" +
               "\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.NOT_CHANGE_STATUS.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        reset();
        String commandPrintTask ="printID "+ id + "\nexit";
        setUp(commandPrintTask);
        startMain();

        String status = "NEW";
        boolean isExistNewStatus = isExistInConsole(consoleContent, status);
        Assertions.assertTrue(isExistNewStatus, consoleContent);
    }

    @Test
    void reStatusEXCEPTIONTaskTest() {
        reset();
        String newStatus = "ОШИБКА";
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "addEPIC Название\nОписание\nreStatus "+ id +" " + newStatus + "\n" + "\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.NOT_CHANGE_STATUS.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        reset();
        String commandPrintTask ="printID "+ id + "\nexit";
        setUp(commandPrintTask);
        startMain();

        String status = "NEW";
        boolean isExistNewStatus = isExistInConsole(consoleContent, status);
        Assertions.assertTrue(isExistNewStatus, consoleContent);
        boolean isExistID = isExistInConsole(consoleContent, "" + id);
        Assertions.assertTrue(isExistID, consoleContent);
    }

    @Test
    void reStatusNONTaskTest() {
        reset();
        String newStatus = "";
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "addEPIC Название\nОписание\nreStatus "+ id + newStatus + "\n" + "\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.NOT_CHANGE_STATUS.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        reset();
        String commandPrintTask ="printID "+ id + "\nexit";
        setUp(commandPrintTask);
        startMain();

        String status = "NEW";
        boolean isExistNewStatus = isExistInConsole(consoleContent, status);
        Assertions.assertTrue(isExistNewStatus, consoleContent);
        boolean isExistID = isExistInConsole(consoleContent, "" + id);
        Assertions.assertTrue(isExistID, consoleContent);
    }

    @Test
    void reSubInTaskTest() {
        reset();
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "addEPIC Название\nОписание\naddSubTaskToID "+ id +"\nНазвание SUB\nОписание SUB\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.ADD_SUBTASK.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        reset();
        int idSub = CreateID.INSTANCE.getCurrentID();
        String commandPrintTask ="printID "+ idSub + "\nexit";
        setUp(commandPrintTask);
        startMain();

        boolean isExistID = isExistInConsole(consoleContent, "" + id);
        Assertions.assertTrue(isExistID, consoleContent);

    }

    @Test
    void printIDEpicTest() {
        reset();
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "addEPIC Название\nОписание\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.ADD_EPIC.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        reset();
        String commandPrintTask ="printID "+ id + "\nexit";
        setUp(commandPrintTask);
        startMain();

        boolean isExistID = isExistInConsole(consoleContent, "" + id);
        Assertions.assertTrue(isExistID, consoleContent);
    }


    @Test
    void printOnlyEpicTest() {
        reset();

        String nameTask1 = "ЭПИК0001";
        String descTask1 = "Описание первой задачи";
        String nameTask2 = "ЭПИК0002";
        String descTask2 = "Описание второй задачи";
        String command = "addEPIC "+nameTask1+"\n"+descTask1+"\n" +
                "addEPIC "+nameTask2+"\n"+descTask2+"\n" +
                "exit";
        setUp(command);
        startMain();

        reset();
        String commandPrintTask ="printEPIC\n" + "exit";
        setUp(commandPrintTask);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.PRINT_EPIC.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        consoleContent = outContent.toString();
        boolean isExistName = isExistInConsole(consoleContent, nameTask1);
        Assertions.assertTrue(isExistName, consoleContent);

        boolean isExistDesc= isExistInConsole(consoleContent, nameTask2);
        Assertions.assertTrue(isExistDesc, consoleContent);
    }

    @Test
    void printOnlyEpicTestShouldNotTaskAnDSubPrint() {
        reset();
        int idEpic = CreateID.INSTANCE.getCurrentID();
        String command = "addEPIC Название\nОписание\n" +
                "addSubTaskToID " + idEpic + "\nСаб Название\nСаб Описание\n" +
                "addTask Название\nОписание\n" +
                "\nexit";
                ;
        setUp(command);
        startMain();

        reset();
        String commandPrintTask ="printEPIC\n" + "exit";
        setUp(commandPrintTask);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.PRINT_EPIC.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        boolean isExistName = isExistInConsole(consoleContent, TaskType.TASK.name());
        Assertions.assertFalse(isExistName, consoleContent);

        boolean isExistDesc= isExistInConsole(consoleContent, TaskType.SUBTASK.name());
        Assertions.assertFalse(isExistDesc, consoleContent);
    }






    @Test
    void deleteIDEpicTest() {
        reset();
        String nameTask = "Тестовое имя задачи";
        String descTask = "Тестовое описание задачи";
        int id = CreateID.INSTANCE.getCurrentID();
        String command = "addEPIC "+nameTask+"\n"+descTask+"\ndeleteID "+ id + "\nprintID "  + id + "\nexit";
        setUp(command);
        startMain();
        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.DELETE_ByID.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        boolean isExistNotID =  isExistInConsole(consoleContent, Notification.ID_NOT_EXIST.toString());
        Assertions.assertTrue(isExistNotID, consoleContent);
    }

    @Test
    void deleteIDEpicContainsSubTest() {
        reset();
        int idEpic = CreateID.INSTANCE.getCurrentID();
        String command = "addEPIC Название\nОписание\n" +
                "addSubTaskToID " + idEpic + "\nСаб Название\nСаб Описание\n" +
                "deleteID "+  idEpic +"\n" +
                "exit";

        setUp(command);
        startMain();
        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.DELETE_ByID.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        reset();
        int idSub = CreateID.INSTANCE.getCurrentID();
        String commandPrintSub =  "printID " + idSub +  "\nexit";
        setUp(commandPrintSub);
        startMain();
        consoleContent = outContent.toString();
        boolean isExistNotID =  isExistInConsole(consoleContent, Notification.ID_NOT_EXIST.toString());
        Assertions.assertTrue(isExistNotID, consoleContent);
    }
}
