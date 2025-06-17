package controller;

import com.controller.IManagerTask;
import com.controller.Managers;
import com.dateTask.Task;
import com.dateTask.TypeTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManagerTaskInMemoryTest {


    @Test
    public void TheNewCollectionShouldBeEmpty() {
        IManagerTask taskManager = Managers.getDefault();
        int sizeCollectionManager = taskManager.getTasks().size();
        int emptyCollectionSize = 0;
        Assertions.assertEquals(emptyCollectionSize, sizeCollectionManager);
    }

    @Test
    public void TaskManagerShouldReturnTheAssignedTaskByID() {
        IManagerTask taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        Task getTask = taskManager.getTask(createTask.getID());
        Assertions.assertEquals(createTask, getTask);
    }
    @Test
    public void TaskEpicShouldReallyTask() {
        IManagerTask taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        Assertions.assertEquals(TypeTask.TASK_NAME, createTask.getTypeTask());
    }

    @Test
    public void TaskManagerShouldReturnTheAssignedEpicByID() {
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task getTask = taskManager.getTask(createEpic.getID());
        Assertions.assertEquals(createEpic, getTask);
    }
    @Test
    public void EpicShouldReallyBeEpic() {
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Assertions.assertEquals(TypeTask.EPIC_NAME, createEpic.getTypeTask());
    }

    @Test
    public void TaskManagerShouldReturnTheAssignedSubByID() {
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");

        Task getTask = taskManager.getTask(createSub.getID());
        Assertions.assertEquals(createSub, getTask);
    }
    @Test
    public void SubShouldReallyBeSub() {
        IManagerTask taskManager = Managers.getDefault();
        Task createEpic = taskManager.addEpic("","");
        Task createSub = taskManager.addSubTaskToEpicID(createEpic.getID(),"","");
        Assertions.assertEquals(TypeTask.SUB_NAME, createSub.getTypeTask());
    }

}
