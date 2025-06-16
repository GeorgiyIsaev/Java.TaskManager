package dataTask;

import com.dateTask.CreateID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateIDTest {


    @Test
    public void CurrentOneShouldBeEqualToTheNewID(){
        int currentId = CreateID.INSTANCE.getCurrentID();
        int newId = CreateID.INSTANCE.createID();
        Assertions.assertEquals(newId, currentId);
    }


    @Test
    public void NewIDShouldBeOneGreaterThanCurrentID(){
        int newId = CreateID.INSTANCE.createID();
        int currentId = CreateID.INSTANCE.getCurrentID();
        Assertions.assertEquals(currentId, newId + 1);
    }


    @Test
    public void AfterReplacingIDCurrentIDAndCreateIDShouldBeOnMore(){
        int startID = 1000;
        CreateID.INSTANCE.setId(startID);
        int currentId = CreateID.INSTANCE.getCurrentID();
        startID++;
        Assertions.assertEquals(startID, currentId);
        int newId = CreateID.INSTANCE.createID();
        Assertions.assertEquals(startID, newId);
    }


    @Test
    public void ReplacingItWithSmallerIDShouldNotDecreaseIt(){
        int startID = 500;
        int startIDmin = 300;
        CreateID.INSTANCE.setId(startID);
        CreateID.INSTANCE.setId(startIDmin);
        int currentId = CreateID.INSTANCE.getCurrentID();
        boolean is = startID+1 <= currentId;
        Assertions.assertTrue(is);
    }

}
