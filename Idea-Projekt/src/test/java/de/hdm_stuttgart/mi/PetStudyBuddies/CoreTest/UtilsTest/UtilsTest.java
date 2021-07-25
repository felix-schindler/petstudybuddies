package de.hdm_stuttgart.mi.PetStudyBuddies.CoreTest.UtilsTest;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {
    /**
     * Test utils valid mail
     */
    @Test
    public void testValidEMail() {
        Assert.assertTrue(Utils.verifyMail("fs146@hdm-stuttgart.de"));
        Assert.assertTrue(Utils.verifyMail("kb136@hdm-stuttgart.de"));
        Assert.assertTrue(Utils.verifyMail("mb365@hdm-stuttgart.de"));
        Assert.assertTrue(Utils.verifyMail("some_mail@outlook.com"));
        Assert.assertTrue(Utils.verifyMail("webmaster@schindlerfelix.de"));
        Assert.assertTrue(Utils.verifyMail("mail@example.com"));
        Assert.assertTrue(Utils.verifyMail("support@apple.com"));
        Assert.assertTrue(Utils.verifyMail("help@i.am.a.mac.user"));
        Assert.assertTrue(Utils.verifyMail("how-do-i@do-this-in.linux.bash"));
        Assert.assertTrue(Utils.verifyMail("abc@abc.abc"));
    }

    /**
     * Test utils invalid mail
     */
    @Test
    public void testInvalidEMail() {
        Assert.assertFalse(Utils.verifyMail("mail@example"));
        Assert.assertFalse(Utils.verifyMail("@example.com"));
        Assert.assertFalse(Utils.verifyMail("@assemble"));
        Assert.assertFalse(Utils.verifyMail("smooth@life"));
        Assert.assertFalse(Utils.verifyMail("assembly.is@hard"));
        Assert.assertFalse(Utils.verifyMail("tay@tay-swift"));
        Assert.assertFalse(Utils.verifyMail("fhdjska@example-com"));
        Assert.assertFalse(Utils.verifyMail("here.is@.felix"));
        Assert.assertFalse(Utils.verifyMail("sha.@de"));
        Assert.assertFalse(Utils.verifyMail("eexamble@.de"));
    }
}
