/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Orm;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.util.converter.IntegerStringConverter;

import model.Ikaslea;

public class ProgramaNagusia extends Application {

    ObservableList<Ikaslea> data = FXCollections.observableArrayList();

    Orm orm;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle(
                "UD2 PROIEKTUA");
        stage.setWidth(
                1200);
        stage.setHeight(
                700);
        orm = new Orm();
        data = orm.datuakKargatu();
        stage.setScene(datuTaularenEskenaSortu(stage));

        stage.show();

    }

    @Override

    public void stop()
            throws Exception {
      //  jdbc.zerrendaGuztiaGorde(data);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Scene datuTaularenEskenaSortu(Stage stage) {
        Scene scene1=null;
        VBox vbox;
        Label label;
        TableView<Ikaslea> table;
        HBox hb;
        TextField addZenbakia;
        TextField addIzena;
        TextField addAbizena1;
        TextField addAbizena2;
        TextField addMicrosoftKontua;
        Button addButton;
        Button removeButton;
        Button exitButton;

        table = new TableView<>();
        table.setEditable(true);
        table.setItems(data);

        TableColumn<Ikaslea, Integer> zenbakiaCol
                = new TableColumn<>("Zenbakia");
        zenbakiaCol.setMinWidth(100);
        zenbakiaCol.setCellValueFactory(
                new PropertyValueFactory<>("Zenbakia"));
        zenbakiaCol.setEditable(false);
        zenbakiaCol.setCellFactory(TextFieldTableCell.<Ikaslea, Integer>forTableColumn(new IntegerStringConverter()));

        TableColumn<Ikaslea, String> izenaCol
                = new TableColumn<>("Izena");
        izenaCol.setMinWidth(100);
        izenaCol.setCellValueFactory(
                new PropertyValueFactory<>("Izena"));
        izenaCol.setCellFactory(TextFieldTableCell.<Ikaslea>forTableColumn());
        izenaCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Ikaslea, String> t) -> {
                    ((Ikaslea) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setIzena(t.getNewValue());
                    System.out.println("Izena aldatu duzu: " + t.getOldValue() + " => " + t.getNewValue());
        //            Orm.aldatu(((Ikaslea) t.getTableView().getItems().get(
        //                    t.getTablePosition().getRow())).getZenbakia(),"izena",t.getNewValue());
                });

        TableColumn<Ikaslea, String> abizena1Col
                = new TableColumn<>("Abizena1");
        abizena1Col.setMinWidth(125);
        abizena1Col.setCellValueFactory(
                new PropertyValueFactory<>("Abizena1"));
        abizena1Col.setCellFactory(TextFieldTableCell.<Ikaslea>forTableColumn());
        abizena1Col.setOnEditCommit(
                (TableColumn.CellEditEvent<Ikaslea, String> t) -> {
                    ((Ikaslea) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setAbizena1(t.getNewValue());
                    System.out.println("Abizena aldatu duzu: " + t.getOldValue() + " => " + t.getNewValue());
                });

        TableColumn<Ikaslea, String> abizena2Col
                = new TableColumn<>("Abizena2");
        abizena2Col.setMinWidth(125);
        abizena2Col.setCellValueFactory(
                new PropertyValueFactory<>("Abizena2"));
        abizena2Col.setCellFactory(TextFieldTableCell.<Ikaslea>forTableColumn());
        abizena2Col.setOnEditCommit(
                (TableColumn.CellEditEvent<Ikaslea, String> t) -> {
                    ((Ikaslea) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setAbizena2(t.getNewValue());
                });

        TableColumn<Ikaslea, String> microsoftKontuaCol
                = new TableColumn<>("MicrosoftKontua");
        microsoftKontuaCol.setMinWidth(125);
        microsoftKontuaCol.setCellValueFactory(
                new PropertyValueFactory<>("MicrosoftKontua"));
        microsoftKontuaCol.setCellFactory(TextFieldTableCell.<Ikaslea>forTableColumn());
        microsoftKontuaCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Ikaslea, String> t) -> {
                    ((Ikaslea) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setMicrosoftKontua(t.getNewValue());
                });

        table.getColumns().addAll(zenbakiaCol, izenaCol, abizena1Col, abizena2Col, microsoftKontuaCol);

        addZenbakia = new TextField();
        // addZenbakia.setMaxWidth(izenaCol.getPrefWidth());

        addZenbakia.setPromptText(
                "zenbakia");

        addIzena = new TextField();
        //addIzena.setMaxWidth(izenaCol.getPrefWidth());

        addIzena.setPromptText(
                "izen");

        addAbizena1 = new TextField();
        //addAbizena1.setMaxWidth(izenaCol.getPrefWidth());

        addAbizena1.setPromptText(
                "Abizena1");

        addAbizena2 = new TextField();
        addAbizena2 = new TextField();
        // addAbizena2.setMaxWidth(izenaCol.getPrefWidth());

        addAbizena2.setPromptText(
                "Abizena2");

        addMicrosoftKontua = new TextField();
        addMicrosoftKontua = new TextField();
        //addMicrosoftKontua.setMaxWidth(izenaCol.getPrefWidth());

        addMicrosoftKontua.setPromptText(
                "Microsoft Kontua");

        addButton = new Button("Gehitu");

        addButton.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e
            ) {
                try {
                    Ikaslea p = new Ikaslea(
                            Integer.parseInt(addZenbakia.getText()),
                            addIzena.getText(),
                            addAbizena1.getText(),
                            "",
                            "");//zergatik addAbizena2.getText() errorea??

                    if (orm.gehitu(p)) {//DBan gehitu
                        // if (table.getPlaceholder() != NULL) 
                        //       table.setItems(data);
                        data.add(p); //ObservableList-ean gehitu
                        addZenbakia.clear();
                        addIzena.clear();
                        addAbizena1.clear();
//                addAbizena2.clear();
//                addMicrosoftKontua.clear();}
                    } else {
                        System.out.println("Ezin izan da erregistroa gehitu.");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("egiaztatu datuak mesedez");
                }
            }
        }
        );

        removeButton = new Button("Ezabatu");

        removeButton.setOnAction(
                (ActionEvent re) -> {
                    Ikaslea ikaslea = table.getSelectionModel().getSelectedItem();
//                    if (orm.ezabatu(ikaslea)){
//                      data.remove(ikaslea);    
//                    }
                }
        );

        exitButton = new Button("Irten");

        exitButton.setOnAction(
                (ActionEvent re) -> {
//                       stop();

                    Platform.exit();
                }
        );

        hb = new HBox();

        hb.getChildren()
                .addAll(addZenbakia, addIzena, addAbizena1, addAbizena2, addMicrosoftKontua, addButton, removeButton, exitButton);
        hb.setSpacing(
                3);

        vbox = new VBox();
        label = new Label("Ikasleak");
        label.getStyleClass().add("izenburua");

        vbox.setSpacing(
                5);
        vbox.setPadding(
                new Insets(10, 0, 0, 10));
        vbox.getChildren()
                .addAll(label, table, hb);

        scene1 = new Scene(new Group(), 1200, 700);
        scene1.getStylesheets().add("estiloa.css");
        ((Group) scene1.getRoot()).getChildren().addAll(vbox);

        
        return scene1;
    }
}
