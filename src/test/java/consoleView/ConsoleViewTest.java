package consoleView;

import com.consoleView.ConsoleView;
import com.controller.IManagerTask;
import com.controller.ManagerFile;
import com.controller.Managers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class ConsoleViewTest {
    public static  ConsoleView consoleView;

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
    @ArgumentsSource(MyClassArgumentsProvider.class)
    public void manyCommandTest(String description, List<String> commands) {
        System.out.println("\n - - > " + description);
        StringBuilder nextCommand = new StringBuilder();

        if (commands.size() > 1) {
            nextCommand = new StringBuilder(commands.get(0));
        }

        for (int i = 1; i < commands.size(); i++) {
            nextCommand.append("\n");
            nextCommand.append(commands.get(i));
        }
        nextCommand.append("\n");
        nextCommand.append("exit"); //все команды завершаются exit
        setUp(nextCommand.toString());
        consoleView.run();
        System.out.println("Список команд: " + commands);

    }


    public static class MyClassArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            Stream<Arguments> argumentsStream = Stream.of(
                    Arguments.of("Вызов подсказок", Arrays.asList("help", "-")),
                    Arguments.of("Очищаем список", Arrays.asList("deleteAll", "-")),
                    Arguments.of("-->> ПРИНТ пустой список", Arrays.asList("printAll", "-")),

                    /// Взаимодействие с не существующим TASK
                    Arguments.of("Удаление по ID 0", Arrays.asList("deleteID 0", "-")),
                    Arguments.of("Изменяем имя ID 0 ", Arrays.asList("reNameID 0", "Новое измененное имя")),
                    Arguments.of("Изменяем описание ID 0 ", Arrays.asList("reDescID 0", "Новое измененное описание")),
                    Arguments.of("Изменяем статус на PROG ID 0 ", Arrays.asList("newStatusId 0 PROG", "-")),
                    Arguments.of("Изменяем статус на DONE ID 0 ", Arrays.asList("newStatusId 0 DONE", "-")),
                    Arguments.of("Изменяем статус на NEW ID 0 ", Arrays.asList("newStatusId 0 NEW", "-")),
                    Arguments.of("Изменяем статус на Нет такого статуса ID 0 ", Arrays.asList("newStatusId 0 Брантазябра", "-")),
                    Arguments.of("Изменяем статус ID 0 не указал статус", Arrays.asList("newStatusId 0", "-")),
                    Arguments.of("Добавление Sub", Arrays.asList("addSubTaskToID 0", "Название SUB", "Описание SUB")),
                    Arguments.of("--> ПРИНТ - ID 0", Arrays.asList("printId 0", "-")),
                    Arguments.of("------>> ПРИНТ пустой список", Arrays.asList("printAll", "-")),

                    /// Взаимодействие с обычным TASK ID=0
                    Arguments.of("!!!!!! Добавление TASK", Arrays.asList("add Название", "Описание")),
                    Arguments.of("-->> ПРИНТ одного TASK", Arrays.asList("printAll", "-")),
                    Arguments.of("Изменяем имя ID 0 ", Arrays.asList("reNameID 0", "Новое измененное имя")),
                    Arguments.of("Изменяем описание ID 0 ", Arrays.asList("reDescID 0", "Новое измененное описание")),
                    Arguments.of("Изменяем статус на PROG ID 0 ", Arrays.asList("newStatusId 0 PROG", "-")),
                    Arguments.of("-->> ПРИНТ одного TASK cо статусом PROG", Arrays.asList("printAll", "-")),
                    Arguments.of("Изменяем статус на DONE ID 0 ", Arrays.asList("newStatusId 0 DONE", "-")),
                    Arguments.of("-->> ПРИНТ одного TASK cо статусом DONE", Arrays.asList("printAll", "-")),
                    Arguments.of("Изменяем статус на NEW ID 0 ", Arrays.asList("newStatusId 0 NEW", "-")),
                    Arguments.of("-->> ПРИНТ одного TASK cо статусом NEW ID", Arrays.asList("printAll", "-")),
                    Arguments.of("Изменяем статус на Нет такого статуса ID 0 ", Arrays.asList("newStatusId 0 Брантазябра", "-")),
                    Arguments.of("Изменяем статус ID 0 не указал статус", Arrays.asList("newStatusId 0", "-")),
                    Arguments.of("Добавление Sub в ID 0 (Обычный Таск)", Arrays.asList("addSubTaskToID 0", "Название SUB", "Описание SUB")),
                    Arguments.of("--> ПРИНТ - ID 0", Arrays.asList("printId 0", "-")),
                    Arguments.of("------>> ПРИНТ одного TASK", Arrays.asList("printAll", "-")),

                    /// Взаимодействие с обычным EPIC ID=1 (+SUB ID=2)
                    Arguments.of("!!!!! Добавление Epic", Arrays.asList("addEpic Название", "Описание")),
                    Arguments.of("-->> ПРИНТ Список TASK", Arrays.asList("printAll", "-")),
                    Arguments.of("Изменяем имя ID 1 ", Arrays.asList("reNameID 1", "Новое измененное имя")),
                    Arguments.of("Изменяем описание ID 1 ", Arrays.asList("reDescID 1", "Новое измененное описание")),
                    Arguments.of("Изменяем статус на PROG ID 1 ", Arrays.asList("newStatusId 1 PROG", "-")),
                    Arguments.of("-->> ПРИНТ одного Epic cо статусом PROG", Arrays.asList("printAll", "-")),
                    Arguments.of("Изменяем статус на DONE ID 1 ", Arrays.asList("newStatusId 1 DONE", "-")),
                    Arguments.of("-->> ПРИНТ одного Epic cо статусом DONE", Arrays.asList("printAll", "-")),
                    Arguments.of("Изменяем статус на NEW ID 1 ", Arrays.asList("newStatusId 1 NEW", "-")),
                    Arguments.of("-->> ПРИНТ одного TASK cо статусом NEW ID", Arrays.asList("printAll", "-")),
                    Arguments.of("Изменяем статус на Epic такого статуса ID 1 ", Arrays.asList("newStatusId 1 Брантазябра", "-")),
                    Arguments.of("Изменяем статус ID 1 не указал статус", Arrays.asList("newStatusId 1", "-")),
                    Arguments.of("Добавление Sub в ID 1", Arrays.asList("addSubTaskToID 1", "Название SUB", "Описание SUB")),
                    Arguments.of("--> ПРИНТ - ID 1", Arrays.asList("printId 1", "-")),
                    Arguments.of("------>> ПРИНТ Список TASK",  Arrays.asList("printAll", "-")),

                    /// Взаимодействие с SUB ID=3
                    Arguments.of("!!!!!! Добавление Sub в ID 1 (Обычный Таск)", Arrays.asList("addSubTaskToID 1", "Название SUB 2", "Описание SUB 2")),
                    Arguments.of("-->> ПРИНТ Список SUB", Arrays.asList("printAll", "-")),
                    Arguments.of("Изменяем имя ID 3 ", Arrays.asList("reNameID 3", "Новое измененное имя")),
                    Arguments.of("Изменяем описание ID 3 ", Arrays.asList("reDescID 3", "Новое измененное описание")),
                    Arguments.of("Изменяем статус на PROG ID 3", Arrays.asList("newStatusId 3 PROG", "-")),
                    Arguments.of("-->> ПРИНТ одного SUB cо статусом PROG", Arrays.asList("printAll", "-")),
                    Arguments.of("Изменяем статус на DONE ID 3 ", Arrays.asList("newStatusId 3 DONE", "-")),
                    Arguments.of("-->> ПРИНТ одного SUB cо статусом DONE", Arrays.asList("printAll", "-")),
                    Arguments.of("Изменяем статус на NEW ID 3 ", Arrays.asList("newStatusId 3 NEW", "-")),
                    Arguments.of("-->> ПРИНТ одного SUB cо статусом NEW ID 3", Arrays.asList("printAll", "-")),
                    Arguments.of("Изменяем статус на Нет такого статуса ID 3 ", Arrays.asList("newStatusId 3 Брантазябра", "-")),
                    Arguments.of("Изменяем статус ID 3 не указал статус", Arrays.asList("newStatusId 3", "-")),
                    Arguments.of("Добавление Sub в ID 3 (Нельзя добавить SUB в SUB)", Arrays.asList("addSubTaskToID 3", "Название SUB", "Описание SUB")),
                    Arguments.of("--> ПРИНТ - ID 3", Arrays.asList("printId 3", "-")),
                    Arguments.of("------>> ПРИНТ Список TASK", Arrays.asList("printAll", "-")),

                    /// Дополнительное добавление
                    Arguments.of("Добавление Таск", Arrays.asList("add Название", "Описание")), //ID 4
                    Arguments.of("Добавление Epic", Arrays.asList("addEpic Название", "Описание")), //ID 5
                    Arguments.of("Добавление Sub в ID 5", Arrays.asList("addSubTaskToID 5", "Название SUB 1001", "Описание SUB 2")), //6
                    Arguments.of("Добавление Sub в ID 5", Arrays.asList("addSubTaskToID 5", "Название SUB 1002", "Описание SUB 2")), //7
                    Arguments.of("Добавление Sub в ID 5", Arrays.asList("addSubTaskToID 5", "Название SUB 1003", "Описание SUB 2")), //8
                    Arguments.of("Добавление Epic", Arrays.asList("addEpic Название", "Описание")), //ID 9
                    Arguments.of("Добавление Sub в ID 9", Arrays.asList("addSubTaskToID 9", "Название SUB 9001", "Описание SUB 2")), //10
                    Arguments.of("Добавление Таск", Arrays.asList("add Название", "Описание")), //ID 11


                    /// РАЗНЫЕ ПРИНТЫ
                    Arguments.of("------>> ПРИНТ Список всех TASK", Arrays.asList("printAll", "-")),
                    Arguments.of("------>> ПРИНТ Список только TASK", Arrays.asList("printTask", "-")),
                    Arguments.of("------>> ПРИНТ Список только EPIC", Arrays.asList("printEpic", "-")),
                    Arguments.of("------>> ПРИНТ Список только SUB", Arrays.asList("printSubTask", "-")),
                    Arguments.of("------>> ПРИНТ DEBUG", Arrays.asList("printDebug", "-")),
                    Arguments.of("------>> ПРИНТ History", Arrays.asList("printHistory", "-")),

                    /// Взаимодействие с список Истории
                    Arguments.of("--> ПРИНТ - ID 4", Arrays.asList("printId 4", "-")),
                    Arguments.of("--> ПРИНТ - ID 4", Arrays.asList("printId 4", "-")),
                    Arguments.of("--> ПРИНТ - ID 0", Arrays.asList("printId 4", "-")),
                    Arguments.of("--> ПРИНТ - ID 5 (ЭПИК)", Arrays.asList("printId 5", "-")),
                    Arguments.of("--> ПРИНТ - ID 8", Arrays.asList("printId 4", "-")),
                    Arguments.of("------>> ПРИНТ History", Arrays.asList("printHistory", "-")),

                    /// Удаления по ID
                    Arguments.of("Удаление по ID 4 Обычный таск)", Arrays.asList("deleteID 4", "-")),
                    Arguments.of("--> ПРИНТ - ID 4", Arrays.asList("printId 4", "-")),
                    Arguments.of("Удаление по ID 7 SUB он должен пропасть и у Эпика 5)", Arrays.asList("deleteID 7", "-")),
                    Arguments.of("--> ПРИНТ - ID 5 (ЭПИК)", Arrays.asList("printId 5", "-")),
                    Arguments.of("Удаление по ID 9 ЭПИК вместе с ним должен удалится SUB 10)", Arrays.asList("deleteID 9", "-")),
                    Arguments.of("------>> ПРИНТ Список всех TASK", Arrays.asList("printAll", "-")),
                    Arguments.of("------>> ПРИНТ History", Arrays.asList("printHistory", "-")),

                    /// УДАЛЕНИЕ ВСЕГО СПИСКА
                    Arguments.of("------>> ПРИНТ Список всех TASK", Arrays.asList("printAll", "-")),
                    Arguments.of("------>> УДАЛЕНИЕ ВСЕГО СПИСКА", Arrays.asList("deleteAll", "-")),
                    Arguments.of("------>> ПРИНТ Список всех TASK", Arrays.asList("printAll", "-")),
                    Arguments.of("------>> ПРИНТ History", Arrays.asList("printHistory", "-"))
            );
            return argumentsStream;
        }

    }


}
