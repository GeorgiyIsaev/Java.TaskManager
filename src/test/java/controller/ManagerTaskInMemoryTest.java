package controller;

import com.controller.IManagerTask;
import com.controller.Managers;
import com.dateTask.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        String name = "Тестовое название SubTask";
        String description = "Тестовое описание SubTask";
        int oldSize = managerTask.getTaskMap().size();
        Task task = managerTask.addEpic(name, description);
        int newSize = managerTask.getTaskMap().size();
        Assertions.assertTrue(newSize>oldSize);
        Assertions.assertTrue("EPIC".equalsIgnoreCase(task.getTypeTask()));
        System.out.println("Добавлен " + task.getTypeTask() + ": ID " +task.getID()+ " - " + task);
    }




}
