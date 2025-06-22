package controller;

import com.controller.taskManager.TaskManager;
import com.controller.Managers;
import com.dateTask.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class InMemoryHistoryManagerTest {

    @Test
    public void whenCreatingManagerTheHistoryShouldBeEmpty(){
        TaskManager taskManager = Managers.getDefault();
        int sizeHistoryContainer =  taskManager.getHistory().size();
        int emptyContainer = 0;
        Assertions.assertEquals(emptyContainer,sizeHistoryContainer);
    }


    @Test
    public void whenAddingManagerTheHistoryShouldBeEmpty(){
        TaskManager taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        int sizeHistoryContainer =  taskManager.getHistory().size();
        int emptyContainer = 0;
        Assertions.assertEquals(emptyContainer,sizeHistoryContainer);
    }

    @Test
    public void historyShouldBeWrittenWhenTaskIsCalled(){
        TaskManager taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        taskManager.getTask(createTask.getID());

        int sizeHistoryContainer =  taskManager.getHistory().size();
        int oneElement = 1;
        Assertions.assertEquals(oneElement,sizeHistoryContainer);

        Task taskFromHistory = taskManager.getHistory().get(0);
        Assertions.assertEquals(createTask,taskFromHistory);
    }


    @Test
    public void whenCallingSameTasksTheHistoryShouldBeWrittenOnlyOnce(){
        TaskManager taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        taskManager.getTask(createTask.getID());
        taskManager.getTask(createTask.getID());

        int sizeHistoryContainer =  taskManager.getHistory().size();
        int oneElement = 1;
        Assertions.assertEquals(oneElement,sizeHistoryContainer);

        Task taskFromHistory = taskManager.getHistory().get(0);
        Assertions.assertEquals(createTask,taskFromHistory);
    }


    @Test
    public void taskShouldBeRemovedFromHistoryWhenTaskIsDeleted(){
        TaskManager taskManager = Managers.getDefault();
        Task createTask = taskManager.addTask("","");
        taskManager.getTask(createTask.getID());
        Task taskFromHistory = taskManager.getHistory().get(0);
        Assertions.assertEquals(createTask,taskFromHistory);

        taskManager.deleteIDTask(createTask.getID());
        int sizeHistoryContainer =  taskManager.getHistory().size();
        int emptyContainer = 0;
        Assertions.assertEquals(emptyContainer,sizeHistoryContainer);
    }

    @Test
    public void onlyLastTenCallsShouldBeRecordedInHistory(){
        TaskManager taskManager = Managers.getDefault();

        Task createOneTask = taskManager.addTask("","");
        taskManager.getTask(createOneTask.getID());
        for(int i= 0 ; i<15; i++){
            Task tempTask = taskManager.addTask("","");
            taskManager.getTask(tempTask.getID());
        }
        Task createLastTask = taskManager.addTask("","");
        taskManager.getTask(createLastTask.getID());

        int sizeHistoryContainer =  taskManager.getHistory().size();
        int maxSizeContainer = 10;
        Assertions.assertEquals(maxSizeContainer,sizeHistoryContainer);

        Assertions.assertFalse(isInContainer(createOneTask, taskManager.getHistory()));
        Assertions.assertTrue(isInContainer(createLastTask, taskManager.getHistory()));
    }

    public boolean isInContainer(Task findTask, List<Task> tasks){
        for (Task task : tasks) {
            if(task.getID() == findTask.getID()){
                return true;
            }
        }
        return false;
    }
}
