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

public class ConsoleInteractionHistoryTest {
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
    public String runCommand(String command){
        setUp(command + "\nexit");
        startMain();
        return outContent.toString();
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

    public int createTask(String name, String description){
        int idTask= CreateID.INSTANCE.getCurrentID();
        runCommand("addTask "+ name + "\n" + description+"\n");
        return idTask;
    }

    public int createEpic(String name, String description){
        int idSub = CreateID.INSTANCE.getCurrentID();
        runCommand("addEpic "+ name + "\n" + description+"\n");
        return idSub;
    }



    /// ТЕСТЫ Взаимодействие с Историей просмотров
    @Test
    void printHistoryTest() {
        reload();
        String consoleContent = runCommand("printHistory");

        boolean isExistNewName = isExistInConsole(consoleContent, Notification.PRINT_HISTORY.toString());
        Assertions.assertTrue(isExistNewName, consoleContent);
    }

    @Test
    void shouldByIDTask() {
        reload();
        int id = createTask("TaskTest", "Desc");

        String consoleContentPrint = runCommand("printID " + id);
        boolean isExistNotificationID = isExistInConsole(consoleContentPrint, Notification.PRINT_ID.toString());
        Assertions.assertTrue(isExistNotificationID, consoleContentPrint);

        String consoleContent = runCommand("printHistory");
        boolean isExistNotificationIDHist = isExistInConsole(consoleContent, Notification.PRINT_HISTORY.toString());
        Assertions.assertTrue(isExistNotificationIDHist, consoleContent);

        boolean isExistIDHist = isExistInConsole(consoleContent, "" + id);
        Assertions.assertTrue(isExistIDHist, consoleContent);
    }

    @Test
    void shouldByIDEpic() {
        reload();
        int id = createEpic("Name", "Desc");

        String consoleContentPrint = runCommand("printID " + id);
        boolean isExistNotificationID = isExistInConsole(consoleContentPrint, Notification.PRINT_ID.toString());
        Assertions.assertTrue(isExistNotificationID, consoleContentPrint);

        String consoleContent = runCommand("printHistory");
        boolean isExistNewName = isExistInConsole(consoleContent, Notification.PRINT_HISTORY.toString());
        Assertions.assertTrue(isExistNewName, consoleContent);

        boolean isExistID = isExistInConsole(consoleContent, "" + id);
        Assertions.assertTrue(isExistID, consoleContent);
    }

    @Test
    void shouldByIDSub() {
        reload();
        int id = createSubTask("Name", "Desc");

        String consoleContentPrint = runCommand("printID " + id);
        boolean isExistNotificationID = isExistInConsole(consoleContentPrint, Notification.PRINT_ID.toString());
        Assertions.assertTrue(isExistNotificationID, consoleContentPrint);

        String consoleContent = runCommand("printHistory");
        boolean isExistNewName = isExistInConsole(consoleContent, Notification.PRINT_HISTORY.toString());
        Assertions.assertTrue(isExistNewName, consoleContent);

        boolean isExistID = isExistInConsole(consoleContent, "" + id);
        Assertions.assertTrue(isExistID, consoleContent);
    }
}
