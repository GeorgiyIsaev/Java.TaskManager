package controller;

import com.controller.taskManager.TaskManager;
import com.controller.Managers;
import com.controller.controlException.NotChangedEpicStatusException;
import com.dateTask.Task;
import com.dateTask.TaskStatus;
import com.dateTask.TaskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class InMemoryTaskManagerTest {


    @Test
    public void newCollectionShouldBeEmpty() {
        TaskManager taskManager = Managers.getDefault();
        int sizeCollectionManager = taskManager.getTasks().size();
        int emptyCollectionSize = 0;
        Assertions.assertEquals(emptyCollectionSize, sizeCollectionManager);
    }


    ///Добавления
    @Test
    public void taskManagerShouldReturnTheAssignedTaskByID() {
        TaskManager taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        Task getTask = taskManager.getTask(createTask.getID());
        Assertions.assertEquals(createTask, getTask);
    }
    @Test
    public void taskEpicShouldReallyTask() {
        TaskManager taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        Assertions.assertEquals(TaskType.TASK.name(), createTask.getTypeTask());
    }

    @Test
    public void taskManagerShouldReturnTheAssignedEpicByID() {
        TaskManager taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task getTask = taskManager.getTask(createEpic.getID());
        Assertions.assertEquals(createEpic, getTask);
    }
    @Test
    public void epicShouldReallyBeEpic() {
        TaskManager taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Assertions.assertEquals(TaskType.EPIC.name(), createEpic.getTypeTask());
    }

    @Test
    public void taskManagerShouldReturnTheAssignedSubByID() {
        TaskManager taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");

        Task getTask = taskManager.getTask(createSub.getID());
        Assertions.assertEquals(createSub, getTask);
    }
    @Test
    public void subShouldReallyBeSub() {
        TaskManager taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        Assertions.assertEquals(TaskType.SUBTASK.name(), createSub.getTypeTask());
    }

    /// Изменения Имени и описания
    @Test
    public void taskShouldPerformNameChange() {
        String newName = "Новое имя";
        TaskManager taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        taskManager.reNameToIDTask(createTask.getID(), newName);
        Assertions.assertEquals(newName, createTask.getName());
    }
    @Test
    public void epicShouldPerformNameChange() {
        String newName = "Новое имя";
        TaskManager taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        taskManager.reNameToIDTask(createEpic.getID(), newName);
        Assertions.assertEquals(newName, createEpic.getName());
    }
    @Test
    public void subShouldPerformNameChange() {
        String newName = "Новое имя";
        TaskManager taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        taskManager.reNameToIDTask(createSub.getID(), newName);
        Assertions.assertEquals(newName, createSub.getName());
    }

    @Test
    public void taskShouldPerformDescriptionChange() {
        String newDescription = "Новое Описание";
        TaskManager taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        taskManager.reDescToIDTask(createTask.getID(), newDescription);
        Assertions.assertEquals(newDescription, createTask.getDescription());
    }
    @Test
    public void epicShouldPerformDescriptionChange() {
        String newDescription = "Новое Описание";
        TaskManager taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        taskManager.reDescToIDTask(createEpic.getID(), newDescription);
        Assertions.assertEquals(newDescription, createEpic.getDescription());
    }
    @Test
    public void subShouldPerformDescriptionChange() {
        String newDescription= "Новое Описание";
        TaskManager taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        taskManager.reDescToIDTask(createSub.getID(), newDescription);
        Assertions.assertEquals(newDescription, createSub.getDescription());
    }

    //Изменение Статуса
    @Test
    public void taskShouldPerformStatusPROGChange() {
        TaskManager taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        taskManager.reStatus(createTask.getID(), TaskStatus.IN_PROGRESS);
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, createTask.getStatus());
    }
    @Test
    public void taskShouldPerformStatusDONEChange() {
        TaskManager taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        taskManager.reStatus(createTask.getID(), TaskStatus.DONE);
        Assertions.assertEquals(TaskStatus.DONE, createTask.getStatus());
    }
    @Test
    public void taskShouldPerformStatusNEWChange() {
        TaskManager taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        taskManager.reStatus(createTask.getID(), TaskStatus.IN_PROGRESS);
        taskManager.reStatus(createTask.getID(), TaskStatus.NEW);
        Assertions.assertEquals(TaskStatus.NEW, createTask.getStatus());
    }
    @Test
    public void taskShouldPerformStatusNEWtoNEWChange() {
        TaskManager taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        taskManager.reStatus(createTask.getID(), TaskStatus.NEW);
        Assertions.assertEquals(TaskStatus.NEW, createTask.getStatus());
    }

    @Test
    public void epicShouldNOTPerformStatusPROGChange() {
        TaskManager taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Assertions.assertThrows(NotChangedEpicStatusException.class, ()->{
            taskManager.reStatus(createEpic.getID(), TaskStatus.IN_PROGRESS);
        });
        Assertions.assertEquals(TaskStatus.NEW, createEpic.getStatus());
    }
    @Test
    public void epicShouldNOTPerformStatusDONEChange() {
        TaskManager taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Assertions.assertThrows(NotChangedEpicStatusException.class, ()->{
            taskManager.reStatus(createEpic.getID(), TaskStatus.DONE);
        });
        Assertions.assertEquals(TaskStatus.NEW, createEpic.getStatus());
    }
    @Test
    public void epicShouldNOTPerformStatusNEWChange() {

        TaskManager taskManager = Managers.getDefault();
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
        TaskManager taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        taskManager.reStatus(createSub.getID(), TaskStatus.IN_PROGRESS);
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, createSub.getStatus());
    }
    @Test
    public void subShouldPerformStatusDONEChange() {
        TaskManager taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        taskManager.reStatus(createSub.getID(), TaskStatus.DONE);
        Assertions.assertEquals(TaskStatus.DONE, createSub.getStatus());
    }
    @Test
    public void subShouldPerformStatusNEWChange() {
        TaskManager taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        taskManager.reStatus(createSub.getID(), TaskStatus.IN_PROGRESS);
        taskManager.reStatus(createSub.getID(), TaskStatus.NEW);
        Assertions.assertEquals(TaskStatus.NEW, createSub.getStatus());
    }

    /// Удаление по ID
    @Test
    public void deleteByIDShouldRemoveTaskFromCollection() {
        TaskManager taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        Assertions.assertNotNull(taskManager.getTask(createTask.getID()));

        taskManager.deleteIDTask(createTask.getID());
        int emptyCollectionSize = 0;
        Assertions.assertEquals(emptyCollectionSize, taskManager.getTasks().size());
    }
    @Test
    public void deleteByIDShouldRemoveEpicFromCollection() {
        TaskManager taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Assertions.assertNotNull(taskManager.getTask(createEpic.getID()));

        taskManager.deleteIDTask(createEpic.getID());
        int emptyCollectionSize = 0;
        Assertions.assertEquals(emptyCollectionSize, taskManager.getTasks().size());
    }
    @Test
    public void deleteByIDShouldRemoveSubFromCollection() {
        TaskManager taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        Assertions.assertNotNull(taskManager.getTask(createSub.getID()));

        taskManager.deleteIDTask(createSub.getID());
        int epicIntoCollectionSize = 1;
        Assertions.assertEquals(epicIntoCollectionSize, taskManager.getTasks().size());
    }
    @Test
    public void deleteByIDShouldRemoveEpicAndRelatedSubFromCollection() {
        TaskManager taskManager = Managers.getDefault();
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
        TaskManager taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("Epic","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"Sub","");
        Task createTask = taskManager.addTask("Task","");
        Assertions.assertNotNull(taskManager.getTask(createSub.getID()));
        Assertions.assertNotNull(taskManager.getTask(createTask.getID()));

        taskManager.deleteALL();
        int emptyCollectionSize = 0;
        Assertions.assertEquals(emptyCollectionSize, taskManager.getTasks().size());
    }

    /// Замена Коллекции
    @Test
    public void replacementOfTaskCollectionToAnotherShouldBeSuccessful() {
        TaskManager taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("Epic","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"Sub","");
        Task createTask = taskManager.addTask("Task","");
        Assertions.assertNotNull(taskManager.getTask(createSub.getID()));
        Assertions.assertNotNull(taskManager.getTask(createTask.getID()));

        Map<Integer, Task> tasks = new HashMap<>();
        Task taskToReplace = new Task("","");
        tasks.put(taskToReplace.getID(),taskToReplace);
        taskManager.replacementTasks(tasks);
        Assertions.assertEquals(taskToReplace, taskManager.getTask(taskToReplace.getID()));
    }

    @Test
    public void replacementOfTaskCollectionToEmptyShouldBeSuccessful() {
        TaskManager taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("Epic","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"Sub","");
        Task createTask = taskManager.addTask("Task","");
        Assertions.assertNotNull(taskManager.getTask(createSub.getID()));
        Assertions.assertNotNull(taskManager.getTask(createTask.getID()));

        Map<Integer, Task> tasks = new HashMap<>();
        taskManager.replacementTasks(tasks);
        int emptyCollectionSize = 0;
        Assertions.assertEquals(emptyCollectionSize, taskManager.getTasks().size());
    }

}
