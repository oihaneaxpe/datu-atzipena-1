/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Ikaslea;

/**
 *
 * @author idoia
 */
public class Jdbc {

    String dbMota = "";
    String dba = "";
    String zerbidorea = "";
    String taula = "";

    String erabiltzailea = "";
    String pasahitza = "";

    public Jdbc(String dbMota, String dba,
            String zerbidorea, String taula,
            String erabiltzailea, String pasahitza) {
        this.dbMota = dbMota;
        this.dba = dba;
        this.zerbidorea = zerbidorea;
        this.taula = taula;

        this.erabiltzailea = erabiltzailea;
        this.pasahitza = pasahitza;
    }

    public Connection konektatu() {
        Connection konekzioa = null;
        String urla = "";
        try {
            if (dbMota.equals("sqlite")) {
                urla = "jdbc:sqlite:" + dba;
                konekzioa = DriverManager.getConnection(urla);

            } else if (dbMota.equals("mysql")) {
                urla = "jdbc:mysql://" + zerbidorea + "/" + dba;
                konekzioa = DriverManager.getConnection(urla, erabiltzailea, pasahitza);

            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1049) {
                System.out.println("Datu-base ezezaguna");
            } else {
                System.out.println(ex.getMessage());
            }
        } finally {
            return konekzioa;
        }
    }

    public ObservableList<Ikaslea> datuakKargatu() {

        ObservableList<Ikaslea> data = FXCollections.observableArrayList();

        Statement sententzia = null;
        ResultSet rs = null;
        Connection konekzioa = konektatu();
        try {
            sententzia = konekzioa.createStatement();
            rs = sententzia.executeQuery("SELECT * FROM " + taula);

            while (rs.next()) {
                int zenbakia = rs.getInt("zenbakia");
                String izena = rs.getString("izena");
                String abizena1 = rs.getString("abizena1");
                String abizena2 = rs.getString("abizena2");
                String microsoftKontua = rs.getString("microsoftKontua");
                Ikaslea ikasle = new Ikaslea(zenbakia, izena, abizena1, abizena2, microsoftKontua);
                data.add(ikasle);
            }
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        } finally {
            try {
                if (sententzia != null) {
                    sententzia.close();
                }
            } catch (SQLException sqle) {
            }
            return data;
        }

    }

    public boolean gehitu(Ikaslea ika) {
        String sql = "INSERT INTO Ikasleak(zenbakia,izena,abizena1,"
                + "abizena2,microsoftKontua) VALUES(?,?,?,?,?)";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ika.getZenbakia());
            pstmt.setString(2, ika.getIzena());
            pstmt.setString(3, ika.getAbizena1());
            pstmt.setString(4, ika.getAbizena2());
            pstmt.setString(5, ika.getMicrosoftKontua());
            pstmt.executeUpdate();

            System.out.println("Erregistroa ondo txertatu da datu-basean.");

            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public boolean ezabatu(Ikaslea ika) {

        String sql = "DELETE FROM Ikasleak WHERE zenbakia = ?";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ika.getZenbakia());
            pstmt.executeUpdate();
            System.out.println(ika.getZenbakia() + " ikaslea datu-basetik ezabatu da.");
            return true;
        } catch (SQLException e) {
            return false;
        }        
    }

    public boolean aldatu( int zein, String zutabea,String balioBerria) {

        String sql = "UPDATE Ikasleak SET izena = ? WHERE zenbakia = ?";

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, balioBerria);
            pstmt.setInt(2, zein);
            pstmt.executeUpdate();
            System.out.println(zein + " ikaslearen izena  aldatu egin da datu-basean.");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean zerrendaGuztiaGorde(ObservableList<Ikaslea> data) {
        Connection konekzioa = null;
        PreparedStatement updateSententzia = null;
        ResultSet rs = null;
        String urla = "";

        String sql = "UPDATE Ikasleak SET "
                + "izena = ?,"
                + "abizena1 = ?,"
                + "abizena2 = ?,"
                + "microsoftKontua = ?"
                + "WHERE zenbakia = ?";

        try {
            if (dbMota.equals("sqlite")) {
                urla = "jdbc:sqlite:" + dba;
                konekzioa = DriverManager.getConnection(urla);

            } else if (dbMota.equals("mysql")) {
                urla = "jdbc:mysql://" + zerbidorea + "/" + dba;
                konekzioa = DriverManager.getConnection(urla, erabiltzailea, pasahitza);

            }
            //konekzioa.setAutoCommit(false);
            updateSententzia = konekzioa.prepareStatement(sql);

            for (int i = 0; i < data.size(); i++) {
                updateSententzia.setInt(5, data.get(i).getZenbakia());
                updateSententzia.setString(1, data.get(i).getIzena());
                updateSententzia.setString(2, data.get(i).getAbizena1());
                updateSententzia.setString(3, data.get(i).getAbizena2());
                updateSententzia.setString(4, data.get(i).getMicrosoftKontua());
                int erregistroAldatuak = updateSententzia.executeUpdate();
                if (erregistroAldatuak == 0) {
                    sql = "INSERT INTO Ikasleak VALUES(?,?,?,?,?)";
//                     sql = "INSERT INTO Ikasleak(zenbakia) VALUES(?)";
//                     sql = "INSERT  Ikasleak VALUES(2)";
                    PreparedStatement insertSententzia = konekzioa.prepareStatement(sql);
                    insertSententzia.setInt(1, data.get(i).getZenbakia());
                    insertSententzia.setString(2, data.get(i).getIzena());
                    insertSententzia.setString(3, data.get(i).getAbizena1());
                    insertSententzia.setString(4, data.get(i).getAbizena2());
                    insertSententzia.setString(5, data.get(i).getMicrosoftKontua());
                    insertSententzia.executeUpdate();

                }
                //  konekzioa.commit();
            }

        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1049) {
                System.out.println("Datu-base ezezaguna");
            } else {
                System.out.println(ex.getMessage());
            }

        } finally {
            try {
                if (updateSententzia != null) {
                    updateSententzia.close();
                }
            } catch (SQLException sqle) {
            }
        }
        return true;
    }
}
