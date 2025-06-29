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
     HELP ( "ДОСТУПНЫЕ КОМАНДЫ:")
            ;

    private final String description;
    Notification(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
