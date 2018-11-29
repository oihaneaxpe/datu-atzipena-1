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
public class Jdbc0 {

    static String zerbidorea = "localhost";
    static String dba = "IkasleenDatuBasea";
    static String taula = "Ikasleak";

    static String erabiltzailea = "root";
    static String pasahitza = "root";

    public static ObservableList<Ikaslea> cargarDatos() {

        ObservableList<Ikaslea> data = FXCollections.observableArrayList();

        Connection konekzioa = null;
        Statement sententzia = null;
        ResultSet rs = null;

        try {
            String urla = "jdbc:mysql://" + zerbidorea + "/" + dba;
            konekzioa = DriverManager.getConnection(urla, erabiltzailea, pasahitza);

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
            if (ex.getErrorCode() == 1049) {
                System.out.println("Datu-base ezezaguna");
            } else {
                System.out.println(ex.getMessage());
            }

        } finally {
            try {
                if (sententzia != null) {
                    sententzia.close();
                }
            } catch (SQLException sqle) {
            }
        }
        return data;
    }

    public static boolean datuakGorde(ObservableList<Ikaslea> data) {
        Connection konekzioa = null;
        PreparedStatement updateSententzia = null;
        ResultSet rs = null;
        String urla = "jdbc:mysql://" + zerbidorea + "/" + dba;

        String sql = "UPDATE Ikasleak SET "
                + "izena = ?,"
                + "abizena1 = ?,"
                + "abizena2 = ?,"
                + "microsoftKontua = ?"
                + "WHERE zenbakia = ?";

        try {
            konekzioa = DriverManager.getConnection(urla, erabiltzailea, pasahitza);

            konekzioa.setAutoCommit(false);
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
               konekzioa.commit();
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
