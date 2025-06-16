package dataTask;

import com.dateTask.TaskStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TaskStatusTest {
    @ParameterizedTest
    @ValueSource(strings = {"IN_PROGRESS", "IN_PROG"})
    public void statusShouldChangeToPROGRESS(String text){
        TaskStatus taskStatus =  TaskStatus.toTaskStatus(text);
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, taskStatus);
    }

    @ParameterizedTest
    @ValueSource(strings = {"DONE"})
    public void statusShouldChangeToDONE(String text){
        TaskStatus taskStatus =  TaskStatus.toTaskStatus(text);
        Assertions.assertEquals(TaskStatus.DONE, taskStatus);
    }
    @ParameterizedTest
    @ValueSource(strings = {"NEW","","12132","ehhjnregv fefw ewfwe"})
    public void statusShouldChangeToNEW(String text){
        TaskStatus taskStatus =  TaskStatus.toTaskStatus(text);
        Assertions.assertEquals(TaskStatus.NEW, taskStatus);
    }

    @Test
    public void StatusAsStringShouldReturnNEW(){
        String statusShould = "NEW";
        String statusIt = TaskStatus.NEW.toString();
        Assertions.assertEquals(statusShould, statusIt);
    }

    @Test
    public void StatusAsStringShouldReturnDONE(){
        String statusShould = "DONE";
        String statusIt = TaskStatus.DONE.toString();
        Assertions.assertEquals(statusShould, statusIt);
    }

    @Test
    public void StatusAsStringShouldReturnIN_PROG(){
        String statusShould = "IN_PROG";
        String statusIt = TaskStatus.IN_PROGRESS.toString();
        Assertions.assertEquals(statusShould, statusIt);
    }

}
