package kotlarchik.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kotlarchik.dao.DAO;
import kotlarchik.model.Instancemodel;
import kotlarchik.model.Marka;
import kotlarchik.model.Options;
import kotlarchik.service.ServiceInstanceModel;
import kotlarchik.service.ServiceMarka;
import kotlarchik.service.ServiceOptions;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class ControllerMain {
    @FXML
    private AnchorPane pane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TilePane tilePane;

    @FXML
    private TextField search;

    @FXML
    private ComboBox<String> comboBox;

    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();
    private final ObservableList<Marka> comboMarkaList = FXCollections.observableArrayList();
    private final ObservableList<Instancemodel> instanceModelList = FXCollections.observableArrayList();
    private final ObservableList<Options> options = FXCollections.observableArrayList();

    @FXML
    void initialize() throws IOException {
        setData();
        initComboBox();
        initCells(instanceModelList);
        rubberWindow();
        search();
    }

    private void initCells(ObservableList<Instancemodel> instanceModelList) throws IOException {
        tilePane.getChildren().clear();

        tilePane.setPadding(new Insets(15));
        tilePane.setHgap(15);
        tilePane.setVgap(15);


        for (Instancemodel instancemodel: instanceModelList){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Tile.fxml"));
            VBox vBox = loader.load();
            ControllerTile controllerTile = loader.getController();

            controllerTile.initData(instancemodel);
            tilePane.getChildren().add(vBox);
        }
    }

    // Резиновое окошечко
    private void rubberWindow(){
        scrollPane.widthProperty().addListener((observableValue, oldValue, newValue) -> {
            tilePane.setPrefWidth(newValue.doubleValue());
        });
    }

    private void setData(){
        DAO<Instancemodel, Integer> instanceModelDAO = new ServiceInstanceModel(factory);
        instanceModelList.addAll(instanceModelDAO.readAll());

        DAO<Marka, Integer> markaDAO = new ServiceMarka(factory);
        comboMarkaList.addAll(markaDAO.readAll());

        DAO<Options,Integer> optionsDAO = new ServiceOptions(factory);
        options.addAll(optionsDAO.readAll());
    }

    private void search(){
        search.setOnKeyReleased(keyEvent -> {
            ObservableList<Instancemodel> instancemodels = FXCollections.observableArrayList();
            for (Instancemodel instancemodel: instanceModelList){
               if (instancemodel.getModel().getName().toLowerCase().contains(search.getText().toLowerCase())){
                    instancemodels.add(instancemodel);
               }
           }
            try {
                initCells(instancemodels);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initComboBox() throws IOException {
        ObservableList<String> combo = FXCollections.observableArrayList();

        combo.add("Все марки");
            for (Marka marka: comboMarkaList) {
                combo.add(marka.getName());
            }
            comboBox.setValue(combo.get(0));

            comboBox.setItems(combo);

            comboBox.valueProperty().addListener((observableValue, oldValue, newValue) -> {
                ObservableList<Instancemodel> sortedMarka = FXCollections.observableArrayList();

                for (Instancemodel instancemodel : instanceModelList) {
                    if (comboBox.getValue().equals("Все марки")){
                        sortedMarka.add(instancemodel);
                    }
                    if (instancemodel.getModel().getMarka().getName().contains(comboBox.getValue())) {
                        sortedMarka.add(instancemodel);
                    }
                }
                try {
                    initCells(sortedMarka);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    }

    public void pressNewAuto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddNewAuto.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Создание нового автомобиля");
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon/666.png")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void pressAddClient(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddNewClient.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Регистрация нового клиента");
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon/666.png")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void pressContract(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddContract.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Составление контракта");
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon/666.png")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void pressEmployee(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddEmployee.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Внесение нового сотрудника");
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon/666.png")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}