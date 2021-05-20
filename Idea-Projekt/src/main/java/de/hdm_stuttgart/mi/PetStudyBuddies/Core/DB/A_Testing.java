package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class A_Testing {
    public static void main(String args[]) {
        Connection c;
        Statement stmt = null;

        try {
            c = new SQLiteJDBC().getConnection();
            if (c == null) {
                System.out.println("Connection is null");
            } else {
                System.out.println("Connection works");
            }
            /* Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            c = DriverManager.getConnection("jdbc:sqlite:psb.sqlite", config.toProperties()); */
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            String sql = "INSERT INTO Note (UserID, Title, Content) " +
                    "VALUES (2, 'Hellu', 'testi testi');";
            int count = stmt.executeUpdate(sql);

            sql = "INSERT INTO Note (UserID, Title, Content) " +
                    "VALUES (2, 'another one', 'wohoo');";
            count += stmt.executeUpdate(sql);

            // count += new Query(sql).WriteData();

            System.out.println(count + " rows inserted");

            stmt.close();
            c.commit();
            // c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}
