package controller;

import com.controller.IManagerTask;
import com.controller.Managers;
import com.controller.controlException.NotChangedEpicStatusException;
import com.dateTask.Task;
import com.dateTask.TaskStatus;
import com.dateTask.TypeTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class ManagerTaskInMemoryTest {


    @Test
    public void newCollectionShouldBeEmpty() {
        IManagerTask taskManager = Managers.getDefault();
        int sizeCollectionManager = taskManager.getTasks().size();
        int emptyCollectionSize = 0;
        Assertions.assertEquals(emptyCollectionSize, sizeCollectionManager);
    }


    ///Добавления
    @Test
    public void taskManagerShouldReturnTheAssignedTaskByID() {
        IManagerTask taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        Task getTask = taskManager.getTask(createTask.getID());
        Assertions.assertEquals(createTask, getTask);
    }
    @Test
    public void taskEpicShouldReallyTask() {
        IManagerTask taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        Assertions.assertEquals(TypeTask.TASK_NAME, createTask.getTypeTask());
    }

    @Test
    public void taskManagerShouldReturnTheAssignedEpicByID() {
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task getTask = taskManager.getTask(createEpic.getID());
        Assertions.assertEquals(createEpic, getTask);
    }
    @Test
    public void epicShouldReallyBeEpic() {
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Assertions.assertEquals(TypeTask.EPIC_NAME, createEpic.getTypeTask());
    }

    @Test
    public void taskManagerShouldReturnTheAssignedSubByID() {
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");

        Task getTask = taskManager.getTask(createSub.getID());
        Assertions.assertEquals(createSub, getTask);
    }
    @Test
    public void subShouldReallyBeSub() {
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        Assertions.assertEquals(TypeTask.SUB_NAME, createSub.getTypeTask());
    }

    /// Изменения Имени и описания
    @Test
    public void taskShouldPerformNameChange() {
        String newName = "Новое имя";
        IManagerTask taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        taskManager.reNameToIDTask(createTask.getID(), newName);
        Assertions.assertEquals(newName, createTask.getName());
    }
    @Test
    public void epicShouldPerformNameChange() {
        String newName = "Новое имя";
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        taskManager.reNameToIDTask(createEpic.getID(), newName);
        Assertions.assertEquals(newName, createEpic.getName());
    }
    @Test
    public void subShouldPerformNameChange() {
        String newName = "Новое имя";
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        taskManager.reNameToIDTask(createSub.getID(), newName);
        Assertions.assertEquals(newName, createSub.getName());
    }

    @Test
    public void taskShouldPerformDescriptionChange() {
        String newDescription = "Новое Описание";
        IManagerTask taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        taskManager.reDescToIDTask(createTask.getID(), newDescription);
        Assertions.assertEquals(newDescription, createTask.getDescription());
    }
    @Test
    public void epicShouldPerformDescriptionChange() {
        String newDescription = "Новое Описание";
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        taskManager.reDescToIDTask(createEpic.getID(), newDescription);
        Assertions.assertEquals(newDescription, createEpic.getDescription());
    }
    @Test
    public void subShouldPerformDescriptionChange() {
        String newDescription= "Новое Описание";
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        taskManager.reDescToIDTask(createSub.getID(), newDescription);
        Assertions.assertEquals(newDescription, createSub.getDescription());
    }

    //Изменение Статуса
    @Test
    public void taskShouldPerformStatusPROGChange() {
        IManagerTask taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        taskManager.reStatus(createTask.getID(), TaskStatus.IN_PROGRESS);
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, createTask.getStatus());
    }
    @Test
    public void taskShouldPerformStatusDONEChange() {
        IManagerTask taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        taskManager.reStatus(createTask.getID(), TaskStatus.DONE);
        Assertions.assertEquals(TaskStatus.DONE, createTask.getStatus());
    }
    @Test
    public void taskShouldPerformStatusNEWChange() {
        IManagerTask taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        taskManager.reStatus(createTask.getID(), TaskStatus.IN_PROGRESS);
        taskManager.reStatus(createTask.getID(), TaskStatus.NEW);
        Assertions.assertEquals(TaskStatus.NEW, createTask.getStatus());
    }
    @Test
    public void taskShouldPerformStatusNEWtoNEWChange() {
        IManagerTask taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        taskManager.reStatus(createTask.getID(), TaskStatus.NEW);
        Assertions.assertEquals(TaskStatus.NEW, createTask.getStatus());
    }

    @Test
    public void epicShouldNOTPerformStatusPROGChange() {
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Assertions.assertThrows(NotChangedEpicStatusException.class, ()->{
            taskManager.reStatus(createEpic.getID(), TaskStatus.IN_PROGRESS);
        });
        Assertions.assertEquals(TaskStatus.NEW, createEpic.getStatus());
    }
    @Test
    public void epicShouldNOTPerformStatusDONEChange() {
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Assertions.assertThrows(NotChangedEpicStatusException.class, ()->{
            taskManager.reStatus(createEpic.getID(), TaskStatus.DONE);
        });
        Assertions.assertEquals(TaskStatus.NEW, createEpic.getStatus());
    }
    @Test
    public void epicShouldNOTPerformStatusNEWChange() {

        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Assertions.assertThrows(NotChangedEpicStatusException.class, ()->{
            taskManager.reStatus(createEpic.getID(), TaskStatus.IN_PROGRESS);
        });
        Assertions.assertThrows(NotChangedEpicStatusException.class, ()->{
            taskManager.reStatus(createEpic.getID(), TaskStatus.NEW);
        });
        Assertions.assertEquals(TaskStatus.NEW, createEpic.getStatus());
    }

    @Test
    public void subShouldPerformStatusPROGChange() {
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        taskManager.reStatus(createSub.getID(), TaskStatus.IN_PROGRESS);
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, createSub.getStatus());
    }
    @Test
    public void subShouldPerformStatusDONEChange() {
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        taskManager.reStatus(createSub.getID(), TaskStatus.DONE);
        Assertions.assertEquals(TaskStatus.DONE, createSub.getStatus());
    }
    @Test
    public void subShouldPerformStatusNEWChange() {
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        taskManager.reStatus(createSub.getID(), TaskStatus.IN_PROGRESS);
        taskManager.reStatus(createSub.getID(), TaskStatus.NEW);
        Assertions.assertEquals(TaskStatus.NEW, createSub.getStatus());
    }

    /// Удаление по ID
    @Test
    public void deleteByIDShouldRemoveTaskFromCollection() {
        IManagerTask taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        Assertions.assertNotNull(taskManager.getTask(createTask.getID()));

        taskManager.deleteIDTask(createTask.getID());
        int emptyCollectionSize = 0;
        Assertions.assertEquals(emptyCollectionSize, taskManager.getTasks().size());
    }
    @Test
    public void deleteByIDShouldRemoveEpicFromCollection() {
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Assertions.assertNotNull(taskManager.getTask(createEpic.getID()));

        taskManager.deleteIDTask(createEpic.getID());
        int emptyCollectionSize = 0;
        Assertions.assertEquals(emptyCollectionSize, taskManager.getTasks().size());
    }
    @Test
    public void deleteByIDShouldRemoveSubFromCollection() {
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        Assertions.assertNotNull(taskManager.getTask(createSub.getID()));

        taskManager.deleteIDTask(createSub.getID());
        int epicIntoCollectionSize = 1;
        Assertions.assertEquals(epicIntoCollectionSize, taskManager.getTasks().size());
    }
    @Test
    public void deleteByIDShouldRemoveEpicAndRelatedSubFromCollection() {
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        Assertions.assertNotNull(taskManager.getTask(createSub.getID()));

        taskManager.deleteIDTask(createEpic.getID());
        int emptyCollectionSize = 0;
        Assertions.assertEquals(emptyCollectionSize, taskManager.getTasks().size());
    }

    ///Очистка всей коллекции
    @Test
    public void deleteALlDShouldRemoveAllFromCollection() {
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("Epic","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"Sub","");
        Task createTask = taskManager.addTask("Task","");
        Assertions.assertNotNull(taskManager.getTask(createSub.getID()));
        Assertions.assertNotNull(taskManager.getTask(createTask.getID()));

        taskManager.deleteALL();
        int emptyCollectionSize = 0;
        Assertions.assertEquals(emptyCollectionSize, taskManager.getTasks().size());
    }

}
