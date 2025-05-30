//package old.dataTask;
//
//import com.dateTask.*;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//
//public class SubTaskTest {
//    public static EpicTask epicTask;
//    public static SubTask subTask;
//
//    @BeforeAll
//    public static void createTask(){
//        epicTask = new EpicTask(CreateID.INSTANCE.createID(),
//                "Эпик для теста","Описание Эпика");
//
//
//        int id = CreateID.INSTANCE.createID();
//        String name = "Название Саб";
//        String description = "Описание Саб";
//        subTask = new SubTask(id,name,description, epicTask);
//
//        Assertions.assertEquals(name, subTask.getName());
//        Assertions.assertEquals(description, subTask.getDescription());
//        Assertions.assertEquals(id, subTask.getID());
//        Assertions.assertEquals(TaskStatus.NEW, subTask.getStatus());
//        Assertions.assertEquals(TypeTask.SUB_NAME, subTask.getTypeTask());
//        Assertions.assertEquals("" + epicTask.getID(), subTask.getLinkStr());
//
//    }
//
//    @Test
//    public void changeTaskName(){
//        String newName ="Новое имя Саб";
//        subTask.setName(newName);
//        Assertions.assertEquals(newName, subTask.getName());
//    }
//    @Test
//    public void changeTaskDescription(){
//        String newDesc ="Новое описание Саб";
//        subTask.setDescription(newDesc);
//        Assertions.assertEquals(newDesc, subTask.getDescription());
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = { "NEW", "IN_PROGRESS", "DONE", "NEW" })
//    public void changeTaskStatus(TaskStatus taskStatus){
//        subTask.setStatus(taskStatus);
//        Assertions.assertEquals(taskStatus, subTask.getStatus());
//    }
//
//    @AfterAll
//    public static void deleteSub(){
//        epicTask.deleteSubTask(subTask);
//        Assertions.assertFalse(epicTask.findID(subTask.getID()));
//    }
//}
