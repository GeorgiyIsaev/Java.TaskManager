package old.controller;

import com.controller.IManagerTask;
import com.controller.Managers;
import com.dateTask.CreateID;
import com.dateTask.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ManagerHistoryInMemoryTest {
    public static IManagerTask managerTask;

    @BeforeAll
    public static void startAll(){
        managerTask = Managers.getDefault();
        //managerTask.getHistory();
    }
    @Test
    public void addTaskShouldAddTaskHistory(){
        managerTask.deleteALL();
        Task task1= managerTask.addTask( "01", "Описание 01");
        managerTask.getTask(task1.getID());
        Task task2 = managerTask.addTask( "02", "Описание 02");
        managerTask.getTask(task2.getID());
        Task task3 =  managerTask.addTask( "03", "Описание 03");
        managerTask.getTask(task3.getID());

        Assertions.assertEquals(3, managerTask.getHistory().size());
        System.out.println("Проверка что 3 разных задачи добавились");
        System.out.println(managerTask.getHistory());
    }

    @Test
    public void addTaskShouldAddTaskHistoryEquals() {
        managerTask.deleteALL();
        Task task = managerTask.addTask("01", "Описание 01");
        managerTask.getTask(task.getID());
        Task taskHistory = managerTask.getHistory().get(0);
        Assertions.assertEquals(task, taskHistory);
        System.out.println("Сравнение двух задач");

    }

    @Test
    public void addTaskShouldAddTaskHistoryNoDuplicates() {
        managerTask.deleteALL();
        Task task = managerTask.addTask("01", "Описание 01");
        managerTask.getTask(task.getID());
        managerTask.getTask(task.getID());
        managerTask.getTask(task.getID());
        Assertions.assertEquals(1, managerTask.getHistory().size());
        System.out.println("Добавление нескольких одинаковых задач");
        System.out.println(managerTask.getHistory());
    }

    @Test
    public void deleteTaskShouldAddTaskHistoryDeleteTask() {
        managerTask.deleteALL();
        Task task = managerTask.addTask("01", "Описание 01");
        managerTask.getTask(task.getID());
        Assertions.assertEquals(1, managerTask.getHistory().size());
        System.out.println("Удаление Задачи!");
        System.out.println(managerTask.getHistory());

        managerTask.deleteIDTask(task.getID());
        Assertions.assertEquals(0, managerTask.getHistory().size());
    }



    @Test
    public void deleteEpicShouldAddTaskHistoryDeleteEpic() {
        managerTask.deleteALL();
        Task task = managerTask.addEpic("01", "Описание 01");
        managerTask.getTask(task.getID());
        Task sub1 = managerTask.addSubTaskToEpicID(task.getID(),"001", "САБ 01");
        managerTask.getTask(sub1.getID());
        Task sub2 = managerTask.addSubTaskToEpicID(task.getID(),"002", "САБ 02");
        managerTask.getTask(sub2.getID());
        Assertions.assertEquals(3, managerTask.getHistory().size());
        System.out.println("Удаление Эпика!");
        System.out.println(managerTask.getHistory());

        managerTask.deleteIDTask(task.getID());
        Assertions.assertEquals(0, managerTask.getHistory().size());
    }



}
