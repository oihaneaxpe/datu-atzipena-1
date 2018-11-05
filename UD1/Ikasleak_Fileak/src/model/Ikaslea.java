/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author idoia
 */

public class Ikaslea {
        
    private int zenbakia;
    private String izena;
    private String abizena1;
    private String abizena2;
    private String microsoftKontua;
    
    public Ikaslea(int zenbakia, String izena, String abizena1, String abizena2, String microsoftKontua) {
        this.zenbakia = zenbakia;
        this.izena = izena;
        this.abizena1 = abizena1;
        this.abizena2 = abizena2;
        this.microsoftKontua = microsoftKontua;
    }

    public int getZenbakia() {
        return zenbakia;
    }

    public void setZenbakia(int zenbakia) {
        this.zenbakia = zenbakia;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public String getAbizena1() {
        return abizena1;
    }

    public void setAbizena1(String abizena1) {
        this.abizena1 = abizena1;
    }

    public String getAbizena2() {
        return abizena2;
    }

    public void setAbizena2(String abizena2) {
        this.abizena2 = abizena2;
    }

    public String getMicrosoftKontua() {
        return microsoftKontua;
    }

    public void setMicrosoftKontua(String microsoftKontua) {
        this.microsoftKontua = microsoftKontua;
    }

}