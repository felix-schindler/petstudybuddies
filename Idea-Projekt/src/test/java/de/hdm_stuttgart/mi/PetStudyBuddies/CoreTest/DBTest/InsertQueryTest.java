package de.hdm_stuttgart.mi.PetStudyBuddies.CoreTest.DBTest;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.DeleteQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.InsertQuery;
import org.junit.Assert;
import org.junit.Test;

public class InsertQueryTest {
    @Test
    public void testInsertSimple() {
        Assert.assertEquals("INSERT INTO User (Username, EMail, Password) VALUES ('TestUser100' , 'test100@example.com' , '" + Utils.sha1("test100") + "');", new InsertQuery("User", new String[]{"Username", "EMail", "Password"}, new String[]{"TestUser100", "test100@example.com", Utils.sha1("test100")}, false).GetQueryString());
        Assert.assertEquals("INSERT INTO User (Username, EMail, Password) VALUES ('TestUser101' , 'test101@example.com' , '" + Utils.sha1("test101") + "');", new InsertQuery("User", new String[]{"Username", "EMail", "Password"}, new String[]{"TestUser101", "test101@example.com", Utils.sha1("test101")}, false).GetQueryString());
        Assert.assertEquals("INSERT INTO User (Username, EMail, Password) VALUES ('TestUser102' , 'test102@example.com' , '" + Utils.sha1("test102") + "');", new InsertQuery("User", new String[]{"Username", "EMail", "Password"}, new String[]{"TestUser102", "test102@example.com", Utils.sha1("test102")}, false).GetQueryString());
    }

    @Test
    public void testInsert() {
        Assert.assertEquals(1, new InsertQuery("User", new String[]{"Username", "EMail", "Password"}, new String[]{"TestUser555", "test555@example.com", Utils.sha1("password")}).Count());
        Assert.assertEquals(1, new InsertQuery("User", new String[]{"Username", "EMail", "Password"}, new String[]{"TestUser666", "test666@example.com", Utils.sha1("password")}).Count());
        new DeleteQuery("User", "Username='TestUser555'");
        new DeleteQuery("User", "Username='TestUser666'");
        Assert.assertEquals("", new InsertQuery(null, null, null, false).GetQueryString());
    }
}
