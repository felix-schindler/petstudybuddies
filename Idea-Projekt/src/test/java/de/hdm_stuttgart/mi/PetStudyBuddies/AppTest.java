package de.hdm_stuttgart.mi.PetStudyBuddies;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Auth;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Dummy test method
     */
    @Test
    public void testApp() {
        Assert.assertTrue( true );
    }

    /**
     * Test the Auth.login Method
     */
    @Test
    public void testLogin() {
        Assert.assertNull(Auth.login("test", "test"));
        Assert.assertNotNull(Auth.login("fs146@hdm-stuttgart.de", "test"));
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
}
