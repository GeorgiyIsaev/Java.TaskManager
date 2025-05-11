package controller;

import com.controller.IManagerTask;
import com.controller.Managers;
import com.controller.controlException.NotExistIdException;
import com.dateTask.SubTask;
import com.dateTask.Task;
import com.dateTask.TaskStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Objects;

public class ManagerTaskInMemoryTest {

    public static  IManagerTask managerTask;
    @BeforeAll
    public static void createManager(){
        managerTask = Managers.getDefault();
    }

    @Test
    public void addTest(){
        String name = "Тестовое название";
        String description = "Тестовое описание";
        int oldSize = managerTask.getTaskMap().size();
        Task task = managerTask.addTask(name, description);
        int newSize = managerTask.getTaskMap().size();
        Assertions.assertTrue(newSize>oldSize);
        Assertions.assertTrue("TASK".equalsIgnoreCase(task.getTypeTask()));
        System.out.println("Добавлен " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
    }

    @Test
    public void addEpic(){
        String name = "Тестовое название Epic";
        String description = "Тестовое описание Epic";
        int oldSize = managerTask.getTaskMap().size();
        Task task = managerTask.addEpic(name, description);
        int newSize = managerTask.getTaskMap().size();
        Assertions.assertTrue(newSize>oldSize);
        Assertions.assertTrue("EPIC".equalsIgnoreCase(task.getTypeTask()));
        System.out.println("Добавлен " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
    }

    @Test
    public void addSubTask(){
        Task epic = managerTask.addEpic("EPIC", "description EPIC");
        String name = "Тестовое название SubTask";
        String description = "Тестовое описание SubTask";
        int oldSize = managerTask.getTaskMap().size();
        Task task = managerTask.addSubTaskToEpicID(epic.getID(), name, description);
        int newSize = managerTask.getTaskMap().size();
        Assertions.assertTrue(newSize>oldSize);
        Assertions.assertTrue("SubTask".equalsIgnoreCase(task.getTypeTask()));
        boolean isIdExists = epic.findID(task.getID());
        Assertions.assertTrue(isIdExists);
        System.out.println("Добавлен " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
    }

    @Nested
    class TaskLifeCycle{
        static int idTask;
        @BeforeAll
        public static void addTask(){
            String name = "Тестовое название жиз";
            String description = "Тестовое описание жиз";
            int oldSize = managerTask.getTaskMap().size();
            Task task = managerTask.addTask(name, description);
            idTask = task.getID();
            int newSize = managerTask.getTaskMap().size();
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
            Assertions.assertEquals(taskStatus, task.getTaskStatus());
            System.out.println("Статус " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task
                + " --> " + task.getTaskStatus());

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




}
