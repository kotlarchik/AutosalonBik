package kotlarchik.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kotlarchik.dao.DAO;
import kotlarchik.model.*;
import kotlarchik.service.ServiceMarka;
import kotlarchik.service.ServiceModel;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.IOException;

public class ControllerAddNewAuto {
    @FXML
    private TextField txtCode;

    @FXML
    private ComboBox<Model> comboModel;

    @FXML
    private TextField txtCost;

    @FXML
    private ComboBox<PTS> comboPTS;

    @FXML
    private ComboBox<Equipment> comboEquip;

    @FXML
    private Label status;

    @FXML
    private TextField txtColor;

    @FXML
    void pressAdd(ActionEvent event) {

    }

    @FXML
    void pressAddEquip(ActionEvent event) {

    }

    public void pressAddPTS(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPTS.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Создание ПТС");
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon/666.png")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void pressAddColor(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddColor.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Создание нового цвета");
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon/666.png")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void pressAddModel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddModel.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Создание модели");
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon/666.png")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void pressSetImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите путь к изображению");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Все файлы", "*.png", "*.jpg")
        );

        File selectedFile = fileChooser.showOpenDialog(new Stage());
    }

    @FXML
    void initialize(){

    }

    private void initData(){

    }
}