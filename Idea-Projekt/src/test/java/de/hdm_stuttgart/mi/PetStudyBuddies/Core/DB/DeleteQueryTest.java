package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import org.junit.Assert;
import org.junit.Test;


public class DeleteQueryTest {
    @Test
    public void testDelete() {
        new InsertQuery("User", new String[]{"Username", "EMail", "Password"}, new String[]{"TestUserDelete", "testdelete@example.com", Utils.sha1("testdelete")}, true).GetQueryString();
        Assert.assertEquals("DELETE FROM User WHERE Username IS 'TestUserDelete';",new DeleteQuery("User","Username IS 'TestUserDelete'").GetQueryString());

    }
    @Test
    public void testDeleteSeveralTables() {
       //new InsertQuery("ToDoList", new String[]{"UserID","Title"}, new String[]{"1","TestNote2"}, true).GetQueryString();
       new InsertQuery("ToDoListShare", new String[]{"UserID","ToDoListID"}, new String[]{"1","1"}, true).GetQueryString();
       //Assert.assertEquals("DELETE FROM ToDoList WHERE ID=8;",new DeleteQuery("ToDoList","ID=8").GetQueryString());

    }
}
