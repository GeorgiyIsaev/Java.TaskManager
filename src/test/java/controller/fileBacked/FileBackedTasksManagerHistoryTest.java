package controller.fileBacked;

import com.controller.Managers;
import com.controller.files.CreatePath;
import com.controller.taskManager.TaskManager;
import com.dateTask.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

public class FileBackedTasksManagerHistoryTest {

    public TaskManager getClearManager(){
        Path filePath = CreatePath.of().generatePathToPakDate("csvTest.csv");
        TaskManager taskManager = Managers.getFileBacked(filePath);
        taskManager.deleteALL();
        return taskManager;
    }

    public TaskManager getManager(){
        Path filePath = CreatePath.of().generatePathToPakDate("csvTest.csv");
        return Managers.getFileBacked(filePath);
    }

    @Test
    public void whenCreatingManagerTheHistoryShouldBeEmpty(){
        TaskManager taskManager = getClearManager();
        int emptyContainer = 0;

        TaskManager taskManagerInFile = getManager();
        int sizeHistoryContainer =  taskManagerInFile.getHistory().size();
        Assertions.assertEquals(emptyContainer,sizeHistoryContainer);
    }


    @Test
    public void whenAddingManagerTheHistoryShouldBeEmpty(){
        TaskManager taskManager = getClearManager();
        Task createTask = taskManager.addTask("","");
        int emptyContainer = 0;

        TaskManager taskManagerInFile = getManager();
        int sizeHistoryContainer =  taskManagerInFile.getHistory().size();
        Assertions.assertEquals(emptyContainer,sizeHistoryContainer);
    }

    @Test
    public void historyShouldBeWrittenWhenTaskIsCalled(){
        TaskManager taskManager = getClearManager();
        Task createTask = taskManager.addTask("","");
        taskManager.getTask(createTask.getID());


        TaskManager taskManagerInFile = getManager();
        int sizeHistoryContainer =  taskManagerInFile.getHistory().size();
        int oneElement = 1;
        Assertions.assertEquals(oneElement,sizeHistoryContainer);

        final int FIRST_ELEMENT = 0;
        Task taskFromHistory = taskManagerInFile.getHistory().get(FIRST_ELEMENT);
        Assertions.assertEquals(createTask.getID(),taskFromHistory.getID());
    }


    @Test
    public void whenCallingSameTasksTheHistoryShouldBeWrittenOnlyOnce(){
        TaskManager taskManager = getClearManager();
        Task createTask = taskManager.addTask("","");
        taskManager.getTask(createTask.getID());
        taskManager.getTask(createTask.getID());

        int sizeHistoryContainer =  taskManager.getHistory().size();
        int oneElement = 1;
        Assertions.assertEquals(oneElement,sizeHistoryContainer);

        TaskManager taskManagerInFile = getManager();
        Task taskFromHistory = taskManagerInFile.getHistory().get(0);
        Assertions.assertEquals(createTask.getID(),taskFromHistory.getID());
    }


    @Test
    public void taskShouldBeRemovedFromHistoryWhenTaskIsDeleted(){
        TaskManager taskManager = getClearManager();
        Task createTask = taskManager.addTask("","");
        taskManager.getTask(createTask.getID());
        Task taskFromHistory = taskManager.getHistory().get(0);
        Assertions.assertEquals(createTask,taskFromHistory);

        taskManager.deleteIDTask(createTask.getID());
        int emptyContainer = 0;

        TaskManager taskManagerInFile = getManager();
        int sizeHistoryContainer =  taskManagerInFile.getHistory().size();
        Assertions.assertEquals(emptyContainer,sizeHistoryContainer);
    }

    @Test
    public void onlyLastTenCallsShouldBeRecordedInHistory(){
        TaskManager taskManager = getClearManager();

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

        TaskManager taskManagerInFile = getManager();
        Assertions.assertFalse(isInContainer(createOneTask, taskManagerInFile.getHistory()));
        Assertions.assertTrue(isInContainer(createLastTask, taskManagerInFile.getHistory()));
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
