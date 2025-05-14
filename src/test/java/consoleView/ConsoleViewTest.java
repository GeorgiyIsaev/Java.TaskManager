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
    public static class MyClassArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of("Простые команды", Arrays.asList("help", "-")),
                    Arguments.of("--> ПРИНТ - Проверяем что список пустой", Arrays.asList("printAll", "-")),
                    Arguments.of("Добавление Таск", Arrays.asList("add Название", "Описание")),
                    Arguments.of("Добавление Epic", Arrays.asList("addEpic Название", "Описание")),
                    Arguments.of("Добавление Sub", Arrays.asList("addSubTaskToID 1 Лишние буквы", "Название", "Описание")),
                    Arguments.of("Добавление Sub без лишних букв", Arrays.asList("addSubTaskToID 1", "Название", "Описание")),
                    Arguments.of("Принт ID 1 (Эпик и его 2 Саба)", Arrays.asList("printId 1", "Брантазябра>", "Брантазябра2")),
                    Arguments.of("printEpic", Arrays.asList("printEpic", "00000")),
                    Arguments.of("printSubTask", Arrays.asList("printSubTask", "00000")),
                    Arguments.of("--> ПРИНТ Смотрим на общее список", Arrays.asList("printAll", "-")),
                    Arguments.of("Удаление по ID 2", Arrays.asList("deleteID 2", "-")),
                    Arguments.of("Удаление по ID 1000 (нету)", Arrays.asList("deleteID 1000", "-")),
                    Arguments.of("Переименование", Arrays.asList("reNameID 0", "Новое имя", "Блок")),
                    Arguments.of("Переименование", Arrays.asList("reDescID 0", "Новое описание")),
                    Arguments.of("--> ПРИНТ - ID 0 - Должен изменить имя и описание на новое", Arrays.asList("printId 0", "-")),
                    Arguments.of("Новый статус ID 0", Arrays.asList("newStatusId 0 DONE", "-")),
                    Arguments.of("Новый статус для Эпика ID 1", Arrays.asList("newStatusId 1 PROG","-")),
                    Arguments.of("Новый статус для Эпика ID 2 Должен сказать что такого статуса нет", Arrays.asList("newStatusId 2 DONE","-")),
                    Arguments.of("--> ПРИНТ проверяем что таск изменил статус а эпик не изменил", Arrays.asList("printAll", "-")),
                    Arguments.of("Новый статус для Sub ID 3", Arrays.asList("newStatusId 3 PROG", "-")),
                    Arguments.of("--> ПРИНТ проверяем что саб изменил статус и его эпик также сменил статут на PROG", Arrays.asList("printAll", "-")),
                    Arguments.of("Очищаем список", Arrays.asList("deleteAll", "-")),
                    Arguments.of("Сохраняем в Файл Пустой список", Arrays.asList("save", "-")),
                    Arguments.of("--> ПРИНТ - Проверяем что список пустой", Arrays.asList("printAll", "-")),

                    Arguments.of("Добавление Sub в несуществующий эпик", Arrays.asList("addSubTaskToID 1 Лишние буквы", "Название", "Описание")),
                    Arguments.of("Удаление по ID 2 (не существует)", Arrays.asList("deleteID 2", "-")),
                    Arguments.of("Переименование ID 0 (не существует)", Arrays.asList("reNameID 0", "Новое имя", "Блок"))
            );
        }

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


}
