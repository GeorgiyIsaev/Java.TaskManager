package consoleView;

import com.consoleView.ConsoleNotification;
import com.consoleView.ConsoleUtils;
import com.consoleView.ConsoleView;
import com.controller.IManagerTask;
import com.controller.Managers;
import com.controller.controlException.NotExistIdException;
import com.dateTask.CreateID;
import com.dateTask.Task;
import com.dateTask.TaskStatus;
import com.dateTask.TypeTask;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Objects;

public class ConsoleInteractionWithSubTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    public void setUp(String command) {
        System.setIn(new ByteArrayInputStream(command.getBytes()));
    }
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    public boolean isExistInConsole(String allContent, String findContent){
        return allContent.toUpperCase().contains(findContent.toUpperCase());
    }

    /// ТЕСТ ВЗАИМОДЕЙСТВИЯ С EPIC ЗАДАЧАМИ
    @Test
    void addSubTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        int idEpic = CreateID.INSTANCE.getCurrentID();
        String notificationAddSub = "Add SubTask";
        String command = "addEPIC Название\nОписание\n" +
                "addSubTaskToID " + idEpic + "\nСаб Название\nСаб Описание\n" +
                "exit";
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size());

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, notificationAddSub);
        Assertions.assertTrue(isExist, consoleContent);

        final int EPIC_AND_SUB = 2;
        Assertions.assertEquals(EPIC_AND_SUB, managerTask.getTasks().size());
    }

    @Test
    void reNameTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String newName = "Новое имя";
        int idEpic = CreateID.INSTANCE.getCurrentID();
        int idSub = CreateID.INSTANCE.getCurrentID() + 1;
        String command = "addEPIC Название\nОписание\n" +
                "addSubTaskToID " + idEpic + "\nСаб Название\nСаб Описание\n" +
                "reNameID "+ idSub +"\n" + newName + "\nexit";
        String findContent = ConsoleNotification.RENAME;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех отображен в консоли

        Task lastTask = managerTask.getTask(idSub);
        Assertions.assertNotNull(lastTask);
        Assertions.assertEquals(newName, lastTask.getName());
        Assertions.assertEquals(TypeTask.SUB_NAME, lastTask.getTypeTask());
    }

    @Test
    void reDescTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String newDescription = "Новое описание";
        int idEpic = CreateID.INSTANCE.getCurrentID();
        int idSub = CreateID.INSTANCE.getCurrentID() + 1;
        String command = "addEPIC Название\nОписание\n" +
                "addSubTaskToID " + idEpic + "\nСаб Название\nСаб Описание\n" +
                "reDESCID "+ idSub +"\n" + newDescription + "\nexit\nexit";
        String findContent = ConsoleNotification.REDESC;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех отображен в консоли

        Task lastTask = managerTask.getTask(idSub);
        Assertions.assertNotNull(lastTask);
        Assertions.assertEquals(newDescription, lastTask.getDescription());
        Assertions.assertEquals(TypeTask.SUB_NAME, lastTask.getTypeTask());
    }

    @Test
    void reStatusPROGTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        int idEpic = CreateID.INSTANCE.getCurrentID();
        int idSub = CreateID.INSTANCE.getCurrentID() + 1;
        String command = "addEPIC Название\nОписание\n" +
                "addSubTaskToID " + idEpic + "\nСаб Название\nСаб Описание\n" +
                "newStatusId "+  idSub +" PROG\nexit";
        String findContent = ConsoleNotification.RESTATUS;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех отображен в консоли

        Task lastTask = managerTask.getTask(idSub);
        Assertions.assertNotNull(lastTask); //Проверяем что Задача существует
        Assertions.assertEquals(TypeTask.SUB_NAME, lastTask.getTypeTask(), consoleContent); //Проверяем что Задача действительно Sub
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, lastTask.getStatus(), consoleContent); //Проверяем что описание действительно изменилось
    }

    @Test
    void reStatusNEWTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        int idEpic = CreateID.INSTANCE.getCurrentID();
        int idSub = CreateID.INSTANCE.getCurrentID() + 1;
        String command = "addEPIC Название\nОписание\n" +
                "addSubTaskToID " + idEpic + "\nСаб Название\nСаб Описание\n" +
                "newStatusId "+  idSub +" NEW\nexit";
        String findContent = ConsoleNotification.RESTATUS;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех отображен в консоли

        Task lastTask = managerTask.getTask(idSub);
        Assertions.assertNotNull(lastTask); //Проверяем что Задача существует
        Assertions.assertEquals(TypeTask.SUB_NAME, lastTask.getTypeTask(), consoleContent); //Проверяем что Задача действительно Sub
        Assertions.assertEquals(TaskStatus.NEW, lastTask.getStatus(), consoleContent); //Проверяем что описание действительно изменилось

    }

    @Test
    void reStatusDONETaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        int idEpic = CreateID.INSTANCE.getCurrentID();
        int idSub = CreateID.INSTANCE.getCurrentID() + 1;
        String command = "addEPIC Название\nОписание\n" +
                "addSubTaskToID " + idEpic + "\nСаб Название\nСаб Описание\n" +
                "newStatusId "+  idSub +" DONE\nexit";
        String findContent = ConsoleNotification.RESTATUS;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех отображен в консоли

        Task lastTask = managerTask.getTask(idSub);
        Assertions.assertNotNull(lastTask); //Проверяем что Задача существует
        Assertions.assertEquals(TypeTask.SUB_NAME, lastTask.getTypeTask(), consoleContent); //Проверяем что Задача действительно Sub
        Assertions.assertEquals(TaskStatus.DONE, lastTask.getStatus(), consoleContent); //Проверяем что описание действительно изменилось

    }

    @Test
    void reStatusEXCEPTIONTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        int idEpic = CreateID.INSTANCE.getCurrentID();
        int idSub = CreateID.INSTANCE.getCurrentID() + 1;
        String command = "addEPIC Название\nОписание\n" +
                "addSubTaskToID " + idEpic + "\nСаб Название\nСаб Описание\n" +
                "newStatusId "+  idSub +" EXCEPTION\nexit";
        String findContent = ConsoleNotification.STATUS_INCORRECTLY;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех отображен в консоли
    }

    @Test
    void reStatusNONTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        int idEpic = CreateID.INSTANCE.getCurrentID();
        int idSub = CreateID.INSTANCE.getCurrentID() + 1;
        String command = "addEPIC Название\nОписание\n" +
                "addSubTaskToID " + idEpic + "\nСаб Название\nСаб Описание\n" +
                "newStatusId "+  idSub +"\nexit";
        String findContent = ConsoleNotification.STATUS_INCORRECTLY;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех отображен в консоли
    }

    @Test
    void addSubInSubTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        int idEpic = CreateID.INSTANCE.getCurrentID();
        int idSub = CreateID.INSTANCE.getCurrentID() + 1;
        String command = "addEPIC Название\nОписание\n" +
                "addSubTaskToID " + idEpic + "\nСаб Название\nСаб Описание\n" +
                "addSubTaskToID "+  idSub +"\nSUB to SUB\nОписание SUB\nexit";
        String findContent = ConsoleNotification.NOT_EPIC;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех  отображен в консоли

        final int EPIC_AND_SUB = 2;
        Assertions.assertEquals(EPIC_AND_SUB, managerTask.getTasks().size());
    }

    @Test
    void printIDTaskTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        int idEpic = CreateID.INSTANCE.getCurrentID();
        int idSub = CreateID.INSTANCE.getCurrentID() + 1;
        String command = "addEPIC Название\nОписание\n" +
                "addSubTaskToID " + idEpic + "\nСаб Название\nСаб Описание\n" +
                "printID "+  idEpic +"\nexit";
        String findContent = ConsoleUtils.CONSOLE_TITLE;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExistTitle = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExistTitle, consoleContent); //Успех отображен в консоли


        Task epic = managerTask.getTask(idEpic);
        Task sub = managerTask.getTask(idSub);
        Assertions.assertNotNull(epic); //Проверяем что Задача существует
        Assertions.assertNotNull(sub); //Проверяем что Задача существует
        boolean isExistEpic = isExistInConsole(consoleContent, ConsoleUtils.getTaskString(epic));
        Assertions.assertTrue(isExistEpic, consoleContent); //Успех отображен в консоли EPIC
        boolean isExistSub = isExistInConsole(consoleContent, ConsoleUtils.getTaskString(sub));
        Assertions.assertTrue(isExistSub, consoleContent); //Успех отображен в консоли SUB
    }


    @Test
    void deleteIDSubTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        int idEpic = CreateID.INSTANCE.getCurrentID();
        int idSub = CreateID.INSTANCE.getCurrentID() + 1;
        String command = "addEPIC Название\nОписание\n" +
                "addSubTaskToID " + idEpic + "\nСаб Название\nСаб Описание\n" +
                "printID "+  idEpic + " \n" +
                "deleteID "+  idSub +"\nexit";
        String findContent = ConsoleNotification.DELETE_TASK;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExist = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExist, consoleContent); //Успех удаления отображен в консоли

        Assertions.assertThrows(NotExistIdException.class, ()->{
            Task sub = managerTask.getTask(idSub); //задачи больше не существует в менеджере
        });
        Assertions.assertFalse(managerTask.getTask(idEpic).findID(idSub)); //больше не существует в эпике
    }

    @Test
    void deleteIdEpicContainsSubTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        String notificationAddSub = "Add SubTask";
        int idEpic = CreateID.INSTANCE.getCurrentID();
        int idSub = CreateID.INSTANCE.getCurrentID() + 1;
        String command = "addEPIC Название\nОписание\n" +
                "addSubTaskToID " + idEpic + "\nСаб Название\nСаб Описание\n" +
                "printID "+  idEpic + " \n" +
                "deleteID "+  idEpic +"\nexit";
        String findContent = ConsoleNotification.DELETE_TASK;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();


        String consoleContent = outContent.toString();
        boolean isExistAddSub = isExistInConsole(consoleContent, notificationAddSub);
        Assertions.assertTrue(isExistAddSub, consoleContent); //Проверка что Саб был добавлен

        boolean isExistDelete = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExistDelete, consoleContent); //Успех удаления отображен в консоли



        Assertions.assertThrows(NotExistIdException.class, ()->{
            Task sub = managerTask.getTask(idSub); //задачи sub больше не существует в менеджере
        });
        Assertions.assertThrows(NotExistIdException.class, ()->{
            Task epic = managerTask.getTask(idEpic); //задачи epic больше не существует в менеджере
        });

        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Список менеджера пуст
    }


    @Test
    void printFullSubTest() {
        IManagerTask managerTask = Managers.getDefault();
        ConsoleView consoleView = new ConsoleView(managerTask);
        final int NO_TASKS = 0;
        int idEpic1 = CreateID.INSTANCE.getCurrentID();
        int idSub1 = CreateID.INSTANCE.getCurrentID()+1;
        int idEpic2 = CreateID.INSTANCE.getCurrentID()+2;
        int idSub2 = CreateID.INSTANCE.getCurrentID()+3;

        String command = "addEPIC ЭПИК01\nОписание\n" +
                "addSubTaskToID " + idEpic1 + "\nСаб Название\nСаб Описание\n" +
                "addEPIC ЭПИК02\nОписание\n" +
                "addSubTaskToID " + idEpic2 + "\nСаб Название\nСаб Описание\n" +
                "printSubTask\n" +
                "exit";
        String findContent = ConsoleUtils.CONSOLE_TITLE;
        Assertions.assertEquals(NO_TASKS, managerTask.getTasks().size()); //Перед выполнением список пустой

        setUp(command);
        consoleView.run();

        String consoleContent = outContent.toString();
        boolean isExistTitle = isExistInConsole(consoleContent, findContent);
        Assertions.assertTrue(isExistTitle, consoleContent); //Успех отображен в консоли

        Task sub01 = managerTask.getTask(idSub1);
        Task sub02 = managerTask.getTask(idSub2);

        boolean isExistEpic01= isExistInConsole(consoleContent, ConsoleUtils.getTaskString(sub01));
        Assertions.assertTrue(isExistEpic01, consoleContent);
        boolean isExistEpic02= isExistInConsole(consoleContent, ConsoleUtils.getTaskString(sub02));
        Assertions.assertTrue(isExistEpic02, consoleContent);
    }
}
