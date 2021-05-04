package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import junit.framework.TestCase;
import org.junit.Test;

public class SelectQueryTest extends TestCase {

    @Test
    public void testSelectSimple(){
       // SelectQuery query = new SelectQuery("User","-","-","-","-");
       // SelectQuery query2
       // String test = "SELECT - FROM User WHERE - GROUP BY - ORDER BY -";
       // assertEquals(test.setQueryString("String"),"SELECT - FROM User WHERE - GROUP BY - ORDER BY -");
       // new SelectQuery("User", "EMail", "Username='test' AND Password='" + Utils.sha1("test")+ "';", null, null).fetch());



    }
    @Test
    public void testSelectAll(){

    }
    @Test
    public void testSelectWhere(){

    }
    @Test
    public void testSelectOrderBy(){

    }
    @Test
    public void testSelectGroupBy(){

    }
    @Test
    public void testSelectComplex(){

    }
    @Test
    public void testSelectNull(){
        SelectQuery query = new SelectQuery(null,"-","-","-","-");
        assertEquals(query,null);
    }

}
