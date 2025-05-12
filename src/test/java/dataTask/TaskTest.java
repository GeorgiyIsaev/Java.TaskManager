package dataTask;

import com.dateTask.CONST;
import com.dateTask.CreateID;
import com.dateTask.Task;
import com.dateTask.TaskStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TaskTest {
    public static Task task;

    @BeforeAll
    public static void createTask(){
        int id = CreateID.INSTANCE.createID();
        String name = "Название";
        String description = "Описание";
        task = new Task(id,name,description);

        Assertions.assertEquals(name, task.getName());
        Assertions.assertEquals(description, task.getDescription());
        Assertions.assertEquals(id, task.getID());
        Assertions.assertEquals(TaskStatus.NEW, task.getTaskStatus());
        Assertions.assertEquals(CONST.TASK_NAME, task.getTypeTask());
        Assertions.assertEquals(CONST.NO_REFERENCE, task.getLinkStr());

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
        task.setTaskStatus(taskStatus);
        Assertions.assertEquals(taskStatus, task.getTaskStatus());
    }





}
