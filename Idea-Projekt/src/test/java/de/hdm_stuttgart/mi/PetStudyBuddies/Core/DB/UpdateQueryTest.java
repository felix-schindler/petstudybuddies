package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import org.junit.Assert;
import org.junit.Test;

public class UpdateQueryTest {
    @Test
    public void testUpdateSimple() {
        Assert.assertEquals("UPDATE User SET EMail = 'mail@example.com';", new UpdateQuery("User", "EMail", "mail@example.com", null).GetQueryString());
        Assert.assertEquals("UPDATE Note SET Title = 'Fragen an Kriha';", new UpdateQuery("Note", "Title", "Fragen an Kriha", null).GetQueryString());
        Assert.assertEquals("UPDATE User SET EMail = 'fs146@hdm-stuttgart.de';", new UpdateQuery("User", "EMail", "fs146@hdm-stuttgart.de", null).GetQueryString());
    }

    @Test
    public void testUpdateWhere() {
        Assert.assertEquals("UPDATE Pet SET Name = 'Inge' WHERE UserID=1;", new UpdateQuery("Pet", "Name", "Inge", "UserID=1").GetQueryString());
        Assert.assertEquals("UPDATE User SET EMail = 'fs146@hdm-stuttgart.de' WHERE ID=1;", new UpdateQuery("User", "EMail", "fs146@hdm-stuttgart.de", "ID=1").GetQueryString());
        Assert.assertEquals("UPDATE Note SET Title = 'Hier könnte Ihr Titel stehen' WHERE ID=1;", new UpdateQuery("Note", "Title", "Hier könnte Ihr Titel stehen", "ID=1").GetQueryString());
    }

    @Test
    public void testUpdateNull(){
        Assert.assertEquals("UPDATE User SET EMail = NULL;", new UpdateQuery("User", "EMail", null, null).GetQueryString());
        Assert.assertEquals("UPDATE Note SET Title = NULL;", new UpdateQuery("Note", "Title", null, null).GetQueryString());
        Assert.assertEquals("UPDATE Note SET Title = NULL WHERE ID=1;", new UpdateQuery("Note", "Title", null, "ID=1").GetQueryString());
    }
}
