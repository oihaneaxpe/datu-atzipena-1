/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Jdbc;
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
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.util.converter.IntegerStringConverter;

import model.Ikaslea;

public class ProgramaNagusia1 extends Application {

    ObservableList<Ikaslea> data = FXCollections.observableArrayList();

    Jdbc jdbc;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle(
                "UD2 PROIEKTUA");
        stage.setWidth(
                1200);
        stage.setHeight(
                700);
        stage.setScene(parametroenEskenaSortu(stage));

        stage.show();

    }

    @Override

    public void stop()
            throws Exception {
        jdbc.zerrendaGuztiaGorde(data);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Scene parametroenEskenaSortu(Stage stage) {
        GridPane gridpane0;

        Label label0;
        Label label1;
        Label label2;
        Label label3;
        Label label4;
        Label label5;
        Label label6;
        Label label7;

        TextField textField0;
        TextField textField1;
        TextField textField2;
        TextField textField3;
        TextField textField4;
        TextField textField5;
        TextField textField6;
        TextField textField7;

        Button button0;

        Scene scene0 = new Scene(new Group(), 1200, 700);
        scene0.getStylesheets().add("estiloa.css");
        
        label0 = new Label("UD2. DatuBaseErlazionalak.");
        label0.getStyleClass().add("izenburua");
        
        label1 = new Label("DB mota: ");
        label2 = new Label("DB izena: ");
        label3 = new Label("Zerbitzaria: ");
        label4 = new Label("Taula Izena: ");
        label5 = new Label("Erabiltzailea: ");
        label6 = new Label("Pasahitza");
        //label7= new Label();;
        
        GridPane.setHalignment(label1, HPos.RIGHT);
        GridPane.setHalignment(label2, HPos.RIGHT);
        GridPane.setHalignment(label3, HPos.RIGHT);
        GridPane.setHalignment(label4, HPos.RIGHT);
        GridPane.setHalignment(label5, HPos.RIGHT);
        GridPane.setHalignment(label6, HPos.RIGHT);

        textField1 = new TextField("sqlite");
        textField1.setPromptText("ni textField0a naiz");
        textField2 = new TextField("ikasleak.sqlite");
        textField3 = new TextField("127.0.0.1");
        textField4 = new TextField("Ikasleak");
        textField5 = new TextField("root");
        textField6 = new TextField("root");

        button0 = new Button("Jarraitu");
        button0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                jdbc = new Jdbc(textField1.getText(),
                        textField2.getText(),
                        textField3.getText(),
                        textField4.getText(),
                        textField5.getText(),
                        textField6.getText()
                );
                data = jdbc.datuakKargatu();
                datuTaularenEskenaSortu(stage);
            }
        }
        );

        GridPane gridPane0 = new GridPane();
        gridPane0.setVgap(20);
        GridPane.setConstraints(label0, 0, 0);
        GridPane.setConstraints(label1, 0, 1);
        GridPane.setConstraints(textField1, 1, 1);
        GridPane.setConstraints(label2, 0, 2);
        GridPane.setConstraints(textField2, 1, 2);
        GridPane.setConstraints(label3, 0, 3);
        GridPane.setConstraints(textField3, 1, 3);
        GridPane.setConstraints(label4, 0, 4);
        GridPane.setConstraints(textField4, 1, 4);
        GridPane.setConstraints(label5, 0, 5);
        GridPane.setConstraints(textField5, 1, 5);
        GridPane.setConstraints(label6, 0, 6);
        GridPane.setConstraints(textField6, 1, 6);

        GridPane.setConstraints(button0, 1, 8);
        gridPane0.getChildren().addAll(label0, label1, textField1,
                label2, textField2,
                label3, textField3,
                label4, textField4,
                label5, textField5,
                label6, textField6,
                button0);
        ((Group) scene0.getRoot()).getChildren().addAll(gridPane0);

        return scene0;
    }

    public Scene datuTaularenEskenaSortu(Stage stage) {
        Scene scene1;
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
        zenbakiaCol.setCellFactory(TextFieldTableCell.<Ikaslea, Integer>forTableColumn(new IntegerStringConverter()));
//        zenbakiaCol.setOnEditCommit(
//                (TableColumn.CellEditEvent<Ikaslea, Integer> t) -> {
//                    ((Ikaslea) t.getTableView().getItems().get(
//                            t.getTablePosition().getRow())).setZenbakia(t.getNewValue());
//                });

  
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
                    System.out.println("Izena aldatu duzu: "+t.getOldValue()+ " => "+t.getNewValue());
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
                    System.out.println("Abizena aldatu duzu: "+t.getOldValue()+ " => "+t.getNewValue());
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
                Ikaslea p = new Ikaslea(
                        Integer.parseInt(addZenbakia.getText()),
                        addIzena.getText(),
                        addAbizena1.getText(),
                        "Longarai",
                        "xx@xx");
                data.add(p);
                
                // if (table.getPlaceholder() != NULL) 
                //       table.setItems(data);
                addZenbakia.clear();
                addIzena.clear();
                addAbizena1.clear();
//                addAbizena2.clear();
//                addMicrosoftKontua.clear();
            }
        }
        );

        removeButton = new Button("Ezabatu");

        removeButton.setOnAction(
                (ActionEvent re) -> {
                    Ikaslea ikaslea = table.getSelectionModel().getSelectedItem();
                    data.remove(ikaslea);
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

        stage.setScene(scene1);

        return scene1;
    }
}
