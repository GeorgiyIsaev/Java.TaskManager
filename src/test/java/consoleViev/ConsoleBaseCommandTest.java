package consoleView;

import com.consoleViewStrategy.Main;
import com.consoleViewStrategy.utils.Notification;
import com.consoleViewStrategy.utils.TaskToString;
import com.controller.taskManager.TaskManager;
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

    ///  ТЕСТЫ
    @Test
    public void exitTest() {
        String nextCommand = "exit\n";
        setUp(nextCommand);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, Notification.EXIT.toString());
        Assertions.assertTrue(isExist, consoleContent);
    }

    @Test
    public void deleteAllTest(){
        String nextCommand = "deleteAll\nexit";
        setUp(nextCommand);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent,Notification.DELETE_ALL.toString());
        Assertions.assertTrue(isExist);
    }

    @Test
    public void helpTest(){
        String nextCommand = "help\nexit";
        setUp(nextCommand);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent,Notification.HELP.toString());
        Assertions.assertTrue(isExist, consoleContent);
    }


    @Test
    public void printAllTest(){
        String nextCommand = "printAll\nexit";
        setUp(nextCommand);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, TaskToString.CONSOLE_TITLE);
        Assertions.assertTrue(isExist);
    }

    @Test
    public void notTrueCommandTest(){
        String nextCommand = "Брантазябра\nexit";
        setUp(nextCommand);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, Notification.NOT_COMMAND.toString());
        Assertions.assertTrue(isExist);
    }


    //ТЕСТЫ Обращения к задаче с несуществующим ID
    @ParameterizedTest(name = "ID не существует - Команда: {0}")
         //   "{index} - {0} is a palindrome")
    @ValueSource(strings = {
            "reName 0\nНовое Имя",
            "reDesc 0\nНовое описание",
            "addSubTaskToID 0\nНазвание SUB\nОписание SUB",
            "reStatus 0 PROG",
            "reStatus 0 DONE",
            "reStatus 0 NEW",
            "reStatus 0 Нет",
            "reStatus 0",
            "deleteId 0",
            "printID 0"})
    public void callingNonExistentTask(String command){
        command = "deleteAll\n" + command + "\nHELP\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, Notification.ID_NOT_EXIST.toString());
        Assertions.assertTrue(isExist, consoleContent);
    }

    //ТЕСТЫ Обращения к задаче с неправильно указанным ID
    @ParameterizedTest(name = "ID не существует - Команда: {0}")
    //   "{index} - {0} is a palindrome")
    @ValueSource(strings = {
            "reName Ноль\nНовое",
            "reDesc Ноль\nНовое описание",
            "addSubTaskToID Ноль\nНазвание SUB\nОписание SUB",
            "reStatus Ноль PROG",
            "reStatus Ноль DONE",
            "reStatus Ноль NEW",
            "reStatus Ноль Нет",
            "reStatus Ноль",
            "reStatus",
            "reStatus\n\n\n",
            "reStatus \n \n \n",
            "reStatus \n 0\n 0\n",
            "deleteId Ноль",
            "printID Ноль"})
    public void callingIncorrectIdSpecifiedTaskTest(String command){
        command = "deleteAll\n" + command + "\nHELP\nexit";
        setUp(command);
        startMain();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, Notification.ID_NOT_INPUT.toString());
        Assertions.assertTrue(isExist, consoleContent);
    }
}
