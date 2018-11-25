
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * http://www.sqlitetutorial.net/sqlite-java/create-database/
 */
public class SQLite {

    public static void connekt() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:dbJavatik4.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static Connection connect(String db) {

        String url = "";

        url = "jdbc:sqlite:" + db;

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createNewDatabase(String fileName) {

        Connection conn = connect(fileName);
        try {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException ex) {
            System.out.println("error");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Errorea konexioa ixterakoan.");
                }
            }
        }
    }

    public static void createNewTable(String database, String table) {

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + table + "(\n"
                + "	zenbakia integer PRIMARY KEY,\n"
                + "	izena text,\n"
                + "	abizena1 text,\n"
                + "	abizena2 text,\n"
                + "	microsoftKontua text)\n";

        try ( //try-with-resources
                Connection conn = connect(database);
                Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println(table + " taula sortua.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert(String db, String taula) {
        String sql = "INSERT INTO " + taula + "(zenbakia,izena) VALUES(?,?)";

        try (Connection conn = connect(db);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, 1);
            pstmt.setString(2, "Jorgito");
            pstmt.executeUpdate();

            pstmt.setInt(1, 2);
            pstmt.setString(2, "Juanito");
            pstmt.executeUpdate();

            pstmt.setInt(1, 3);
            pstmt.setString(2, "Jaimito");
            pstmt.executeUpdate();

            System.out.println("Erregistro batzuk txertatu dira.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void select(String db, String taula) {
        String sql = "SELECT * FROM " + taula;

        try (Connection conn = connect(db);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("zenbakia") + "\t"
                        + rs.getString("izena"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void selectWithParams(String db, String taula, int zenbakia) {
        String sql = "SELECT * FROM " + taula + " WHERE zenbakia= ?";
        Connection conn = null;
        try {
            conn = connect(db);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, zenbakia);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("izena"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Errorea konexioa ixterakoan.");
                }
            }
        }
    }

    public static void main(String[] args) {

        String dbIzena = "datubaseak\\dbKoldo.db";
        String taulaIzena = "taula1";
        createNewDatabase(dbIzena);
   //     createNewTable(dbIzena, taulaIzena);
  //      insertAnitza(dbIzena, taulaIzena);
//        select("Ikasleak.sqlite", "Ikasleak");
//        selectWithParams("Ikasleak.sqlite", "Ikasleak", 2);
    }

    public static void insertAnitza(String db, String taula) {
        String sql = "INSERT INTO " + taula + "(zenbakia,izena) VALUES(?,?)";

        try (Connection conn = connect(db);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 1; i < 10000; i++) {
                pstmt.setInt(1, i);
                pstmt.setString(2, "xx"+i);
                pstmt.executeUpdate();
            }

            System.out.println("Erregistro batzuk txertatu dira.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
