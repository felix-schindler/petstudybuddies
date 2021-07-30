package de.hdm_stuttgart.mi.PetStudyBuddies.CoreTest.UtilsTest;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Objects;

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

    @Test
    public void testInArray() {
        Assert.assertTrue(Utils.inArray(new int[]{1, 2, 3}, 1));
        Assert.assertTrue(Utils.inArray(new int[]{1, 2, 3}, 2));
        Assert.assertTrue(Utils.inArray(new int[]{1, 2, 3}, 3));
        Assert.assertFalse(Utils.inArray(new int[]{1, 2, 3}, 5));
        Assert.assertFalse(Utils.inArray(new int[]{1, 2, 3}, 6));
    }

    @Test
    public void testParseDate() {
        Assert.assertNull(Utils.parseDate("YYYY-MM-DD"));
        Assert.assertNull(Utils.parseDate(""));
        Assert.assertNull(Utils.parseDate("abc"));

        Assert.assertNotNull(Utils.parseDate("2018-12-06"));
        Assert.assertNotNull(Utils.parseDate(String.valueOf(LocalDate.parse("2021-12-06").toEpochDay())));
        Assert.assertNotNull(Utils.parseDate(String.valueOf(System.currentTimeMillis())));
    }

    @Test
    public void testSHA1() {
        Assert.assertEquals(40, Objects.requireNonNull(Utils.sha1("a")).length());
        Assert.assertEquals(40, Objects.requireNonNull(Utils.sha1("a9993e364706816aba3e25717850c26c9cd0d89d")).length());
        Assert.assertEquals(40, Objects.requireNonNull(Utils.sha1("abcdefghijklmnopqrstuvwxyz")).length());
        Assert.assertEquals(40, Objects.requireNonNull(Utils.sha1("here is something")).length());
        Assert.assertEquals(40, Objects.requireNonNull(Utils.sha1("123456")).length());
    }
}
