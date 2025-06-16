package dataTask;

import com.dateTask.EpicTask;
import com.dateTask.SubTask;
import com.dateTask.Task;
import com.dateTask.TaskStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EpicAndSubTest {

    EpicTask epic;
    SubTask sub;

    @BeforeEach
    public void createEpic(){
        epic = new EpicTask("Эпик","Описание Эпика");
        sub = new SubTask("Саб","Описание Саб", epic);
        epic.addSubTask(sub);
    }

    public SubTask addSub(String name, String description)  {
        SubTask additionalSub = new SubTask("Саб","Описание Саб", epic);
        epic.addSubTask(additionalSub);
        return additionalSub;
    }



    @Test
    public void eEpicShouldPointToTheSubItContains(){
        String infoConnections = "[" + sub.getID() + "]";
        Assertions.assertEquals(infoConnections, epic.getLinkStr(), epic.getLinkStr());
    }

    @Test
    public void EpicShouldPointToTheSubsItContains(){
        SubTask sub2 = addSub("Саб2","Описание Саб2");
        String infoConnections = "[" + sub.getID() + ", "+ sub2.getID() + "]";
        Assertions.assertEquals(infoConnections, epic.getLinkStr(), epic.getLinkStr());
    }

    @Test
    public void subWithTheSameIDShouldReplaceThePreviousOne(){
        epic.addSubTask(sub);
        String infoConnections = "[" + sub.getID()  + "]";
        Assertions.assertEquals(infoConnections, epic.getLinkStr(), epic.getLinkStr());
    }


    @Test
    public void changingSubStatusShouldChangeEpicStatusToDONE(){
        TaskStatus status = TaskStatus.DONE;
        sub.setStatus(status);
        Assertions.assertEquals(status, epic.getStatus());
    }
    @Test
    public void changingSubStatusShouldChangeEpicStatusToPROGRESS(){
        TaskStatus status = TaskStatus.IN_PROGRESS;
        sub.setStatus(status);
        Assertions.assertEquals(status, epic.getStatus());
    }
    @Test
    public void changingSubStatusShouldChangeEpicStatusToNEW(){
        TaskStatus status = TaskStatus.NEW;
        sub.setStatus(status);
        Assertions.assertEquals(status, epic.getStatus());
    }




    @Test
    public void ifAtLeastOneTaskIsNotDONEThenEpicMustBePROGRESS(){
        sub.setStatus(TaskStatus.DONE);
        SubTask sub2 = addSub("Саб2","Описание Саб2");
        sub2.setStatus(TaskStatus.IN_PROGRESS);
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, epic.getStatus());
    }

    @Test
    public void ifAtLeastOneTaskIsNotDONEThenEpicMustBePROGRESSDub(){
        sub.setStatus(TaskStatus.IN_PROGRESS);
        SubTask sub2 = addSub("Саб2","Описание Саб2");
        sub2.setStatus(TaskStatus.DONE);
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, epic.getStatus());
    }

    @Test
    public void ifAtLeastAllDoneTaskIsNotDONEThenEpicMustBeDONE(){
        sub.setStatus(TaskStatus.DONE);
        SubTask sub2 = addSub("Саб2","Описание Саб2");
        sub2.setStatus(TaskStatus.DONE);
        Assertions.assertEquals(TaskStatus.DONE, epic.getStatus());
    }



    @Test
    public void subContainerSizeShouldBeEqualToOne(){
        int SIZE_SHOULD = 1;
        Assertions.assertEquals(SIZE_SHOULD, epic.getSubTasks().size());
    }

    @Test
    public void shouldBeSubAddedToTheSubContainer(){
        boolean isExistSub = false;
        for(SubTask subTask : epic.getSubTasks()){
            if (subTask == sub) {
                isExistSub = true;
                break;
            }
        }
        Assertions.assertTrue(isExistSub);
    }



}
