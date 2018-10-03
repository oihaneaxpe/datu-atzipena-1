/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Ikaslea;

/**
 *
 * @author idoia
 */
public class GestionListaEnMemoria {

    public static ObservableList<Ikaslea> cargarDatos() {

        return FXCollections.observableArrayList(
                new Ikaslea(1, "Julen", "Aboitiz", "Bermeo\nsolo", "dam2-jaboitizb"),
                new Ikaslea(2, "Aimar", "Aizpuru", "Eguia", "dam2-aaizpurue"),
                new Ikaslea(3, "Jon", "Akarregi", "Letona", "dam2-jakarregi"),
                new Ikaslea(4, "Julen", "Alzaa", "Pedro de Oliveira", "dam2-jalzaaped"),
                new Ikaslea(5, "Eider", "Anduaga", "Garcia", "dam2-eanduagag"),
                new Ikaslea(6, "Jon", "Aretxabaleta", "Zelaia", "dam2-jaretxaba"),
                new Ikaslea(7, "Oihane", "Axpe", "Telleriarte", "dam2-oaxpetell"),
                new Ikaslea(8, "Jon", "Barrutieta", "Lorca", "dam2-jbarrutie"),
                new Ikaslea(9, "Jon Mikel", "Beitia", "Echeita", "dam2-jbeitiaec"),
                new Ikaslea(10, "Josu", "Bilbao", "Iraegi", "dam2-jbilbaoir"),
                new Ikaslea(11, "Xabier", "Bolumburu", "Casado", "dam2-xbolumbur"),
                new Ikaslea(12, "Gontzal", "de Carlos", "Garcia", "dam2-gdecarlos"),
                new Ikaslea(13, "Xabier", "Diaz", "de Pedro", "dam2-xdiazdepe"),
                new Ikaslea(14, "Mikel", "Elizondo", "Nogales", "dam2-melizondo"),
                new Ikaslea(15, "Koldo", "Etxaniz", "Orbea", "dam2-ketxanizo"),
                new Ikaslea(16, "Ibon", "Felipe", "Mora", "dam2-ifelipemo"),
                new Ikaslea(17, "Sara", "Garrido", "Carranza", "dam2-sgarridoc"),
                new Ikaslea(18, "Andoitz", "Horrillo", "Valenciano", "dam2-ahorrillo"),
                new Ikaslea(19, "Aritz", "Iza", "Ugarte", "dam2-aizaugart"),
                new Ikaslea(20, "Gaizka", "Izagirre", "López", "dam2-gizagirre"),
                new Ikaslea(21, "Aitor", "Magallanes", "Lozano", "dam2-amagallan"),
                new Ikaslea(22, "Eider", "Maiztegui", "Cestafe", "dam2-emaiztegu"),
                new Ikaslea(23, "Lucia", "Meruelo", "Garcia", "dam2-lmeruelog")
        );
    }
}
