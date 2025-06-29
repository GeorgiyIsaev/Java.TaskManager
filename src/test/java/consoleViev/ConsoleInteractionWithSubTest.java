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

public class ConsoleInteractionWithSubTest {

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
        outContent.reset();
        String[] args = {""};
        Main.main(args);
    }
    public void reload(){
        String command = "\nexit";
        setUp(command);
        startMain();
        outContent.reset();
    }

    public int createSubTask(String nameSub, String descriptionSub){
        int idEpic = CreateID.INSTANCE.getCurrentID();
        String commandCreateSub = "addEPIC Название\nОписание\n" +
                "addSubTaskToID " + idEpic + "\n" + nameSub+"\n" + descriptionSub +"\n" + "\nexit";
        setUp(commandCreateSub);
        startMain();

        int idSub = CreateID.INSTANCE.getCurrentID() -1;
        return idSub;
    }


    ///  ТЕСТЫ (Подзадачи - SUBTASK)
    @Test
    void addSubTest() {
        reload();
        createSubTask("SubTask","Desc");

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, Notification.ADD_SUBTASK.toString());
        Assertions.assertTrue(isExist, consoleContent);
    }

    @Test
    void reNameTaskTest() {
        reload();
        int idSub = createSubTask("SubTask","Desc");
        String newName = "Новое название";
        String commandReName =   "reName "+ idSub +"\n" + newName + "\nexit";
        setUp(commandReName);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, Notification.RENAME.toString());
        Assertions.assertTrue(isExist, consoleContent);

        String commandPrintID = "PrintID "+ idSub +"\nexit";
        setUp(commandPrintID);
        startMain();
        consoleContent = outContent.toString();

        boolean isExistNewName = isExistInConsole(consoleContent, newName);
        Assertions.assertTrue(isExistNewName, consoleContent);
    }

    @Test
    void reDescTaskTest() {
        reload();
        int idSub = createSubTask("SubTask","Desc");
        String newDesc = "Супер Новое описание";
        String commandReName =   "reDesc "+ idSub +"\n" + newDesc + "\nexit";
        setUp(commandReName);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, Notification.REDESC.toString());
        Assertions.assertTrue(isExist, consoleContent);

        String commandPrintID = "PrintID "+ idSub +"\nexit";
        setUp(commandPrintID);
        startMain();
        consoleContent = outContent.toString();

        boolean isExistNewName = isExistInConsole(consoleContent, newDesc);
        Assertions.assertTrue(isExistNewName, consoleContent);
    }

    @Test
    void reStatusPROGTaskTest() {
        reload();
        int idSub = createSubTask("SubTask","Desc");
        String statusProg = "PROG";
        String commandReDesc =    "reStatus "+  idSub +" " + statusProg +"\n" +
            "print ID "+ idSub + "\nexit";
        setUp(commandReDesc);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.RESTATUS.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        boolean isExistNewDesc = isExistInConsole(consoleContent, statusProg);
        Assertions.assertTrue(isExistNewDesc, consoleContent);
    }

    @Test
    void reStatusNEWTaskTest() {
        reload();
        int idSub = createSubTask("SubTask","Desc");
        String statusPROG = "PROG";
        String statusNEW = "NEW";
        String commandReStatus =    "reStatus "+  idSub + " " +statusPROG +
                "\nreStatus "+  idSub + " " + statusNEW + "\nexit";
        setUp(commandReStatus);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.RESTATUS.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        reload();
        String commandPrintID =    "printID "+  idSub + "\nexit";
        setUp(commandPrintID);
        startMain();

        boolean isExistNewStatus = isExistInConsole(consoleContent, statusNEW);
        Assertions.assertTrue(isExistNewStatus, consoleContent);
    }

    @Test
    void reStatusDONETaskTest() {
        reload();
        int idSub = createSubTask("SubTask","Desc");
        String statusDONE = "DONE";
        String commandReStatus = "reStatus "+  idSub + " " +statusDONE + "\nexit";
        setUp(commandReStatus);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.RESTATUS.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        reload();
        String commandPrintID =    "printID "+  idSub + "\nexit";
        setUp(commandPrintID);
        startMain();

        boolean isExistNewStatus = isExistInConsole(consoleContent, statusDONE);
        Assertions.assertTrue(isExistNewStatus, consoleContent);
    }

    @Test
    void reStatusEXCEPTIONTaskTest() {
        reload();
        int idSub = createSubTask("SubTask","Desc");
        String statusException = " Белеберда";
        String commandReStatus = "reStatus "+  idSub + statusException + "\nexit";
        setUp(commandReStatus);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.STATUS_INCORRECTLY.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        reload();
        String commandPrintID =    "printID "+  idSub + "\nexit";
        setUp(commandPrintID);
        startMain();

        String statusNEW = "NEW";
        boolean isExistNewStatus = isExistInConsole(consoleContent, statusNEW);
        Assertions.assertTrue(isExistNewStatus, consoleContent);
    }

    @Test
    void reStatusNONTaskTest() {
        reload();
        int idSub = createSubTask("SubTask","Desc");

        String statusException = "";
        String commandReStatus = "reStatus "+  idSub + statusException + "\nexit";
        setUp(commandReStatus);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.STATUS_INCORRECTLY.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        reload();
        String commandPrintID =    "printID "+  idSub + "\nexit";
        setUp(commandPrintID);
        startMain();

        String statusNEW = "NEW";
        boolean isExistNewStatus = isExistInConsole(consoleContent, statusNEW);
        Assertions.assertTrue(isExistNewStatus, consoleContent);
    }

    @Test
    void addSubInSubTest() {
        reload();
        createSubTask("SubTask","Desc");

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.ADD_SUBTASK.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);
    }

    @Test
    void printIDSubTest() {
        reload();
        int idSub = createSubTask("SubTask","Desc");
        String commandPrintID = idSub +  "\nprintAll\nprintID "+  idSub +"\nexit";
        setUp(commandPrintID);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.PRINT_ID.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        boolean isExistName = isExistInConsole(consoleContent, "" + idSub);
        Assertions.assertTrue(isExistName, consoleContent);
    }


    @Test
    void deleteIDSubTest() {
        reload();
        int idSub = createSubTask("SubTask","Desc");

        String command = "deleteID "+ idSub + "\nprintID "  + idSub + "\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.DELETE_ByID.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        boolean isExistNotID =  isExistInConsole(consoleContent, Notification.ID_NOT_EXIST.toString());
        Assertions.assertTrue(isExistNotID, consoleContent);
    }

    @Test
    void deleteEpicByIDContainsSubTest() {
        reload();
        int idEpic = CreateID.INSTANCE.getCurrentID();
        int idSub = createSubTask("SubTask","Desc");

        String command = "deleteID "+ idEpic + "\nprintID "  + idSub + "\nexit";
        setUp(command);
        startMain();
        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.DELETE_ByID.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        boolean isExistNotID =  isExistInConsole(consoleContent, Notification.ID_NOT_EXIST.toString());
        Assertions.assertTrue(isExistNotID, consoleContent);
    }


    @Test
    void printOnlySubTest() {
        reload();
        createSubTask("SubTask","Desc");

        String commandPrintTask ="printSUB\n" + "exit";
        setUp(commandPrintTask);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExistNotification = isExistInConsole(consoleContent, Notification.PRINT_SUB.toString());
        Assertions.assertTrue(isExistNotification, consoleContent);

        consoleContent = outContent.toString();
        boolean isExistName = isExistInConsole(consoleContent, TaskType.EPIC.name());
        Assertions.assertFalse(isExistName, consoleContent);
    }
}
