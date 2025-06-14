package consoleView;

import com.consoleView.ConsoleUtils;
import com.consoleView.ConsoleView;
import com.controller.IManagerTask;
import com.controller.Managers;
import com.dateTask.CreateID;
import com.dateTask.Task;
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
    public boolean isExistInConsole(String allContent, String findContent){
        return allContent.toUpperCase().contains(findContent.toUpperCase());
    }


    /// ТЕСТ ИСТОРИИ ПРОСМОТРОВ
    @Test
    void printHistoryTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        int idEpic1 = CreateID.INSTANCE.getCurrentID();
        int idSub = CreateID.INSTANCE.getCurrentID();

        String nameEpic = "[TEСТОВОЕ НАЗВАНИЕ ЭПИКА]";
                String nameSub= "[Добавленный SubTask]";

                String commandAdd ="addEPIC " +nameEpic+"\nОписание\n" +
                "addSubTaskToID " + idEpic1 + "\n" +nameSub+ "\nСаб Описание\n" +
                "printID "+ idEpic1 + "\n"+
                "printHistory\nexit";

        setUp(commandAdd);
        consoleView.run();


        String consoleContent = outContent.toString();
        String findContent = ConsoleUtils.CONSOLE_TITLE;
        boolean isExistTitle = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExistTitle, consoleContent); //Успех отображен в консоли
    }
}
