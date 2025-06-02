package consoleView;

import com.Main;
import com.consoleView.ConsoleNotification;
import com.consoleView.ConsoleUtils;
import com.consoleView.ConsoleView;
import com.controller.IManagerTask;
import com.controller.Managers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
    public void testMain() {
        String[] args = {""};
        String nextCommand = "exit\n";
        setUp(nextCommand);
        Main.main(args);

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent,ConsoleNotification.EXIT);
        Assertions.assertTrue(isExist, consoleContent);
    }

    @Test
    public void deleteAllTest(){
        IManagerTask managerTaskInMemory = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTaskInMemory);
        String nextCommand = "deleteAll\nexit";
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





    //ТЕСТЫ Обращения к задаче с несуществующим ID
    @ParameterizedTest(name = "ID не существует - Команда: {0}")
         //   "{index} - {0} is a palindrome")
    @ValueSource(strings = {
            "reNameID 0\nНовое Имя",
            "reDescID 0\nНовое описание",
            "addSubTaskToID 0\nНазвание SUB\nОписание SUB",
            "newStatusId 0 PROG",
            "newStatusId 0 DONE",
            "newStatusId 0 NEW",
            "newStatusId 0 Нет",
            "newStatusId 0",
            "printID 0"})
    public void callingNonExistentTask(String command){
        IManagerTask managerTaskInMemory = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTaskInMemory);

        command = "deleteAll\n" + command + "\nexit";
        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, ConsoleNotification.ID_NOT_EXIST);
        Assertions.assertTrue(isExist, consoleContent);
    }

    //ТЕСТЫ Обращения к задаче с неправильно указанным ID
    @ParameterizedTest(name = "ID не существует - Команда: {0}")
    //   "{index} - {0} is a palindrome")
    @ValueSource(strings = {
            "reNameID Ноль\nНовое имя",
            "reDescID Ноль\nНовое описание",
            "addSubTaskToID Ноль\nНазвание SUB\nОписание SUB",
            "newStatusId Ноль PROG",
            "newStatusId Ноль DONE",
            "newStatusId Ноль NEW",
            "newStatusId Ноль Нет",
            "newStatusId Ноль",
            "deleteId Ноль",
            "printID Ноль"})
    public void callingIncorrectIdSpecifiedTaskTest(String command){
        IManagerTask managerTaskInMemory = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTaskInMemory);

        command = "deleteAll\n" + command + "\nexit";
        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, ConsoleNotification.ID_NOT_INPUT);
        Assertions.assertTrue(isExist, consoleContent);
    }
}
