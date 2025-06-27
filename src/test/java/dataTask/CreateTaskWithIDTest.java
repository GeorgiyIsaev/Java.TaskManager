package dataTask;

import com.dateTask.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateTaskWithIDTest {

    @Test
    public void taskIDShouldBeTheSameAsPassedIn(){
        int forwardedID = 2;
        Task task = new Task(forwardedID,"", "");
        int idTask = task.getID();
        Assertions.assertEquals(forwardedID, idTask);
    }

    @Test
    public void createdIDTaskBeingShouldBeLarger(){
        int forwardedID = 1000;
        Task task = new Task(forwardedID,"", "");
        int newCreateID= CreateID.INSTANCE.createID();

        boolean isBigger = newCreateID > task.getID();
        Assertions.assertTrue(isBigger);
    }

    @Test
    public void epicIDShouldBeTheSameAsPassedIn(){
        int forwardedID = 2;
        Task task = new EpicTask(forwardedID,"", "");
        int idTask = task.getID();
        Assertions.assertEquals(forwardedID, idTask);
    }

    @Test
    public void createdIDEpicBeingShouldBeLarger(){
        int forwardedID = 1000;
        Task task = new EpicTask(forwardedID,"", "");
        int newCreateID= CreateID.INSTANCE.createID();

        boolean isBigger = newCreateID > task.getID();
        Assertions.assertTrue(isBigger);
    }

    @Test
    public void subIDShouldBeTheSameAsPassedIn(){
        int forwardedID = 2;
        EpicTask epicTask = new EpicTask(forwardedID,"", "");
        Task task = new SubTask(forwardedID,"", "", epicTask);
        int idTask = task.getID();
        Assertions.assertEquals(forwardedID, idTask);
    }

    @Test
    public void createdIDSubBeingShouldBeLarger(){
        int forwardedID = 1000;
        EpicTask epicTask = new EpicTask(forwardedID,"", "");
        Task task = new SubTask(forwardedID,"", "", epicTask);
        int newCreateID= CreateID.INSTANCE.createID();

        boolean isBigger = newCreateID > task.getID();
        Assertions.assertTrue(isBigger);
    }






}
