package controller;

import com.consoleView.ConsoleUtils;
import com.controller.IManagerTask;
import com.controller.ManagerFile;
import com.controller.Managers;
import com.controller.controlException.NotExistIdException;
import com.dateTask.CONST;
import com.dateTask.Task;
import org.junit.jupiter.api.*;

public class ManagerFileTest {
    public static IManagerTask managerTask;
    public static Task task;


    @BeforeAll
    public static void start(){
        managerTask = Managers.getDefault();
        task = managerTask.addTask("0","0");
    }


    @BeforeEach
    public void saveTest(){
        ManagerFile.save(managerTask);
    }

    @Test
    public void loadTask(){
        managerTask.deleteALL();
        ManagerFile.load(managerTask);
        Task newTask = managerTask.getTask(task.getID());
        System.out.println(ConsoleUtils.CONSOLE_TITLE);
        System.out.println(ConsoleUtils.getTaskString(newTask));
    }

    @AfterAll
    public static void noLoadTest(){
        ManagerFile.deleteFile();

        managerTask.deleteALL();
        ManagerFile.load(managerTask);

        System.out.println("Загружаем не существующий файл");
        Assertions.assertThrows(NotExistIdException.class, ()->{
            Task newTask=  managerTask.getTask(task.getID());
            System.out.println(ConsoleUtils.CONSOLE_TITLE);
            System.out.println(ConsoleUtils.getTaskString(newTask));
        });
        System.out.println("Конец");



    }




}
