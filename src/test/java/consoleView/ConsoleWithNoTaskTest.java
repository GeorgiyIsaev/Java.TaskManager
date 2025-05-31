package consoleView;

import com.consoleView.ConsoleView;
import com.controller.IManagerTask;
import com.controller.ManagerFile;
import com.controller.Managers;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class ConsoleWithNoTaskTest {
    //Класс провидит тест взаимодействия с несуществующим ID

    //Arguments.of("02.01 - Удаление несуществующий ID 0", Arrays.asList("deleteID 0", "-")),
    //                Arguments.of("02.01 - Изменяем несуществующий ID 0 ", Arrays.asList("reNameID 0", "Новое измененное имя")),
    //                Arguments.of("02.01 - Изменяем описание несуществующий ID 0 ", Arrays.asList("reDescID 0", "Новое измененное описание")),
    //                Arguments.of("02.01 - Изменяем статус на PROG несуществующий ID 0 ", Arrays.asList("newStatusId 0 PROG", "-")),
    //                Arguments.of("02.01 - Изменяем статус на DONE несуществующий ID 0 ", Arrays.asList("newStatusId 0 DONE", "-")),
    //                Arguments.of("02.01 - Изменяем статус на NEW несуществующий ID 0 ", Arrays.asList("newStatusId 0 NEW", "-")),
    //                Arguments.of("02.01 - Изменяем статус на Брантазябра несуществующий ID 0 ", Arrays.asList("newStatusId 0 Брантазябра", "-")),
    //                Arguments.of("02.01 - Изменяем статус несуществующий ID 0 не указав статус", Arrays.asList("newStatusId 0", "-")),
    //                Arguments.of("02.01 - Добавление Sub в несуществующий ID 0", Arrays.asList("addSubTaskToID 0", "Название SUB", "Описание SUB")),
    //                Arguments.of("02.01 - ПРИНТ - несуществующий ID 0", Arrays.asList("printId 0", "-")),
    //                Arguments.of("02.01 - ПРИНТ пустой список", Arrays.asList("printAll", "-")),


    public void setUp(String command) {
        System.setIn(new ByteArrayInputStream(command.getBytes()));
        //System.out.println("Список Команда: \n" + nextCommand);
    }

    @Test
    public void renameTest(){
        IManagerTask managerTaskInMemory = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTaskInMemory);
        String nextCommand = "deleteAll\nreNameID 0\nНовое Имя\nexit";

        setUp(nextCommand);
        consoleView.run();

        int countTask = managerTaskInMemory.getTasks().size();

    }
}
