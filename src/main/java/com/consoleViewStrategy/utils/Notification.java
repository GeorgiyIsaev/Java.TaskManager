package com.consoleViewStrategy.utils;

public enum Notification {
     STATUS_INCORRECTLY("ERROR: Не верно введен статус! Допустимые значения 'NEW' 'PROG' 'DONE'"),
     NOT_CHANGE_STATUS ("Нельзя изменить статус EPIC"),
     RESTATUS ("Статус задачи изменено успешно!"),
     REDESC ("Описание задачи изменено успешно!"),
     RENAME ("Наименование задачи изменено успешно!"),

     DELETE_TASK  ("СЛЕДУЮЩАЯ ЗАДАЧА УДАЛЕНА"),
     NOT_EPIC  ("Задача с указанным ID не EPIC"),
     ID_NOT_INPUT  ("ERROR: Не указан ID задачи!"),
     ID_NOT_EXIST  ("ERROR: Задачи с указанным ID не существует!"),
     NOT_COMMAND  ("Неверная команда"),
     EXIT  ("Спасибо за работу!"),
     SAVE ("Содержимое Task Manager сохранено в файл!"),
     DELETE_ALL ("Все содержимое Task Manager удалено!"),
     HELP ( """
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
            """);

    private final String description;
    Notification(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
