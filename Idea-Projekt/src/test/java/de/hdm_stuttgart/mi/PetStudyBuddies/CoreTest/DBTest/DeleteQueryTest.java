package de.hdm_stuttgart.mi.PetStudyBuddies.CoreTest.DBTest;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.DeleteQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DeleteQueryTest {
    @Test
    public void testDelete() {
        new DeleteQuery("User", "Username='TestUserDelete'");
        new InsertQuery("User", new String[]{"Username", "EMail", "Password"}, new String[]{"TestUserDelete", "testdelete@example.com", Utils.sha1("testdelete")});
        Assert.assertEquals(1, new DeleteQuery("User", "Username='TestUserDelete'").Count());
    }

    @Test
    public void testDeleteSeveralTables() throws SQLException {
        new DeleteQuery("ToDoList", "UserID='1' AND Title='testNote2'");
        new InsertQuery("ToDoList", new String[]{"UserID", "Title"}, new String[]{"1","TestNote2"}).GetQueryString();
        ResultSet ToDoListID = new SelectQuery("ToDoList","ID","Title='TestNote2'").fetchAll();
        ArrayList<Integer> IDs = new ArrayList<Integer>();
        while (ToDoListID.next()) {
            IDs.add(ToDoListID.getInt(1));
        }
        for (int i : IDs) {
            new DeleteQuery("ToDoListShare", "UserID='2' AND ToDoListID='" + Integer.toString(i) + "'");
            new InsertQuery("ToDoListShare", new String[]{"UserID","ToDoListID"}, new String[]{"2",Integer.toString(i)});
        }
        // System.out.println(IDs);
        // Assert.assertEquals("DELETE FROM ToDoList WHERE Title='TestNote2';",new DeleteQuery("ToDoList","Title='TestNote2'").GetQueryString());
        // Assert.assertNull("", new SelectQuery("ToDoList","*","Title='TestNote2'",null,null,true));
        ArrayList<String> IDsShare = new ArrayList<>();
        for (int i : IDs) {
            String selectedID = new SelectQuery("ToDoListShare","ID","ToDoListID='"+i+"'").fetch();
            IDsShare.add(selectedID);
            new DeleteQuery("ToDoListShare","ID = "+selectedID+"");
            Assert.assertEquals("",new SelectQuery("ToDoListShare","ID","ToDoListID='"+i+"'").fetch());
        }
    }
}
