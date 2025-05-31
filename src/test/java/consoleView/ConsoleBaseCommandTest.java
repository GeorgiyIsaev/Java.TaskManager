package consoleView;

import com.consoleView.ConsoleNotification;
import com.consoleView.ConsoleUtils;
import com.consoleView.ConsoleView;
import com.controller.IManagerTask;
import com.controller.Managers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleBaseCommandTest {
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


    @Test
    public void deleteAllTest(){
        IManagerTask managerTaskInMemory = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTaskInMemory);
        String nextCommand = "deleteall\nexit";
        setUp(nextCommand);
        consoleView.run();
        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent,ConsoleNotification.DELETE_ALL);
        Assertions.assertTrue(isExist);
    }

    @Test
    public void helpTest(){
        IManagerTask managerTaskInMemory = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTaskInMemory);
        String nextCommand = "help\nexit";
        setUp(nextCommand);
        consoleView.run();
        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent,ConsoleNotification.HELP);
        Assertions.assertTrue(isExist);
    }
    @Test
    public void saveTest(){
        IManagerTask managerTaskInMemory = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTaskInMemory);
        String nextCommand = "save\nexit";
        setUp(nextCommand);
        consoleView.run();
        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent,ConsoleNotification.SAVE);
        Assertions.assertTrue(isExist);
    }

    @Test
    public void exitTest(){
        IManagerTask managerTaskInMemory = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTaskInMemory);
        String nextCommand = "exit";
        setUp(nextCommand);
        consoleView.run();
        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent,ConsoleNotification.EXIT);
        Assertions.assertTrue(isExist);
    }

    @Test
    public void printAllTest(){
        IManagerTask managerTaskInMemory = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTaskInMemory);
        String nextCommand = "printAll\nexit";
        setUp(nextCommand);
        consoleView.run();
        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, ConsoleUtils.CONSOLE_TITLE);
        Assertions.assertTrue(isExist);
    }

    @Test
    public void notTrueCommandTest(){
        IManagerTask managerTaskInMemory = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTaskInMemory);
        String nextCommand = "Брантазябра\nexit";
        setUp(nextCommand);
        consoleView.run();
        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, ConsoleNotification.NOT_COMMAND);
        Assertions.assertTrue(isExist);
    }

}
