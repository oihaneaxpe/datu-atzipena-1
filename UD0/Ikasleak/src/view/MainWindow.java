/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import controller.GestionListaEnMemoria;
import javafx.event.EventHandler;
import javafx.util.converter.IntegerStringConverter;

import model.Ikaslea;

/**
 *
 * @author idoia
 */
public class MainWindow extends Application {

    private final TableView<Ikaslea> table = new TableView<>();

    final HBox hb = new HBox();

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());

        ObservableList<Ikaslea> data = GestionListaEnMemoria.cargarDatos();

        stage.setTitle("Datuen Taula");
//        stage.setWidth(700);
//        stage.setHeight(100);
        final Label label = new Label("Ikasleak");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn<Ikaslea, Integer> zenbakiaCol
                = new TableColumn<>("Zenbakia");
        zenbakiaCol.setMinWidth(100);
        zenbakiaCol.setCellValueFactory(
                new PropertyValueFactory<>("Zenbakia"));
        zenbakiaCol.setCellFactory(TextFieldTableCell.<Ikaslea,Integer>forTableColumn(new IntegerStringConverter()));
        zenbakiaCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Ikaslea, Integer> t) -> {
                    ((Ikaslea) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setZenbakia(t.getNewValue());
                });

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
                });

        TableColumn<Ikaslea, String> abizena1Col
                = new TableColumn<>("Abizena1");
        abizena1Col.setMinWidth(100);
        abizena1Col.setCellValueFactory(
                new PropertyValueFactory<>("Abizena1"));
        abizena1Col.setCellFactory(TextFieldTableCell.<Ikaslea>forTableColumn());
        abizena1Col.setOnEditCommit(
                (TableColumn.CellEditEvent<Ikaslea, String> t) -> {
                    ((Ikaslea) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setAbizena1(t.getNewValue());
                });

        TableColumn<Ikaslea, String> abizena2Col
                = new TableColumn<>("Abizena2");
        abizena2Col.setMinWidth(100);
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
        microsoftKontuaCol.setMinWidth(200);
        microsoftKontuaCol.setCellValueFactory(
                new PropertyValueFactory<>("MicrosoftKontua"));
        microsoftKontuaCol.setCellFactory(TextFieldTableCell.<Ikaslea>forTableColumn());
        microsoftKontuaCol.setOnEditCommit(
                (TableColumn.CellEditEvent<Ikaslea, String> t) -> {
                    ((Ikaslea) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setMicrosoftKontua(t.getNewValue());
                });

        table.setItems(data);
        table.getColumns().addAll(zenbakiaCol, izenaCol, abizena1Col, abizena2Col, microsoftKontuaCol);

        final TextField addZenbakia = new TextField();
        addZenbakia.setMaxWidth(izenaCol.getPrefWidth());
        addZenbakia.setPromptText("zenbakia");

        final TextField addIzena = new TextField();
        addIzena.setMaxWidth(izenaCol.getPrefWidth());
        addIzena.setPromptText("izen");

        final TextField addAbizena1 = new TextField();
        addAbizena1.setMaxWidth(izenaCol.getPrefWidth());
        addAbizena1.setPromptText("Abizena1");

        final TextField addAbizena2 = new TextField();
        addAbizena2.setMaxWidth(izenaCol.getPrefWidth());
        addAbizena2.setPromptText("Abizena2");

        final TextField addMicrosoftKontua = new TextField();
        addMicrosoftKontua.setMaxWidth(izenaCol.getPrefWidth());
        addMicrosoftKontua.setPromptText("Microsoft Kontua");

        final Button addButton = new Button("Gehitu");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Ikaslea p = new Ikaslea(
                        Integer.parseInt(addZenbakia.getText()),
                        addIzena.getText(),
                        addAbizena1.getText(),
                        addAbizena2.getText(),
                        addMicrosoftKontua.getText());
                data.add(p);

                addZenbakia.clear();
                addIzena.clear();
                addAbizena1.clear();
                addAbizena2.clear();
                addMicrosoftKontua.clear();
            }
        });

        final Button removeButton = new Button("Ezabatu");
        removeButton.setOnAction((ActionEvent e) -> {
            Ikaslea ikaslea = table.getSelectionModel().getSelectedItem();
            data.remove(ikaslea);
        });

        hb.getChildren().addAll(addZenbakia, addIzena, addAbizena1, addAbizena2, addMicrosoftKontua, addButton, removeButton);
        hb.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop()
            throws Exception {
        System.out.println("Agur.");

    }
}
