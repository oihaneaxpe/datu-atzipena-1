/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Ikaslea;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author idoia
 */
public class Orm0 {

    private static Configuration config = new Configuration();
    private static SessionFactory faktoria = config.configure().buildSessionFactory();
    private static Session saioa;

    public Orm0() {
       
    }

    public ObservableList<Ikaslea> datuakKargatu() {

        ObservableList<Ikaslea> data = FXCollections.observableArrayList();

        try {
            saioa = faktoria.openSession();
            for (Iterator<Ikaslea> itIkasleak = saioa.createQuery("FROM Ikaslea").iterate(); itIkasleak.hasNext();) {
                Ikaslea ik = itIkasleak.next();
                data.add(ik);
                System.out.println(ik.getZenbakia() + "\t" + ik.getIzena() + "\t" + ik.getAbizena1());
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            if (saioa != null) {
                saioa.close();
            }
            return data;
        }
    }

    public boolean gehitu(Ikaslea ika) {

        boolean emaitza = false;
        Transaction tx = null;
int ikasleZenb;
        
        try {
            saioa = faktoria.openSession();
            tx = saioa.beginTransaction();
            ikasleZenb = (int)saioa.save(ika);
            tx.commit();
            System.out.println(ikasleZenb + ". ikaslea ondo gorde da datubasean.");
            emaitza = true;

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);

        } finally {
            if (saioa != null) {
                saioa.close();
            }
            return emaitza;
        }

    }
//
//    public boolean ezabatu(Ikaslea ika) {
//
//        String sql = "DELETE FROM Ikasleak WHERE zenbakia = ?";
//
//        try (Connection conn = konektatu();
//                PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, ika.getZenbakia());
//            pstmt.executeUpdate();
//            System.out.println(ika.getZenbakia() + " ikaslea datu-basetik ezabatu da.");
//            return true;
//        } catch (SQLException e) {
//            return false;
//        }        
//    }
//
//    public boolean aldatu( int zein, String zutabea,String balioBerria) {
//
//        String sql = "UPDATE Ikasleak SET izena = ? WHERE zenbakia = ?";
//
//        try (Connection conn = konektatu();
//                PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, balioBerria);
//            pstmt.setInt(2, zein);
//            pstmt.executeUpdate();
//            System.out.println(zein + " ikaslearen izena  aldatu egin da datu-basean.");
//            return true;
//        } catch (SQLException e) {
//            return false;
//        }
//    }
//
//    public boolean zerrendaGuztiaGorde(ObservableList<Ikaslea> data) {
//        Connection konekzioa = null;
//        PreparedStatement updateSententzia = null;
//        ResultSet rs = null;
//        String urla = "";
//
//        String sql = "UPDATE Ikasleak SET "
//                + "izena = ?,"
//                + "abizena1 = ?,"
//                + "abizena2 = ?,"
//                + "microsoftKontua = ?"
//                + "WHERE zenbakia = ?";
//
//        try {
//            if (dbMota.equals("sqlite")) {
//                urla = "jdbc:sqlite:" + dba;
//                konekzioa = DriverManager.getConnection(urla);
//
//            } else if (dbMota.equals("mysql")) {
//                urla = "jdbc:mysql://" + zerbidorea + "/" + dba;
//                konekzioa = DriverManager.getConnection(urla, erabiltzailea, pasahitza);
//
//            }
//            //konekzioa.setAutoCommit(false);
//            updateSententzia = konekzioa.prepareStatement(sql);
//
//            for (int i = 0; i < data.size(); i++) {
//                updateSententzia.setInt(5, data.get(i).getZenbakia());
//                updateSententzia.setString(1, data.get(i).getIzena());
//                updateSententzia.setString(2, data.get(i).getAbizena1());
//                updateSententzia.setString(3, data.get(i).getAbizena2());
//                updateSententzia.setString(4, data.get(i).getMicrosoftKontua());
//                int erregistroAldatuak = updateSententzia.executeUpdate();
//                if (erregistroAldatuak == 0) {
//                    sql = "INSERT INTO Ikasleak VALUES(?,?,?,?,?)";
////                     sql = "INSERT INTO Ikasleak(zenbakia) VALUES(?)";
////                     sql = "INSERT  Ikasleak VALUES(2)";
//                    PreparedStatement insertSententzia = konekzioa.prepareStatement(sql);
//                    insertSententzia.setInt(1, data.get(i).getZenbakia());
//                    insertSententzia.setString(2, data.get(i).getIzena());
//                    insertSententzia.setString(3, data.get(i).getAbizena1());
//                    insertSententzia.setString(4, data.get(i).getAbizena2());
//                    insertSententzia.setString(5, data.get(i).getMicrosoftKontua());
//                    insertSententzia.executeUpdate();
//
//                }
//                //  konekzioa.commit();
//            }
//
//        } catch (SQLException ex) {
//            if (ex.getErrorCode() == 1049) {
//                System.out.println("Datu-base ezezaguna");
//            } else {
//                System.out.println(ex.getMessage());
//            }
//
//        } finally {
//            try {
//                if (updateSententzia != null) {
//                    updateSententzia.close();
//                }
//            } catch (SQLException sqle) {
//            }
//        }
//        return true;
//    }
}
