package kotlarchik.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAddNewAuto {
    @FXML
    private TextField txtCode;

    @FXML
    private ComboBox<?> comboMarka;

    @FXML
    private ComboBox<?> comboModel;

    @FXML
    private TextField txtCost;

    @FXML
    private ComboBox<?> comboPTS;


    public void pressAdd(ActionEvent event) {
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

    public void pressAddMarka(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddMarka.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Создание марки");
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
}
