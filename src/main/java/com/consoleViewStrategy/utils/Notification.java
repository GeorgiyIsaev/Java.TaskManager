package com.consoleViewStrategy.utils;

public enum Notification {
    HELP("ДОСТУПНЫЕ КОМАНДЫ:"),
    EXIT("Спасибо за работу!"),
    NOT_COMMAND("Неверная команда"),

    PRINT_ALL("ТАБЛИЦА ВСЕХ ЗАДАЧ:"),
    PRINT_onlyTASK("ТАБЛИЦА Только с ПРОСТЫМИ ЗАДАЧАМИ (TASK):"),
    PRINT_EPIC("ТАБЛИЦА Только с Эпиками (EPIC):"),
    PRINT_SUB("ТАБЛИЦА Только с ПОДЗАДАЧАМИ (SUBTASK):"),
    PRINT_ID("ТАБЛИЦА С ОДНОЙ ЗАДАЧЕЙ"),
    PRINT_HISTORY("ТАБЛИЦА с историей вызовов задач"),

    DELETE_ALL("Все содержимое Task Manager удалено!"),
    DELETE_ByID("Задача успешна удалена"),

    ADD_TASK("Новая задача добавлена!"),
    ADD_EPIC("Новый ЭПИК добавлен!"),
    ADD_SUBTASK("Новая подзадача прикреплена к Эпику"),

    REDESC("Описание задачи изменено успешно!"),
    RENAME("Имя задачи изменено успешно!"),
    RESTATUS("Статус задачи изменено успешно!"),
    STATUS_INCORRECTLY("ERROR: Не верно введен статус! Допустимые значения 'NEW' 'PROG' 'DONE'"),

    NOT_CHANGE_STATUS("Нельзя изменить статус EPIC"),
    NOT_EPIC("Задача с указанным ID не EPIC"),
    ID_NOT_INPUT("ERROR: Не указан ID задачи!"),
    ID_NOT_EXIST("ERROR: Задачи с указанным ID не существует!"),


    ;

    private final String description;

    Notification(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
