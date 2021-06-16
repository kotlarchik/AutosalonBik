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
import kotlarchik.service.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

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
    TextField txtImage;

    ObservableList<Model> listModel = FXCollections.observableArrayList();
    ObservableList<PTS> listPTS = FXCollections.observableArrayList();
    ObservableList<Equipment> listEquipment = FXCollections.observableArrayList();

    @FXML
    void pressAdd(ActionEvent event) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        DAO<Instancemodel, Integer> instancemodelIntegerDAO = new ServiceInstanceModel(factory);

        Instancemodel instancemodel = new Instancemodel();
        if (txtCode.getText().isEmpty()){
            status.setText("Код комплектации пусто");
        } else if (txtColor.getText().isEmpty()){
            status.setText("Цвет пусто");
        } else if (comboModel.getValue() == null){
            status.setText("Модель пусто");
        } else if (txtCost.getText().isEmpty()){
            status.setText("Стоимость пусто");
        } else if (comboPTS.getValue() == null){
            status.setText("ПТС пусто");
        } else if (txtImage.getText().isEmpty()){
            status.setText("Фотография пусто");
        } else if (comboEquip.getValue() == null){
            status.setText("Комплектации пусто");
        } else {
            instancemodel.setCode(txtCode.getText());
            instancemodel.setColor(txtColor.getText());
            instancemodel.setModel(comboModel.getValue());
            instancemodel.setCost(Double.valueOf(txtCost.getText()));
            instancemodel.setPts(comboPTS.getValue());
            instancemodel.setImage("image/"+txtImage.getText());
            instancemodel.setEquipment(comboEquip.getValue());
            instancemodelIntegerDAO.create(instancemodel);
            status.setText("Автомобиль добавлен");
        }

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

    public void pressSetImage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите путь к изображению");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Все файлы", "*.png", "*.jpg")
        );

        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null){
            txtImage.setText(selectedFile.getName());
            File source = new File(selectedFile.getAbsolutePath());
            File dest = new File("./src/main/resources/image/" + txtImage.getText());
            Files.copy(source.toPath(),dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

            txtImage.setDisable(true);
        }
    }

    @FXML
    void initialize(){
        initData();
    }

    private void initData(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        DAO<Model, Integer> modelIntegerDAO = new ServiceModel(factory);
        listModel.addAll(modelIntegerDAO.readAll());
        comboModel.setItems(listModel);

        DAO<PTS, Integer> ptsIntegerDAO = new ServicePTS(factory);
        listPTS.addAll(ptsIntegerDAO.readAll());
        comboPTS.setItems(listPTS);

        DAO<Equipment, Integer> equipmentIntegerDAO = new ServiceEquipment(factory);
        listEquipment.addAll(equipmentIntegerDAO.readAll());
        comboEquip.setItems(listEquipment);
    }
}