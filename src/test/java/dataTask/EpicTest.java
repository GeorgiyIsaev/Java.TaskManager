package dataTask;

import com.dateTask.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EpicTest {
    public static EpicTask task;

    @BeforeAll
    public static void createTask(){
        int id = CreateID.INSTANCE.createID();
        String name = "Название";
        String description = "Описание";
        task = new EpicTask(id,name,description);

        Assertions.assertEquals(name, task.getName());
        Assertions.assertEquals(description, task.getDescription());
        Assertions.assertEquals(id, task.getID());
        Assertions.assertEquals(TaskStatus.NEW, task.getStatus());
        Assertions.assertEquals(CONST.EPIC_NAME, task.getTypeTask());
        Assertions.assertEquals("[]", task.getLinkStr());

    }

    @Test
    public void changeTaskName(){
        String newName ="Новое имя";
        task.setName(newName);
        Assertions.assertEquals(newName, task.getName());
    }
    @Test
    public void changeTaskDescription(){
        String newDesc ="Новое имя";
        task.setDescription(newDesc);
        Assertions.assertEquals(newDesc, task.getDescription());
    }

    @ParameterizedTest
    @ValueSource(strings = { "NEW", "IN_PROGRESS", "DONE", "NEW" })
    public void changeTaskStatus(TaskStatus taskStatus){
        TaskStatus oldStatus = task.getStatus();
        task.setStatus(taskStatus);
        Assertions.assertEquals(oldStatus, task.getStatus());

        System.out.println("Статус для Эпика нельзя менять");

    }

    @Test
    public void addSub(){
        int id = CreateID.INSTANCE.createID();
        String nameSub ="Имя Саба";
        String descriptionSub = "Описание";
        SubTask sub = new SubTask(id,nameSub,descriptionSub, task);

        task.addSubTask(sub);

        Assertions.assertTrue(task.findID(id));

        Task subInEpic = task.getSubTasks().get(0);

        Assertions.assertEquals(nameSub, subInEpic.getName());
        Assertions.assertEquals(descriptionSub, subInEpic.getDescription());
        Assertions.assertEquals(id, subInEpic.getID());
        Assertions.assertEquals(TaskStatus.NEW, subInEpic.getStatus());
        Assertions.assertEquals(CONST.SUB_NAME, subInEpic.getTypeTask());
        Assertions.assertEquals("" + task.getID(), subInEpic.getLinkStr());

    }

}
