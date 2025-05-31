package consoleView;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Arrays;
import java.util.stream.Stream;

public class ConsoleInputCommandArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        Stream<Arguments> argumentsStream = Stream.of(
                Arguments.of("01.01 - Вызов подсказок", Arrays.asList("help","-")),
                Arguments.of("01.02 - Сохраняем", Arrays.asList("save", "-")),
                Arguments.of("01.02 - Очищаем список, выводим пустой список", Arrays.asList("deleteAll", "printAll", "-")),
               /// Arguments.of("01.03 - ПРИНТ пустой список", Arrays.asList("printAll", "-")),
                Arguments.of("01.03 - Сохраняем", Arrays.asList("save", "-")),
                Arguments.of("01.04 - Выходим", Arrays.asList("exit", "-")),

                /// Взаимодействие с не существующим TASK
                Arguments.of("02.01 - Удаление несуществующий ID 0", Arrays.asList("deleteID 0", "-")),
                Arguments.of("02.01 - Изменяем несуществующий ID 0 ", Arrays.asList("reNameID 0", "Новое измененное имя")),
                Arguments.of("02.01 - Изменяем описание несуществующий ID 0 ", Arrays.asList("reDescID 0", "Новое измененное описание")),
                Arguments.of("02.01 - Изменяем статус на PROG несуществующий ID 0 ", Arrays.asList("newStatusId 0 PROG", "-")),
                Arguments.of("02.01 - Изменяем статус на DONE несуществующий ID 0 ", Arrays.asList("newStatusId 0 DONE", "-")),
                Arguments.of("02.01 - Изменяем статус на NEW несуществующий ID 0 ", Arrays.asList("newStatusId 0 NEW", "-")),
                Arguments.of("02.01 - Изменяем статус на Брантазябра несуществующий ID 0 ", Arrays.asList("newStatusId 0 Брантазябра", "-")),
                Arguments.of("02.01 - Изменяем статус несуществующий ID 0 не указав статус", Arrays.asList("newStatusId 0", "-")),
                Arguments.of("02.01 - Добавление Sub в несуществующий ID 0", Arrays.asList("addSubTaskToID 0", "Название SUB", "Описание SUB")),
                Arguments.of("02.01 - ПРИНТ - несуществующий ID 0", Arrays.asList("printId 0", "-")),
                Arguments.of("02.01 - ПРИНТ пустой список", Arrays.asList("printAll", "-")),

                /// Взаимодействие с обычным TASK ID=0
                Arguments.of("03.01 - Добавление TASK", Arrays.asList("add Название", "Описание")),
                Arguments.of("03.01 -  ПРИНТ одного TASK", Arrays.asList("printAll", "-")),
                Arguments.of("03.01 - Изменяем имя ID 0 ", Arrays.asList("reNameID 0", "Новое измененное имя")),
                Arguments.of("03.01 - Изменяем описание ID 0 ", Arrays.asList("reDescID 0", "Новое измененное описание")),
                Arguments.of("03.01 - Изменяем статус на PROG ID 0 ", Arrays.asList("newStatusId 0 PROG", "-")),
                Arguments.of("03.01 -  ПРИНТ одного TASK cо статусом PROG", Arrays.asList("printAll", "-")),
                Arguments.of("Изменяем статус на DONE ID 0 ", Arrays.asList("newStatusId 0 DONE", "-")),
                Arguments.of("03.01 -  ПРИНТ одного TASK cо статусом DONE", Arrays.asList("printAll", "-")),
                Arguments.of("03.01 - Изменяем статус на NEW ID 0 ", Arrays.asList("newStatusId 0 NEW", "-")),
                Arguments.of("03.01 - ПРИНТ одного TASK cо статусом NEW ID", Arrays.asList("printAll", "-")),
                Arguments.of("03.01 - Изменяем статус на Нет такого статуса ID 0 ", Arrays.asList("newStatusId 0 Брантазябра", "-")),
                Arguments.of("03.01 - Изменяем статус ID 0 не указал статус", Arrays.asList("newStatusId 0", "-")),
                Arguments.of("03.01 - Добавление Sub в ID 0 (Обычный Таск)", Arrays.asList("addSubTaskToID 0", "Название SUB", "Описание SUB")),
                Arguments.of("03.01 - ПРИНТ - ID 0", Arrays.asList("printId 0", "-")),
                Arguments.of("03.01 - ПРИНТ одного TASK", Arrays.asList("printAll", "-")),

                /// Взаимодействие с обычным EPIC ID=1 (+SUB ID=2)
                Arguments.of("04.01 - Добавление Epic", Arrays.asList("addEpic Название", "Описание")),
                Arguments.of("04.01 - ПРИНТ Список TASK", Arrays.asList("printAll", "-")),
                Arguments.of("04.01 - Изменяем имя ID 1 ", Arrays.asList("reNameID 1", "Новое измененное имя")),
                Arguments.of("04.01 - Изменяем описание ID 1 ", Arrays.asList("reDescID 1", "Новое измененное описание")),
                Arguments.of("04.01 - Изменяем статус на PROG ID 1 ", Arrays.asList("newStatusId 1 PROG", "-")),
                Arguments.of("04.01 - ПРИНТ одного Epic cо статусом PROG", Arrays.asList("printAll", "-")),
                Arguments.of("04.01 - Изменяем статус на DONE ID 1 ", Arrays.asList("newStatusId 1 DONE", "-")),
                Arguments.of("04.01 -  ПРИНТ одного Epic cо статусом DONE", Arrays.asList("printAll", "-")),
                Arguments.of("04.01 - Изменяем статус на NEW ID 1 ", Arrays.asList("newStatusId 1 NEW", "-")),
                Arguments.of("04.01 - ПРИНТ одного TASK cо статусом NEW ID", Arrays.asList("printAll", "-")),
                Arguments.of("04.01 - Изменяем статус на Epic такого статуса ID 1 ", Arrays.asList("newStatusId 1 Брантазябра", "-")),
                Arguments.of("04.01 - Изменяем статус ID 1 не указал статус", Arrays.asList("newStatusId 1", "-")),
                Arguments.of("04.01 - Добавление Sub в ID 1", Arrays.asList("addSubTaskToID 1", "Название SUB", "Описание SUB")),
                Arguments.of("04.01 - ПРИНТ - ID 1", Arrays.asList("printId 1", "-")),
                Arguments.of("04.01 -  ПРИНТ Список TASK", Arrays.asList("printAll", "-")),

                /// Взаимодействие с SUB ID=3
                Arguments.of("05.01 - Добавление Sub в ID 1 (Обычный Таск)", Arrays.asList("addSubTaskToID 1", "Название SUB 2", "Описание SUB 2")),
                Arguments.of("05.01 - ПРИНТ Список SUB", Arrays.asList("printAll", "-")),
                Arguments.of("05.01 - Изменяем имя ID 3 ", Arrays.asList("reNameID 3", "Новое измененное имя")),
                Arguments.of("05.01 - Изменяем описание ID 3 ", Arrays.asList("reDescID 3", "Новое измененное описание")),
                Arguments.of("05.01 - Изменяем статус на PROG ID 3", Arrays.asList("newStatusId 3 PROG", "-")),
                Arguments.of("05.01 - ПРИНТ одного SUB cо статусом PROG", Arrays.asList("printAll", "-")),
                Arguments.of("05.01 - Изменяем статус на DONE ID 3 ", Arrays.asList("newStatusId 3 DONE", "-")),
                Arguments.of("05.01 - ПРИНТ одного SUB cо статусом DONE", Arrays.asList("printAll", "-")),
                Arguments.of("05.01 - Изменяем статус на NEW ID 3 ", Arrays.asList("newStatusId 3 NEW", "-")),
                Arguments.of("05.01 - ПРИНТ одного SUB cо статусом NEW ID 3", Arrays.asList("printAll", "-")),
                Arguments.of("05.01 - Изменяем статус на Нет такого статуса ID 3 ", Arrays.asList("newStatusId 3 Брантазябра", "-")),
                Arguments.of("05.01 - Изменяем статус ID 3 не указал статус", Arrays.asList("newStatusId 3", "-")),
                Arguments.of("05.01 - Добавление Sub в ID 3 (Нельзя добавить SUB в SUB)", Arrays.asList("addSubTaskToID 3", "Название SUB", "Описание SUB")),
                Arguments.of("05.01 -  ПРИНТ - ID 3", Arrays.asList("printId 3", "-")),
                Arguments.of("05.01 -  ПРИНТ Список TASK", Arrays.asList("printAll", "-")),

                /// Дополнительное добавление
                Arguments.of("06.01 - Добавление Таск", Arrays.asList("add Название", "Описание")), //ID 4
                Arguments.of("06.01 - Добавление Epic", Arrays.asList("addEpic Название", "Описание")), //ID 5
                Arguments.of("06.01 - Добавление Sub в ID 5", Arrays.asList("addSubTaskToID 5", "Название SUB 1001", "Описание SUB 2")), //6
                Arguments.of("06.01 - Добавление Sub в ID 5", Arrays.asList("addSubTaskToID 5", "Название SUB 1002", "Описание SUB 2")), //7
                Arguments.of("06.01 - Добавление Sub в ID 5", Arrays.asList("addSubTaskToID 5", "Название SUB 1003", "Описание SUB 2")), //8
                Arguments.of("06.01 - Добавление Epic", Arrays.asList("addEpic Название", "Описание")), //ID 9
                Arguments.of("06.01 - Добавление Sub в ID 9", Arrays.asList("addSubTaskToID 9", "Название SUB 9001", "Описание SUB 2")), //10
                Arguments.of("06.01 - Добавление Таск", Arrays.asList("add Название", "Описание")), //ID 11


                /// РАЗНЫЕ ПРИНТЫ
                Arguments.of("------>> ПРИНТ Список всех TASK", Arrays.asList("printAll", "-")),
                Arguments.of("------>> ПРИНТ Список только TASK", Arrays.asList("printTask", "-")),
                Arguments.of("------>> ПРИНТ Список только EPIC", Arrays.asList("printEpic", "-")),
                Arguments.of("------>> ПРИНТ Список только SUB", Arrays.asList("printSubTask", "-")),
                Arguments.of("------>> ПРИНТ DEBUG", Arrays.asList("printDebug", "-")),
                Arguments.of("------>> ПРИНТ History", Arrays.asList("printHistory", "-")),

                /// Взаимодействие с список Истории
                Arguments.of("08.01 - ПРИНТ - ID 4", Arrays.asList("printId 4", "-")),
                Arguments.of("08.01 - ПРИНТ - ID 4", Arrays.asList("printId 4", "-")),
                Arguments.of("08.01 - ПРИНТ - ID 0", Arrays.asList("printId 4", "-")),
                Arguments.of("08.01 - ПРИНТ - ID 5 (ЭПИК)", Arrays.asList("printId 5", "-")),
                Arguments.of("08.01 - ПРИНТ - ID 8", Arrays.asList("printId 4", "-")),
                Arguments.of("08.01 - ПРИНТ History", Arrays.asList("printHistory", "-")),

                /// Удаления по ID
                Arguments.of("09.01 - Удаление по ID 4 Обычный таск)", Arrays.asList("deleteID 4", "-")),
                Arguments.of("09.01 - ПРИНТ - ID 4", Arrays.asList("printId 4", "-")),
                Arguments.of("09.01 - Удаление по ID 7 SUB он должен пропасть и у Эпика 5)", Arrays.asList("deleteID 7", "-")),
                Arguments.of("09.01 - ПРИНТ - ID 5 (ЭПИК)", Arrays.asList("printId 5", "-")),
                Arguments.of("09.01 - Удаление по ID 9 ЭПИК вместе с ним должен удалится SUB 10)", Arrays.asList("deleteID 9", "-")),
                Arguments.of("09.01 - ПРИНТ Список всех TASK", Arrays.asList("printAll", "-")),
                Arguments.of("09.01 - ПРИНТ History", Arrays.asList("printHistory", "-")),

                /// УДАЛЕНИЕ ВСЕГО СПИСКА
                Arguments.of("10.01 - ПРИНТ Список всех TASK", Arrays.asList("printAll", "-")),
                Arguments.of("10.01 - УДАЛЕНИЕ ВСЕГО СПИСКА", Arrays.asList("deleteAll", "-")),
                Arguments.of("10.01 - ПРИНТ Список всех TASK", Arrays.asList("printAll", "-")),
                Arguments.of("10.01 - ПРИНТ History", Arrays.asList("printHistory", "-"))
        );
        return argumentsStream;
    }
}
