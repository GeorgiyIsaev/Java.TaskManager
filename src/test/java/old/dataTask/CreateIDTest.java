package old.dataTask;

import com.dateTask.CreateID;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CreateIDTest {

    @BeforeAll
    public static void nextIdRun(){
        for(int i =0; i<10; i++){
            int id1 =   CreateID.INSTANCE.createID();
            System.out.println("newID: " + id1);
        }
    }

    @Test
    public void newIdShouldBeGreaterThanThePreviousOne(){
        int id1 =  CreateID.INSTANCE.createID();
        int id2 =  CreateID.INSTANCE.createID();
        Assertions.assertTrue(id2>id1 );
        System.out.println("ID: " + id2 + " > " + id1);
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 20, 26, 1000, 10, 0, -1})
    public void theAddedStartIdShouldNotDecreaseTheId(int newId){
        //Добавленный стартовый id не должен уменьшать id
        CreateID.INSTANCE.setId(newId);
        int id2 =  CreateID.INSTANCE.createID();
        Assertions.assertTrue(id2>newId );
        System.out.println("newID: " + newId + " currentID: " + id2);
    }
}
