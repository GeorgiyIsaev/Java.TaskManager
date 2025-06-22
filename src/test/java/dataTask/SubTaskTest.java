package dataTask;

import com.dateTask.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubTaskTest {

    EpicTask epic;

    @BeforeEach
    public void createEpic(){
        epic = new EpicTask("Эпик", "Описание");
    }


    @Test
    public void checkThatTheSubReturnsTheCorrectType(){
        Task task = new SubTask("", "", epic);
        String typeTasK = task.getTypeTask();
        String typeTasKShouldBy = TaskType.SUBTASK.name();
        Assertions.assertEquals(typeTasKShouldBy, typeTasK);
    }

    @Test
    public void idOfNewSubMustBeOneMoreThanPreviousOne(){
        Task task1 = new SubTask("", "", epic);
        Task task2 = new SubTask("", "", epic);
        Assertions.assertEquals(task1.getID() +1 , task2.getID());
    }

    @Test
    public void  returnedSubNameShouldMatchTheOnePassedToConstructor(){
        String nameTask = "Имя тестовой задачи";
        Task task = new SubTask(nameTask, "", epic);
        Assertions.assertEquals(nameTask, task.getName());
    }
    @Test
    public void  returnedSubDescriptionShouldMatchTheOnePassedToConstructor(){
        String description = "Описание тестовой задачи";
        Task task = new SubTask("", description, epic);
        Assertions.assertEquals(description, task.getDescription());
    }

    @Test
    public void SubShouldNotHaveAnyConnections(){
        Task task = new SubTask("", "", epic);
        String infoConnections = epic.getID() + "";
        Assertions.assertEquals(infoConnections, task.getLinkStr(),  task.getLinkStr());
    }


    @Test
    public void subStatusShouldBeSetToNewWhenCreated(){
        Task task = new SubTask("", "", epic);
        Assertions.assertEquals(TaskStatus.NEW, task.getStatus());
    }


    @Test
    public void subShouldChangeItsStatusToDONE(){
        Task task = new SubTask("", "", epic);
        task.setStatus(TaskStatus.DONE);
        Assertions.assertEquals(TaskStatus.DONE, task.getStatus());
    }
    @Test
    public void subShouldChangeItsStatusToPROGRESS(){
        Task task = new SubTask("", "", epic);;
        task.setStatus(TaskStatus.IN_PROGRESS);
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }


    @Test
    public void SubShouldChangeTheName(){
        Task task = new SubTask("", "", epic);;
        String newName = "Новое имя задачи";
        task.setName(newName);
        Assertions.assertEquals(newName, task.getName());
    }

    @Test
    public void subShouldChangeTheDescription(){
        Task task = new SubTask("", "", epic);;
        String newDescription = "Новое описание задачи";
        task.setDescription(newDescription);
        Assertions.assertEquals(newDescription, task.getDescription());
    }

    @Test
    public void subShouldReturnStringDescription(){
        String name = "Имя";
        String description = "Описание";
        Task task = new SubTask(name, description, epic);;
        String resultIT = name + " {" + description + "}";
        Assertions.assertEquals(resultIT, task.toString());
    }

    @Test
    public void searchingForAnIdInSubShouldConfirmThatTaskHasThatId(){
        Task task = new SubTask("", "", epic);;
        int id = task.getID();
        boolean isFind = task.findID(id);
        Assertions.assertTrue(isFind);
    }

    @Test
    public void searchingForAnIdInSubShouldNOTConfirmThatTaskHasThatId(){
        Task task = new SubTask("", "", epic);;
        int id = task.getID() + 1;
        boolean isFind = task.findID(id);
        Assertions.assertFalse(isFind);
    }


    @Test
    public void theLinkToEpicShouldBeVerified(){
        SubTask task = new SubTask("", "", epic);
        EpicTask epicTaskRef = task.getRefrains();
        Assertions.assertEquals(epic, epicTaskRef);
    }

}
