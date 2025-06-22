package dataTask;

import com.dateTask.Task;
import com.dateTask.TaskStatus;
import com.dateTask.TaskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {


    @Test
    public void checkThatTheTaskReturnsTheCorrectType(){
        Task task = new Task("", "");
        String typeTasK = task.getTypeTask();
        String typeTasKShouldBy = TaskType.TASK.name();
        Assertions.assertEquals(typeTasKShouldBy, typeTasK);
    }

    @Test
    public void idOfNewTaskMustBeOneMoreThanPreviousOne(){
        Task task1 = new Task("", "");
        Task task2 = new Task("", "");
        Assertions.assertEquals(task1.getID() +1 , task2.getID());
    }

    @Test
    public void  returnedTaskNameShouldMatchTheOnePassedToConstructor(){
        String nameTask = "Имя тестовой задачи";
        Task task = new Task(nameTask, "");
        Assertions.assertEquals(nameTask, task.getName());
    }
    @Test
    public void  returnedTaskDescriptionShouldMatchTheOnePassedToConstructor(){
        String description = "Описание тестовой задачи";
        Task task = new Task("", description);
        Assertions.assertEquals(description, task.getDescription());
    }

    @Test
    public void taskShouldNotHaveAnyConnections(){
        Task task = new Task("", "");
        String infoConnections = Task.NO_REFERENCE;
        Assertions.assertEquals(infoConnections, task.getLinkStr());
    }


    @Test
    public void taskStatusShouldBeSetToNewWhenCreated(){
        Task task = new Task("", "");
        Assertions.assertEquals(TaskStatus.NEW, task.getStatus());
    }


    @Test
    public void taskShouldChangeItsStatusToDONE(){
        Task task = new Task("", "");
        task.setStatus(TaskStatus.DONE);
        Assertions.assertEquals(TaskStatus.DONE, task.getStatus());
    }
    @Test
    public void taskShouldChangeItsStatusToPROGRESS(){
        Task task = new Task("", "");
        task.setStatus(TaskStatus.IN_PROGRESS);
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }


    @Test
    public void TaskShouldChangeTheName(){
        Task task = new Task("", "");
        String newName = "Новое имя задачи";
        task.setName(newName);
        Assertions.assertEquals(newName, task.getName());
    }

    @Test
    public void taskShouldChangeTheDescription(){
        Task task = new Task("", "");
        String newDescription = "Новое описание задачи";
        task.setDescription(newDescription);
        Assertions.assertEquals(newDescription, task.getDescription());
    }

    @Test
    public void taskShouldReturnStringDescription(){
        String name = "Имя";
        String description = "Описание";
        Task task = new Task(name, description);
        String resultIT = name + " {" + description + "}";
        Assertions.assertEquals(resultIT, task.toString());
    }

    @Test
    public void searchingForAnIdInTaskShouldConfirmThatTaskHasThatId(){
        Task task = new Task("", "");
        int id = task.getID();
        boolean isFind = task.findID(id);
        Assertions.assertTrue(isFind);
    }

    @Test
    public void searchingForAnIdInTaskShouldNOTConfirmThatTaskHasThatId(){
        Task task = new Task("", "");
        int id = task.getID() + 1;
        boolean isFind = task.findID(id);
        Assertions.assertFalse(isFind);
    }



}
