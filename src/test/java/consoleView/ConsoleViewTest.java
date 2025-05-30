package consoleView;

import com.consoleView.ConsoleView;
import com.controller.IManagerTask;
import com.controller.ManagerFile;
import com.controller.Managers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.ByteArrayInputStream;
import java.util.List;

public class ConsoleViewTest {
    public static ConsoleView consoleView;

    @BeforeAll
    public static void start(){
        IManagerTask managerTaskInMemory = Managers.getDefault();
        ManagerFile.load(managerTaskInMemory);
        consoleView = new ConsoleView(managerTaskInMemory);

    }

    public void setUp(String command) {
        System.setIn(new ByteArrayInputStream(command.getBytes()));
    }

    @ParameterizedTest
    @ArgumentsSource(ConsoleInputCommandArgumentsProvider.class)
    public void manyCommandTest(String description, List<String> commands) {
        System.out.println("\n - - > " + description);
        StringBuilder nextCommand = new StringBuilder();

        if (!commands.isEmpty()) {
            nextCommand = new StringBuilder(commands.get(0));
        }

        for (int i = 1; i < commands.size(); i++) {
            nextCommand.append("\n");
            nextCommand.append(commands.get(i));
        }
        nextCommand.append("\n");
        nextCommand.append("exit"); //все команды завершаются exit
        nextCommand.append("\n");
        nextCommand.append("exit"); //все команды завершаются exit
        nextCommand.append("\n");
        System.out.println("Команда: " + nextCommand);

        setUp(nextCommand.toString());
        consoleView.run();
        System.out.println("Список команд: " + commands);

    }



}
