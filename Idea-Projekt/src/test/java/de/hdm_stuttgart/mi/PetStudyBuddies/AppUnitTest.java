package de.hdm_stuttgart.mi.PetStudyBuddies;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Auth;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Unit test for simple App.
 */
public class AppUnitTest {
    /**
     * Test utils valid mail
     */
    @Test
    public void testValidEMail() {
        Assert.assertTrue(Utils.verifyMail("fs146@hdm-stuttgart.de"));
        Assert.assertTrue(Utils.verifyMail("kb136@hdm-stuttgart.de"));
        Assert.assertTrue(Utils.verifyMail("abc@abc.de"));
        Assert.assertFalse(Utils.verifyMail("abc@abc"));
        Assert.assertFalse(Utils.verifyMail("abc.de"));
        Assert.assertFalse(Utils.verifyMail("eexamble@.de"));
    }

    /**
     * Test the Auth.register Method
     */
    @Test
    public void testRegister() {
        Assert.assertTrue(Auth.register("fs146@hdm-stuttgart.de", "felix", "test"));
        Assert.assertTrue(Auth.register("guenther.oettinger@parlament.eu", "guenther", "12345"));
        Assert.assertTrue(Auth.register("kriha@hdm-stuttgart.de", "kriha", "r6D84=xt*UCvpJBa"));
        Assert.assertTrue(Auth.register("mb365@hdm-stuttgart.de", "xX_Lover_Xx", "MeinNeuesSichersPasswort"));
    }

    /**
     * Test the Auth.login Method
     * TODO Macht es sinn mit der Datenbank zu testen?!
     */
    @Test
    public void testLogin() {
        Assert.assertNull(Auth.login("test", "test"));
        Assert.assertNull(Auth.login("fs146@hdm-stuttgart.de", "hehe"));
    }
}
