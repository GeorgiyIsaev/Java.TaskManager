package controller;

import com.controller.IManagerTask;
import com.controller.Managers;
import com.controller.controlException.NotChangedEpicStatusException;
import com.controller.controlException.NotEpicException;
import com.controller.controlException.NotExistIdException;
import com.dateTask.Task;
import com.dateTask.TaskStatus;
import com.dateTask.TypeTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManagerTaskInMemoryExceptionTest {

    ///Добавления
    @Test
    public void subShouldNotBeAddedNonExistentTask() {
        IManagerTask taskManager = Managers.getDefault();
        Assertions.assertThrows(NotExistIdException.class, ()->{
            Task createSub = taskManager.addSubTaskToEpicID(-1,"","");
        });
    }
    @Test
    public void subShouldNotBeAddedToNonEpic() {
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addTask("","");
        Assertions.assertThrows(NotEpicException.class, ()->{
            Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        });
    }

    /// Изменение
    @Test
    public void shouldThrowExceptionWhenChangingNameOfTaskWithNonExistentID() {
        IManagerTask taskManager = Managers.getDefault();
        Assertions.assertThrows(NotExistIdException.class, ()->{
            taskManager.reNameToIDTask(-1,"");
        });
    }
    @Test
    public void shouldThrowExceptionWhenChangingDescriptionOfTaskWithNonExistentID() {
        IManagerTask taskManager = Managers.getDefault();
        Assertions.assertThrows(NotExistIdException.class, ()->{
            taskManager.reDescToIDTask(-1,"");
        });
    }

    @Test
    public void shouldThrowExceptionWhenChangingStatusOfTaskWithNonExistentID() {
        IManagerTask taskManager = Managers.getDefault();
        Assertions.assertThrows(NotExistIdException.class, ()->{
            taskManager.reStatus(-1,TaskStatus.DONE);
        });
    }

    /// Удаление по ID
    @Test
    public void deleteByIDShouldRemoveTaskFromCollection() {
        IManagerTask taskManager = Managers.getDefault();
        Assertions.assertThrows(NotExistIdException.class, ()->{
            taskManager.deleteIDTask(-1);
        });
    }

    ///Вызов не существующий задачи
    @Test
    public void callingNonExistentTaskShouldThrowException() {
        IManagerTask taskManager = Managers.getDefault();
        Assertions.assertThrows(NotExistIdException.class, ()->{
            taskManager.getTask(-1);
        });
    }
    @Test
    public void definingEpicForNonExistentTaskShouldThrowException() {
        IManagerTask taskManager = Managers.getDefault();
        Assertions.assertThrows(NotExistIdException.class, ()->{
            taskManager.isEpic(-1);
        });
    }


}
