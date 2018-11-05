/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.JSONFitxaKudeaketaObjectModelParserrarekin;
import controller.JSONFitxaKudeaketaStreamParserrarekin;
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
import controller.TestuFitxaKudeaketa;
import controller.XMLFitxaKudeaketa;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import javax.xml.parsers.ParserConfigurationException;

import model.Ikaslea;
import org.xml.sax.SAXException;

/**
 *
 * @aut author idoia
 */
public class ProgramaNagusia extends Application {

    ObservableList<Ikaslea> data = FXCollections.observableArrayList();
    String fitxategia = "";
    char fitxategiMota = 'T';//'T' edo 'X'

    Scene scene0;
    VBox vbox0;
    Label label0;
    Button button0;

    Scene scene;
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

    @Override
    public void start(Stage stage) throws Exception {

        scene0 = new Scene(new Group(),1200,700);
        label0 = new Label("UD1. Fitxategien kudeaketa.");
        button0 = new Button("Sakatu fitxategia aukeratzeko");
        button0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    //System.out.println("Name: " + file.getName());
                    fitxategia = file.getPath();
                }
                label = new Label("Ikasleak");
                        label.setFont(new Font("Arial", 20));
                
                table = new TableView<>();
                table.setEditable(true);
                
                

                //   if (data == null) {
                if ("".equals(fitxategia)) {//ez dute fitxategirik aukeratu
                    final Label mezua = new Label("Ez duzu fitxategirik aukeratu");
                    table.setPlaceholder(mezua);
                } else {

                    try {
                        if (fitxategia.endsWith(".csv")) {
                            data = TestuFitxaKudeaketa.datuakMemorianKargatu(fitxategia);
                        } else if (fitxategia.endsWith(".xml")) {
                            data = XMLFitxaKudeaketa.datuakMemorianKargatu(fitxategia);
                        } else if (fitxategia.endsWith(".json")) {
                            if (file.length() < 3000) {
                                data = JSONFitxaKudeaketaObjectModelParserrarekin.datuakMemorianKargatu(fitxategia);
                            } else {
                                data = JSONFitxaKudeaketaStreamParserrarekin.datuakMemorianKargatu(fitxategia);
                            }
                        } else {
                            System.out.println("csv, xml eta json motako fitxatgiak  bakarrik irakurri ditzaket.");
                            //data = FXCollections.observableArrayList();
                        }
                        table.setItems(data);
                        

                        TableColumn<Ikaslea, Integer> zenbakiaCol
                                = new TableColumn<>("Zenbakia");
                        zenbakiaCol.setMinWidth(100);
                        zenbakiaCol.setCellValueFactory(
                                new PropertyValueFactory<>("Zenbakia"));
                        zenbakiaCol.setCellFactory(TextFieldTableCell.<Ikaslea, Integer>forTableColumn(new IntegerStringConverter()));
                        zenbakiaCol.setOnEditCommit(
                                (TableColumn.CellEditEvent<Ikaslea, Integer> t) -> {
                                    ((Ikaslea) t.getTableView().getItems().get(
                                            t.getTablePosition().getRow())).setZenbakia(t.getNewValue());
                                });

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
                                = new TableColumn<>("Lehen Abizena");
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
                        microsoftKontuaCol.setMinWidth(100);
                        microsoftKontuaCol.setCellValueFactory(
                                new PropertyValueFactory<>("MicrosoftKontua"));
                        microsoftKontuaCol.setCellFactory(TextFieldTableCell.<Ikaslea>forTableColumn());
                        microsoftKontuaCol.setOnEditCommit(
                                (TableColumn.CellEditEvent<Ikaslea, String> t) -> {
                                    ((Ikaslea) t.getTableView().getItems().get(
                                            t.getTablePosition().getRow())).setMicrosoftKontua(t.getNewValue());
                                });

                        table.getColumns().addAll(zenbakiaCol, izenaCol, abizena1Col, abizena2Col, microsoftKontuaCol);
                    } catch (IOException ioe) {
                        System.out.println("Error");
                    } catch (ParserConfigurationException ex) {
                        Logger.getLogger(ProgramaNagusia.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SAXException ex) {
                        Logger.getLogger(ProgramaNagusia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                addZenbakia = new TextField();
                // addZenbakia.setMaxWidth(izenaCol.getPrefWidth());
                addZenbakia.setPromptText("zenbakia");

                addIzena = new TextField();
                //addIzena.setMaxWidth(izenaCol.getPrefWidth());
                addIzena.setPromptText("izen");

                addAbizena1 = new TextField();
                //addAbizena1.setMaxWidth(izenaCol.getPrefWidth());
                addAbizena1.setPromptText("Abizena1");

                addAbizena2 = new TextField();
                addAbizena2 = new TextField();
                // addAbizena2.setMaxWidth(izenaCol.getPrefWidth());
                addAbizena2.setPromptText("Abizena2");

                addMicrosoftKontua = new TextField();
                addMicrosoftKontua = new TextField();
                //addMicrosoftKontua.setMaxWidth(izenaCol.getPrefWidth());
                addMicrosoftKontua.setPromptText("Microsoft Kontua");

                addButton = new Button("Gehitu");
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
                        // if (table.getPlaceholder() != NULL) 
                        //       table.setItems(data);
                        addZenbakia.clear();
                        addIzena.clear();
                        addAbizena1.clear();
                        addAbizena2.clear();
                        addMicrosoftKontua.clear();
                    }
                });

                removeButton = new Button("Ezabatu");
                removeButton.setOnAction((ActionEvent re) -> {
                    Ikaslea ikaslea = table.getSelectionModel().getSelectedItem();
                    data.remove(ikaslea);
                });

                exitButton = new Button("Irten");
                exitButton.setOnAction((ActionEvent re) -> {
//                       stop();
                    Platform.exit();
                });

                hb = new HBox();
                hb.getChildren().addAll(addZenbakia, addIzena, addAbizena1, addAbizena2, addMicrosoftKontua, addButton, removeButton, exitButton);
                hb.setSpacing(3);

                vbox = new VBox();
                vbox.setSpacing(5);
                vbox.setPadding(new Insets(10, 0, 0, 10));
                vbox.getChildren().addAll(label, table, hb);

                scene = new Scene(new Group(),1200,700);
                ((Group) scene.getRoot()).getChildren().addAll(vbox);

                stage.setScene(scene);
            }
        });

        vbox0 = new VBox();
        vbox0.setSpacing(5);
        vbox0.setPadding(new Insets(10, 0, 0, 10));
        vbox0.getChildren().addAll(label0, button0);
        ((Group) scene0.getRoot()).getChildren().addAll(vbox0);

        stage.setTitle("UD1 PROIEKTUA");
        stage.setWidth(1200);
        stage.setHeight(700);
        stage.setScene(scene0);
        stage.show();

    }

    @Override

    public void stop()
            throws Exception {

        Stage stage2 = new Stage();
        FileChooser fileChooser2 = new FileChooser();
        fileChooser2.setTitle("Non gorde nahi dituzu datuak?");
        File file2 = fileChooser2.showSaveDialog(stage2);
        if (file2 != null) {
            fitxategia = file2.getPath();
        }
        try {
            if (fitxategia.endsWith(".csv")) {
                TestuFitxaKudeaketa.datuakFitxategianGorde(data, fitxategia);
            } else if (fitxategia.endsWith(".xml")) {
                XMLFitxaKudeaketa.datuakFitxategianGorde(data, fitxategia);
            } else if (fitxategia.endsWith(".json")) {
                if (data.size() < 25) {
                    JSONFitxaKudeaketaObjectModelParserrarekin.datuakFitxategianGorde(data, fitxategia);
                } else {
                    JSONFitxaKudeaketaStreamParserrarekin.datuakFitxategianGorde(data, fitxategia);
                }
            } else {
                System.out.println("Bakarrik ezagutzen ditut csv eta xml formatuak. Ez dira aldaketak gorde.");
            }
        } catch (Exception ex) {
            Logger.getLogger(ProgramaNagusia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
