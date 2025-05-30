//package old.dataTask;
//
//import com.dateTask.TaskStatus;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//
//public class TaskStatusTest {
//
//    @ParameterizedTest
//    @ValueSource(strings = {"IN_PROGRESS", "IN_PROG"})
//    public void toTaskStatusTestProd(String text){
//        TaskStatus taskStatus =  TaskStatus.toTaskStatus(text);
//        Assertions.assertEquals(TaskStatus.IN_PROGRESS, taskStatus);
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"DONE"})
//    public void toTaskStatusTestDone(String text){
//        TaskStatus taskStatus =  TaskStatus.toTaskStatus(text);
//        Assertions.assertEquals(TaskStatus.DONE, taskStatus);
//    }
//    @ParameterizedTest
//    @ValueSource(strings = {"NEW","","12132","ehhjnregv fefw ewfwe"})
//    public void toTaskStatusTestNew(String text){
//        TaskStatus taskStatus =  TaskStatus.toTaskStatus(text);
//        Assertions.assertEquals(TaskStatus.NEW, taskStatus);
//    }
//}
