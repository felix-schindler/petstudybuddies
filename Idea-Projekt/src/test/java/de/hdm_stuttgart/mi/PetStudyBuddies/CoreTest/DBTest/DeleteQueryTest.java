package de.hdm_stuttgart.mi.PetStudyBuddies.CoreTest.DBTest;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.DeleteQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeleteQueryTest {
    @Test
    public void testInsertSimple() {
        Assert.assertEquals("DELETE FROM User;", new DeleteQuery("User", null, false).GetQueryString());
        Assert.assertEquals("DELETE FROM User;", new DeleteQuery("User", "", false).GetQueryString());
        Assert.assertEquals("DELETE FROM User WHERE UserID=1;", new DeleteQuery("User", "UserID=1", false).GetQueryString());
    }

    @Test
    public void testInsertNull() {
        Assert.assertEquals("", new InsertQuery(null, null, null, false).GetQueryString());
    }

    @Test
    public void testDBDelete() {
        new DeleteQuery("User", "Username='TestUserDelete'");
        new InsertQuery("User", new String[]{"Username", "EMail", "Password"}, new String[]{"TestUserDelete", "testdelete@example.com", Utils.sha1("testdelete")});
        Assert.assertEquals(1, new DeleteQuery("User", "Username='TestUserDelete'").Count());
    }

    @Test
    public void testDBDeleteSeveralTables() throws SQLException {
        new DeleteQuery("ToDoList", "UserID=101 AND LOWER(Title)=LOWER('TestNote2')");
        new InsertQuery("ToDoList", new String[]{"UserID", "Title"}, new String[]{"101", "TestNote2"}).GetQueryString();
        new DeleteQuery("ToDoList", "UserID=101 AND Title='TestNote2'");

        ResultSet ToDoListID = new SelectQuery("ToDoList", "ID", "Title='TestNote2'").fetchAll();
        ArrayList<Integer> IDs = new ArrayList<>();
        while (ToDoListID.next()) {
            IDs.add(ToDoListID.getInt(1));
        }

        for (int i : IDs) {
            new DeleteQuery("ToDoListShare", "UserID='102' AND ToDoListID='" + i + "'");
            new InsertQuery("ToDoListShare", new String[]{"UserID", "ToDoListID"}, new String[]{"102", Integer.toString(i)});
        }

        for (int i : IDs) {
            String selectedID = new SelectQuery("ToDoListShare", "ID", "ToDoListID='" + i + "'").fetch();
            Assert.assertEquals(1, new DeleteQuery("ToDoListShare", "ID = " + selectedID + "").Count());
            Assert.assertNull(new SelectQuery("ToDoListShare", "ID", "ToDoListID='" + i + "'").fetch());
        }
    }
}
