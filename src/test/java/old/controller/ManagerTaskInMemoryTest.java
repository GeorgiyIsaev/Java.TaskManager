package old.controller;

import com.consoleView.ConsoleUtils;
import com.controller.IManagerTask;
import com.controller.Managers;
import com.controller.controlException.NotChangedEpicStatusException;
import com.controller.controlException.NotExistIdException;
import com.dateTask.TypeTask;
import com.dateTask.Task;
import com.dateTask.TaskStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ManagerTaskInMemoryTest {

    public static  IManagerTask managerTask;
    @BeforeAll
    public static void createManager(){
        managerTask = Managers.getDefault();
    }


    @Nested
    class TaskLifeCycle{
        static int idTask;
        @BeforeAll
        public static void addTask(){
            String name = "Тестовое название жиз";
            String description = "Тестовое описание жиз";
            int oldSize = managerTask.getTasks().size();
            Task task = managerTask.addTask(name, description);
            idTask = task.getID();
            int newSize = managerTask.getTasks().size();
            Assertions.assertTrue(newSize>oldSize);
            Assertions.assertTrue("TASK".equalsIgnoreCase(task.getTypeTask()));
            System.out.println("Добавлен " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
        }
        @Test
        public void reNameTask(){
            Task task = managerTask.getTask(idTask);
            System.out.println("Переименовывается " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
            String newName = "Новое название";
            managerTask.reNameToIDTask(idTask, newName);
            Assertions.assertTrue(newName.equalsIgnoreCase(task.getName()));
            System.out.println("Изменение " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
        }

        @Test
        public void reDescTask(){
            Task task = managerTask.getTask(idTask);
            System.out.println("Переименовывается " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
            String newName = "Новое описание";
            managerTask.reDescToIDTask(idTask, newName);
            Assertions.assertTrue(newName.equalsIgnoreCase(task.getDescription()));
            System.out.println("Изменение " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
        }

        @ParameterizedTest
        @ValueSource(strings = { "NEW", "IN_PROGRESS", "DONE", "NEW" })
        public void reStatusTask(TaskStatus taskStatus){
            Task task = managerTask.getTask(idTask);
            managerTask.reStatus(idTask, taskStatus);
            Assertions.assertEquals(taskStatus, task.getStatus());
            System.out.println("Статус " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task
                + " --> " + task.getStatus());

        }



        @AfterAll
        public static void removeTask(){
            Task task = managerTask.getTask(idTask);
            System.out.println("Удаляется " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);

            managerTask.deleteIDTask(idTask);
            Assertions.assertThrows(NotExistIdException.class, ()->{
                Task taskBeforeDeleteTask =  managerTask.getTask(idTask);
            });
            System.out.println("Task c id " + idTask + " удален!");
        }

    }

    @Nested
    class EpicLifeCycle{
        static int idEpic;
        static int idSub;

        @BeforeAll
        public static void createEpic(){
            String name = "Тестовое название Epic";
            String description = "Тестовое описание Epic";
            int oldSize = managerTask.getTasks().size();
            Task task = managerTask.addEpic(name, description);
            int newSize = managerTask.getTasks().size();
            idEpic = task.getID();

            Assertions.assertTrue(newSize>oldSize);
            Assertions.assertTrue(TypeTask.EPIC_NAME.equalsIgnoreCase(task.getTypeTask()));
            System.out.println("Добавлен " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
        }

        @BeforeEach
        public void addSub() {
            String name = "Тестовое название SubTask";
            String description = "Тестовое описание SubTask";
            int oldSize = managerTask.getTasks().size();
            Task task = managerTask.addSubTaskToEpicID(idEpic, name, description);
            int newSize = managerTask.getTasks().size();
            idSub = task.getID();

            Assertions.assertTrue(newSize>oldSize);
            Assertions.assertTrue(TypeTask.SUB_NAME.equalsIgnoreCase(task.getTypeTask()));
            boolean isIdExists = managerTask.getTask(idEpic).findID(task.getID());
            Assertions.assertTrue(isIdExists);
            System.out.println("Добавлен " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
        }

        //Изменение Эпика
        @Test
        public void reNameTaskEpic(){
            Task task = managerTask.getTask(idEpic);
            System.out.println("Переименовывается " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
            String newName = "Новое название";
            managerTask.reNameToIDTask(idEpic, newName);
            Assertions.assertTrue(newName.equalsIgnoreCase(task.getName()));
            System.out.println("Изменение " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
        }
        @Test
        public void reDescTaskEpic(){
            Task task = managerTask.getTask(idEpic);
            System.out.println("Переименовывается " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
            String newName = "Новое описание";
            managerTask.reDescToIDTask(idEpic, newName);
            Assertions.assertTrue(newName.equalsIgnoreCase(task.getDescription()));
            System.out.println("Изменение " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
        }
        @ParameterizedTest
        @ValueSource(strings = { "NEW", "IN_PROGRESS", "DONE", "NEW" })
        public void reStatusTaskEpic(TaskStatus taskStatus){
            Task task = managerTask.getTask(idEpic);

            Assertions.assertThrows(NotChangedEpicStatusException.class, ()->{
                managerTask.reStatus(idEpic, taskStatus);
            });
            System.out.println("Не допускается изменение Эпика!");

        }

        //Изменение Саба
        @Test
        public void reNameTaskSub(){
            Task task = managerTask.getTask(idSub);
            System.out.println("Переименовывается " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
            String newName = "Новое название";
            managerTask.reNameToIDTask(idSub, newName);
            Assertions.assertTrue(newName.equalsIgnoreCase(task.getName()));
            System.out.println("Изменение " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
        }
        @Test
        public void reDescTaskSub(){
            Task task = managerTask.getTask(idSub);
            System.out.println("Переименовывается " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
            String newName = "Новое описание";
            managerTask.reDescToIDTask(idSub, newName);
            Assertions.assertTrue(newName.equalsIgnoreCase(task.getDescription()));
            System.out.println("Изменение " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
        }
        @ParameterizedTest
        @ValueSource(strings = { "NEW", "IN_PROGRESS", "DONE", "NEW" })
        public void reStatusTaskSub(TaskStatus taskStatus){
            Task task = managerTask.getTask(idSub);
            managerTask.reStatus(idSub, taskStatus);

            //TaskStatus oldStatus = task
            Assertions.assertEquals(taskStatus, task.getStatus());
            System.out.println("Статус " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task
                    + " --> " + task.getStatus());

        }

        //Удаление Sub
        @AfterEach
        public void deleteSub(){
            Task task = managerTask.getTask(idSub);
            System.out.println("Удаляем " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
            managerTask.deleteIDTask(idSub);

            Assertions.assertThrows(NotExistIdException.class, ()->{
                Task taskBeforeDeleteTask =  managerTask.getTask(idSub);
            });
            System.out.println("Task c id " + idSub + " удален и недоступен!");

            boolean isIdSubExistsInEpic =  managerTask.getTask(idEpic).findID(idSub);
            Assertions.assertFalse(isIdSubExistsInEpic);
            System.out.println("Task c id " + idSub + " не существует внутри Epic!");

            Assertions.assertThrows(NotExistIdException.class, ()->{
                managerTask.deleteIDTask(idSub);
            });
            System.out.println("Повторное удаление TASK c " + idSub + " вызывает ошибку NotExistIdException!");

            //Добавим новый sub
            Task subTask =  managerTask.addSubTaskToEpicID(idEpic, "Замена", "Замена");
            System.out.println("Замена Саб");
            System.out.println(ConsoleUtils.getTaskString(subTask));
            idSub = subTask.getID();
        }

        @AfterAll
        public static void deleteEpic(){
            Task task = managerTask.getTask(idEpic);
            System.out.println("Удаляем " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
            System.out.println("Содержит " + managerTask.getTask(idSub).getTypeTask() + ": ID " +managerTask.getTask(idSub).getID()+ " - " + task);
            managerTask.deleteIDTask(idEpic);

            Assertions.assertThrows(NotExistIdException.class, ()->{
                Task taskBeforeDeleteTask =  managerTask.getTask(idEpic);
            });
            System.out.println(task.getTypeTask() + " c id " + idEpic + " удален и недоступен!");

            Assertions.assertThrows(NotExistIdException.class, ()->{
                Task taskBeforeDeleteTask =  managerTask.getTask(idSub);
            });
            System.out.println("Связанный с ним Sub c id " + idSub + " удален и недоступен!");

            Assertions.assertThrows(NotExistIdException.class, ()->{
                managerTask.deleteIDTask(idSub);
            });
            System.out.println("Повторное удаление TASK c " + idEpic + " вызывает ошибку NotExistIdException!");


        }

    }

    @AfterAll
    public static void deleteAll(){
        managerTask.deleteALL();
        int sizeClearMap = 0;
        Assertions.assertEquals(sizeClearMap, managerTask.getTasks().size());
    }

}
