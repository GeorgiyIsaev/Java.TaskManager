package controller.fileBacked;

import com.controller.Managers;
import com.controller.controlException.NotChangedEpicStatusException;
import com.controller.files.CreatePath;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;
import com.dateTask.TaskStatus;
import com.dateTask.TaskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileBackedTasksManagerTest {


    public TaskManager getClearManager(){
        Path filePath = CreatePath.of().generateToPakResources("csv.csv");
        TaskManager taskManager = Managers.getFileBacked(filePath);
        taskManager.deleteALL();
        return taskManager;
    }

    public TaskManager getManager(){
        Path filePath = CreatePath.of().generateToPakResources("csv.csv");
        return Managers.getFileBacked(filePath);
    }


    @Test
    public void newCollectionShouldBeEmpty() {
        TaskManager taskManager = getClearManager();
        int sizeCollectionManager = taskManager.getTasks().size();
        int emptyCollectionSize = 0;
        Assertions.assertEquals(emptyCollectionSize, sizeCollectionManager);

        TaskManager taskManagerInFile = getManager();
        int sizeCollectionManagerInFile = taskManagerInFile.getTasks().size();
        Assertions.assertEquals(emptyCollectionSize, sizeCollectionManagerInFile);
    }


    ///Добавления
    @Test
    public void taskManagerShouldReturnTheAssignedTaskByID() {
        TaskManager taskManager = getClearManager();
        Task createTask = taskManager.addTask("","");

        TaskManager taskManagerInFile = getManager();
        Task getTaskInFile = taskManagerInFile.getTask(createTask.getID());
        Assertions.assertEquals(createTask.getID(), getTaskInFile.getID());
    }
    @Test
    public void taskEpicShouldReallyTask() {
        TaskManager taskManager = getClearManager();
        Task createTask = taskManager.addTask("","");

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createTask.getID());
        Assertions.assertEquals(TaskType.TASK.name(), taskInFile.getTypeTask());
    }

    @Test
    public void taskManagerShouldReturnTheAssignedEpicByID() {
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("","");

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createEpic.getID());
        Assertions.assertEquals(createEpic.getID(), taskInFile.getID());
    }
    @Test
    public void epicShouldReallyBeEpic() {
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("","");

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createEpic.getID());
        Assertions.assertEquals(TaskType.EPIC.name(), taskInFile.getTypeTask());
    }

    @Test
    public void taskManagerShouldReturnTheAssignedSubByID() {
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createSub.getID());
        Assertions.assertEquals(createSub.getID(), taskInFile.getID());
    }
    @Test
    public void subShouldReallyBeSub() {
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createSub.getID());
        Assertions.assertEquals(TaskType.SUBTASK.name(), taskInFile.getTypeTask());
    }

    /// Изменения Имени и описания
    @Test
    public void taskShouldPerformNameChange() {
        String newName = "Новое имя";
        TaskManager taskManager = getClearManager();
        Task createTask = taskManager.addTask("","");
        taskManager.reNameToIDTask(createTask.getID(), newName);

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createTask.getID());
        Assertions.assertEquals(newName, taskInFile.getName());
    }
    @Test
    public void epicShouldPerformNameChange() {
        String newName = "Новое имя";
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("","");
        taskManager.reNameToIDTask(createEpic.getID(), newName);

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createEpic.getID());
        Assertions.assertEquals(newName, taskInFile.getName());
    }
    @Test
    public void subShouldPerformNameChange() {
        String newName = "Новое имя";
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        taskManager.reNameToIDTask(createSub.getID(), newName);

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createSub.getID());
        Assertions.assertEquals(newName, taskInFile.getName());
    }

    @Test
    public void taskShouldPerformDescriptionChange() {
        String newDescription = "Новое Описание";
        TaskManager taskManager = getClearManager();
        Task createTask = taskManager.addTask("","");
        taskManager.reDescToIDTask(createTask.getID(), newDescription);

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createTask.getID());
        Assertions.assertEquals(newDescription, taskInFile.getDescription());
    }
    @Test
    public void epicShouldPerformDescriptionChange() {
        String newDescription = "Новое Описание";
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("","");
        taskManager.reDescToIDTask(createEpic.getID(), newDescription);

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createEpic.getID());
        Assertions.assertEquals(newDescription, taskInFile.getDescription());
    }
    @Test
    public void subShouldPerformDescriptionChange() {
        String newDescription= "Новое Описание";
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        taskManager.reDescToIDTask(createSub.getID(), newDescription);

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createSub.getID());
        Assertions.assertEquals(newDescription, taskInFile.getDescription());
    }

    //Изменение Статуса
    @Test
    public void taskShouldPerformStatusPROGChange() {
        TaskManager taskManager = getClearManager();
        Task createTask = taskManager.addTask("","");
        taskManager.reStatus(createTask.getID(), TaskStatus.IN_PROGRESS);

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createTask.getID());
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, taskInFile.getStatus());
    }
    @Test
    public void taskShouldPerformStatusDONEChange() {
        TaskManager taskManager = getClearManager();
        Task createTask = taskManager.addTask("","");
        taskManager.reStatus(createTask.getID(), TaskStatus.DONE);

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createTask.getID());
        Assertions.assertEquals(TaskStatus.DONE, taskInFile.getStatus());
    }
    @Test
    public void taskShouldPerformStatusNEWChange() {
        TaskManager taskManager = getClearManager();
        Task createTask = taskManager.addTask("","");
        taskManager.reStatus(createTask.getID(), TaskStatus.IN_PROGRESS);
        taskManager.reStatus(createTask.getID(), TaskStatus.NEW);

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createTask.getID());
        Assertions.assertEquals(TaskStatus.NEW, taskInFile.getStatus());
    }
    @Test
    public void taskShouldPerformStatusNEWtoNEWChange() {
        TaskManager taskManager = getClearManager();
        Task createTask = taskManager.addTask("","");
        taskManager.reStatus(createTask.getID(), TaskStatus.NEW);

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createTask.getID());
        Assertions.assertEquals(TaskStatus.NEW, taskInFile.getStatus());
    }

    @Test
    public void epicShouldNOTPerformStatusPROGChange() {
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("","");

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createEpic.getID());
        Assertions.assertThrows(NotChangedEpicStatusException.class, ()->{
            taskManagerInFile.reStatus(taskInFile.getID(), TaskStatus.IN_PROGRESS);
        });
        Assertions.assertEquals(TaskStatus.NEW, taskInFile.getStatus());
    }
    @Test
    public void epicShouldNOTPerformStatusDONEChange() {
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("","");

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createEpic.getID());
        Assertions.assertThrows(NotChangedEpicStatusException.class, ()->{
            taskManagerInFile.reStatus(taskInFile.getID(), TaskStatus.DONE);
        });
        Assertions.assertEquals(TaskStatus.NEW, taskInFile.getStatus());
    }
    @Test
    public void epicShouldNOTPerformStatusNEWChange() {

        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("","");

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createEpic.getID());
        Assertions.assertThrows(NotChangedEpicStatusException.class, ()->{
            taskManagerInFile.reStatus(taskInFile.getID(), TaskStatus.IN_PROGRESS);
        });
        Assertions.assertThrows(NotChangedEpicStatusException.class, ()->{
            taskManagerInFile.reStatus(taskInFile.getID(), TaskStatus.NEW);
        });
        Assertions.assertEquals(TaskStatus.NEW, taskInFile.getStatus());
    }

    @Test
    public void subShouldPerformStatusPROGChange() {
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        taskManager.reStatus(createSub.getID(), TaskStatus.IN_PROGRESS);

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createSub.getID());
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, taskInFile.getStatus());
    }
    @Test
    public void subShouldPerformStatusDONEChange() {
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        taskManager.reStatus(createSub.getID(), TaskStatus.DONE);

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createSub.getID());
        Assertions.assertEquals(TaskStatus.DONE, taskInFile.getStatus());
    }
    @Test
    public void subShouldPerformStatusNEWChange() {
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        taskManager.reStatus(createSub.getID(), TaskStatus.IN_PROGRESS);
        taskManager.reStatus(createSub.getID(), TaskStatus.NEW);

        TaskManager taskManagerInFile = getManager();
        Task taskInFile = taskManagerInFile.getTask(createSub.getID());
        Assertions.assertEquals(TaskStatus.NEW, taskInFile.getStatus());
    }

    /// Удаление по ID
    @Test
    public void deleteByIDShouldRemoveTaskFromCollection() {
        TaskManager taskManager = getClearManager();
        Task createTask = taskManager.addTask("","");
        Assertions.assertNotNull(taskManager.getTask(createTask.getID()));
        taskManager.deleteIDTask(createTask.getID());

        int emptyCollectionSize = 0;
        TaskManager taskManagerInFile = getManager();
        Assertions.assertEquals(emptyCollectionSize, taskManagerInFile.getTasks().size());
    }
    @Test
    public void deleteByIDShouldRemoveEpicFromCollection() {
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("","");
        Assertions.assertNotNull(taskManager.getTask(createEpic.getID()));

        taskManager.deleteIDTask(createEpic.getID());
        int emptyCollectionSize = 0;

        TaskManager taskManagerInFile = getManager();
        Assertions.assertEquals(emptyCollectionSize, taskManagerInFile.getTasks().size());
    }
    @Test
    public void deleteByIDShouldRemoveSubFromCollection() {
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        Assertions.assertNotNull(taskManager.getTask(createSub.getID()));

        taskManager.deleteIDTask(createSub.getID());
        int epicIntoCollectionSize = 1;

        TaskManager taskManagerInFile = getManager();
        Assertions.assertEquals(epicIntoCollectionSize, taskManagerInFile.getTasks().size());
    }
    @Test
    public void deleteByIDShouldRemoveEpicAndRelatedSubFromCollection() {
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        Assertions.assertNotNull(taskManager.getTask(createSub.getID()));

        taskManager.deleteIDTask(createEpic.getID());
        int emptyCollectionSize = 0;

        TaskManager taskManagerInFile = getManager();
        Assertions.assertEquals(emptyCollectionSize, taskManagerInFile.getTasks().size());
    }

    ///Очистка всей коллекции
    @Test
    public void deleteALlDShouldRemoveAllFromCollection() {
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("Epic","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"Sub","");
        Task createTask = taskManager.addTask("Task","");
        Assertions.assertNotNull(taskManager.getTask(createSub.getID()));
        Assertions.assertNotNull(taskManager.getTask(createTask.getID()));

        taskManager.deleteALL();
        int emptyCollectionSize = 0;
        TaskManager taskManagerInFile = getManager();
        Assertions.assertEquals(emptyCollectionSize, taskManagerInFile.getTasks().size());
    }

    /// Замена Коллекции
    @Test
    public void replacementOfTaskCollectionToAnotherShouldBeSuccessful() {
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("Epic","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"Sub","");
        Task createTask = taskManager.addTask("Task","");
        Assertions.assertNotNull(taskManager.getTask(createSub.getID()));
        Assertions.assertNotNull(taskManager.getTask(createTask.getID()));

        Map<Integer, Task> tasks = new HashMap<>();
        Task taskToReplace = new Task("","");
        tasks.put(taskToReplace.getID(),taskToReplace);
        taskManager.replacementTasks(tasks);

        TaskManager taskManagerInFile = getManager();
        Task taskToReplaceInFile = taskManagerInFile.getTask(taskToReplace.getID());
        Assertions.assertEquals(taskToReplace.getID(), taskToReplaceInFile.getID());
    }

    @Test
    public void replacementOfTaskCollectionToEmptyShouldBeSuccessful() {
        TaskManager taskManager = getClearManager();
        Task createEpic = taskManager.addEpic("Epic","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"Sub","");
        Task createTask = taskManager.addTask("Task","");
        Assertions.assertNotNull(taskManager.getTask(createSub.getID()));
        Assertions.assertNotNull(taskManager.getTask(createTask.getID()));

        Map<Integer, Task> tasks = new HashMap<>();
        taskManager.replacementTasks(tasks);
        int emptyCollectionSize = 0;

        TaskManager taskManagerInFile = getManager();
        Assertions.assertEquals(emptyCollectionSize, taskManagerInFile.getTasks().size());
    }
}
