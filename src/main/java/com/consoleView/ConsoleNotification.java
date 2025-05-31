package com.consoleView;

public class ConsoleNotification {

    public static final String SAVE = "Содержимое Task Manager сохранено в файл!";
    public static final String DELETE_ALL= "Все содержимое Task Manager удалено!";
    public static final String HELP = """
            ДОСТУПНЫЕ КОМАНДЫ:\s
             "help" - показать список команда
             "exit" - завершить программу
             "save" - сохранить все записи
            КОМАНДЫ ДЛЯ ОТОБРАЖЕНИЯ ЗАДАЧ:\s
             "printAll" - показать все задачи
             "printEpic" - показать только ЭПИКИ
             "printSubTask" - показать только ПОДЗАДАЧИ\s
             "printHistory'" - показать историю вызова задач
             "printTask" - показать только ОБЫЧНЫЕ ЗАДАЧИ\s
             "printId 'NUMBER ID'" - показать задачу по id
            КОМАНДЫ ДЛЯ ДОБАВЛЕНИЯ ЗАДАЧ:\s
             "add 'указать имя задач'" - добавить обычную задачу
             "addEpic 'указать имя задач'" - добавить задачу c подзадачами
             "addSubTaskToID 'NUMBER ID'" - добавить подзадачу к Эпику с указанным ID
            УДАЛЕНИЕ И ИЗМЕНЕНИЕ ЗАДАЧ:\s
             "deleteAll" - удалить все задачи
             "deleteID 'NUMBER ID'" - удалить задачу с ID
             "reNameID 'NUMBER ID'" – изменить имя задачи с ID
             "reDescID 'NUMBER ID'" – изменить описание задачи с ID
             "newStatusId 'NUMBER ID' ('NEW, 'PROG' or 'DONE')"  – изменить статус выполнения задачи с ID
            """;
}
