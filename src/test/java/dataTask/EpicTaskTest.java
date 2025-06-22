package dataTask;

import com.dateTask.EpicTask;
import com.dateTask.Task;
import com.dateTask.TaskStatus;
import com.dateTask.TaskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EpicTaskTest {



    @Test
    public void CheckThatEpicReturnsTheCorrectType(){
        Task task = new EpicTask("", "");
        String typeTasK = task.getTypeTask();
        String typeTasKShouldBy = TaskType.EPIC.name();
        Assertions.assertEquals(typeTasKShouldBy, typeTasK);
    }

    @Test
    public void TheIDOfNewEpicMustBeOneMoreThanPreviousOne(){
        Task task1 = new EpicTask("", "");
        Task task2 = new EpicTask("", "");
        Assertions.assertEquals(task1.getID() +1 , task2.getID());
    }

    @Test
    public void  TheReturnedEpicNameShouldMatchTheOnePassedToConstructor(){
        String nameTask = "Имя тестовой задачи";
        Task task = new EpicTask(nameTask, "");
        Assertions.assertEquals(nameTask, task.getName());
    }
    @Test
    public void  TheReturnedEpicDescriptionShouldMatchTheOnePassedToConstructor(){
        String description = "Описание тестовой задачи";
        Task task = new EpicTask("", description);
        Assertions.assertEquals(description, task.getDescription());
    }

    @Test

    public void EpicShouldPointToEmptyConnections(){
        Task task = new EpicTask("", "");
        String infoConnections = "[]"; // список Sub пуст
        Assertions.assertEquals(infoConnections, task.getLinkStr(), task.getLinkStr());
    }


    @Test
    public void EpicStatusShouldBeSetToNewWhenCreated(){
        Task task = new EpicTask("", "");
        Assertions.assertEquals(TaskStatus.NEW, task.getStatus());
    }


    @Test
    public void EpicShouldCNOTChangeItsStatusToDONE(){
        Task task = new EpicTask("", "");
        TaskStatus statusOld = task.getStatus();
        task.setStatus(TaskStatus.DONE);
        Assertions.assertEquals(statusOld, task.getStatus());
    }
    @Test
    public void EpicShouldNOTChangeItsStatusToPROGRESS(){
        Task task = new EpicTask("", "");
        TaskStatus statusOld = task.getStatus();
        task.setStatus(TaskStatus.IN_PROGRESS);
        Assertions.assertEquals(statusOld, task.getStatus());
    }


    @Test
    public void EpicShouldChangeTheName(){
        Task task = new EpicTask("", "");
        String newName = "Новое имя задачи";
        task.setName(newName);
        Assertions.assertEquals(newName, task.getName());
    }

    @Test
    public void EpicShouldChangeTheDescription(){
        Task task = new EpicTask("", "");
        String newDescription = "Новое описание задачи";
        task.setDescription(newDescription);
        Assertions.assertEquals(newDescription, task.getDescription());
    }

    @Test
    public void TaskShouldReturnStringDescription(){
        String name = "Имя";
        String description = "Описание";
        Task task = new EpicTask(name, description);
        String resultIT = name + " {" + description + "}";
        Assertions.assertEquals(resultIT, task.toString());
    }

    @Test
    public void SearchingForAnIdInEpicShouldConfirmThatTaskHasThatId(){
        Task task = new EpicTask("", "");
        int id = task.getID();
        boolean isFind = task.findID(id);
        Assertions.assertTrue(isFind);
    }

    @Test
    public void SearchingForAnIdInEpicShouldNOTConfirmThatTaskHasThatId(){
        Task task = new EpicTask("", "");
        int id = task.getID() + 1;
        boolean isFind = task.findID(id);
        Assertions.assertFalse(isFind);
    }

}
