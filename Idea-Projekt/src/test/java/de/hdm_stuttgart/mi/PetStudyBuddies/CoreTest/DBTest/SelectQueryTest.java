package de.hdm_stuttgart.mi.PetStudyBuddies.CoreTest.DBTest;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class SelectQueryTest {
    @Test
    public void testSelectSimple(){
        Assert.assertEquals("SELECT ID FROM User;", new SelectQuery("User","ID",null,null,null,true).GetQueryString());
        Assert.assertEquals("SELECT Username FROM User;", new SelectQuery("User","Username",null,null,null,true).GetQueryString());

    }
    @Test
    public void testSelectAll(){
        Assert.assertEquals("SELECT * FROM User;", new SelectQuery("User","*",null,null,null,true).GetQueryString());
        Assert.assertEquals("SELECT * FROM ToDoList;", new SelectQuery("ToDoList","*",null,null,null,true).GetQueryString());
    }
    @Test
    public void testSelectWhere(){
        Assert.assertEquals("SELECT * FROM User WHERE ID=1;", new SelectQuery("User","*","ID=1",null,null,true).GetQueryString());
        Assert.assertEquals("SELECT * FROM ToDoList WHERE UserID=1;", new SelectQuery("ToDoList","*","UserID=1",null,null,true).GetQueryString());

    }
    @Test
    public void testSelectOrderBy(){
        Assert.assertEquals("SELECT * FROM User ORDER BY Email;", new SelectQuery("User","*",null,"Email",null,true).GetQueryString());
        Assert.assertEquals("SELECT * FROM ToDoList ORDER BY UserID;", new SelectQuery("ToDoList","*",null,"UserID",null,true).GetQueryString());
    }
    @Test
    public void testSelectGroupBy(){
        Assert.assertEquals("SELECT COUNT(UserID),ToDoListID FROM ToDoListShare GROUP BY ToDoListID;", new SelectQuery("ToDoListShare","COUNT(UserID),ToDoListID",null,null,"ToDoListID",true).GetQueryString());
        Assert.assertEquals("SELECT COUNT(ID),UserID FROM ToDoList GROUP BY UserID;", new SelectQuery("ToDoList","COUNT(ID),UserID",null,null,"UserID",true).GetQueryString());
    }
    @Test
    public void testSelectComplex(){
        Assert.assertEquals("SELECT COUNT(UserID),ToDoListID FROM ToDoListShare WHERE UserID=2 GROUP BY ToDoListID ORDER BY ID;", new SelectQuery("ToDoListShare","COUNT(UserID),ToDoListID","UserID=2","ID","ToDoListID",true).GetQueryString());
    }
    @Test
    public void testSelectNull(){
        Assert.assertEquals("", new SelectQuery(null,null,null,null,null,true).GetQueryString());
    }
}
