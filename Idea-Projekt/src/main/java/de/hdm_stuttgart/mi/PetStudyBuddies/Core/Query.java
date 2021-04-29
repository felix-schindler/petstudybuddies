package de.hdm_stuttgart.mi.PetStudyBuddies.Core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Statement;
import java.util.HashMap;

public class Query extends SQLiteJDBC {
    private final static Logger log = LogManager.getLogger(Query.class);

    private StringBuilder queryString;
    public Statement stmt = null;

    public Query(String sql) {
        queryString.append(sql);

        try {
            stmt = getConnection().createStatement();
        } catch (Exception e) {
            log.error(e);
        }

        log.error("Query failed");
    }

    /**
     * @return a single field (SELECT)
     */
    public String fetch() {
        try {
            stmt.executeUpdate(queryString.toString());
            stmt.close();
            log.debug("Fetch worked");
        } catch (Exception e) {
            log.error("Fetch failed; QueryString: " + queryString);
        }
        return "";
    }

    /**
     * TODO
     * @return
     */
    public HashMap<String, String> fetchAll() {
        return new HashMap<String, String>();
    }
}
