package controller;

import com.controller.taskManager.TaskManager;
import com.controller.Managers;
import com.controller.controlException.NotEpicException;
import com.controller.controlException.NotExistIdException;
import com.dateTask.Task;
import com.dateTask.TaskStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManagerTaskInMemoryExceptionTest {

    ///Добавления
    @Test
    public void subShouldNotBeAddedNonExistentTask() {
        TaskManager taskManager = Managers.getDefault();
        Assertions.assertThrows(NotExistIdException.class, ()->{
            Task createSub = taskManager.addSubTaskToEpicID(-1,"","");
        });
    }
    @Test
    public void subShouldNotBeAddedToNonEpic() {
        TaskManager taskManager = Managers.getDefault();
        Task createTask= taskManager.addTask("","");
        Assertions.assertThrows(NotEpicException.class, ()->{
            Task createSub = taskManager.addSubTaskToEpicID(createTask.getID(),"","");
        });
    }

    /// Изменение
    @Test
    public void shouldThrowExceptionWhenChangingNameOfTaskWithNonExistentID() {
        TaskManager taskManager = Managers.getDefault();
        Assertions.assertThrows(NotExistIdException.class, ()->{
            taskManager.reNameToIDTask(-1,"");
        });
    }
    @Test
    public void shouldThrowExceptionWhenChangingDescriptionOfTaskWithNonExistentID() {
        TaskManager taskManager = Managers.getDefault();
        Assertions.assertThrows(NotExistIdException.class, ()->{
            taskManager.reDescToIDTask(-1,"");
        });
    }

    @Test
    public void shouldThrowExceptionWhenChangingStatusOfTaskWithNonExistentID() {
        TaskManager taskManager = Managers.getDefault();
        Assertions.assertThrows(NotExistIdException.class, ()->{
            taskManager.reStatus(-1,TaskStatus.DONE);
        });
    }

    /// Удаление по ID
    @Test
    public void deleteByIDShouldRemoveTaskFromCollection() {
        TaskManager taskManager = Managers.getDefault();
        Assertions.assertThrows(NotExistIdException.class, ()->{
            taskManager.deleteIDTask(-1);
        });
    }

    ///Вызов не существующий задачи
    @Test
    public void callingNonExistentTaskShouldThrowException() {
        TaskManager taskManager = Managers.getDefault();
        Assertions.assertThrows(NotExistIdException.class, ()->{
            taskManager.getTask(-1);
        });
    }
    @Test
    public void definingEpicForNonExistentTaskShouldThrowException() {
        TaskManager taskManager = Managers.getDefault();
        Assertions.assertThrows(NotExistIdException.class, ()->{
            taskManager.isEpic(-1);
        });
    }



}
