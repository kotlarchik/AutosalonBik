package kotlarchik.controllers;

import com.sun.glass.ui.CommonDialogs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kotlarchik.dao.DAO;
import kotlarchik.model.*;
import kotlarchik.service.*;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ControllerFulLInfo {
    public Button buttonPTS;
    @FXML
    private ImageView imageView;

    @FXML
    private TextField txtMarka;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtCode;

    @FXML
    private TextArea txtEquipmentInfo;

    @FXML
    private TextField txtEquipment;

    @FXML
    private TextField txtTransmission;

    @FXML
    private TextField txtCountGears;

    @FXML
    private ComboBox<Marka> comboMark;

    @FXML
    private ComboBox<Equipment> comboEquipment;

    @FXML
    private ComboBox<Transmission> comboTransmission;

    @FXML
    private ComboBox<Instancetransmission> comboCountTrans;

    private Instancemodel instancemodel;

    private final ObservableList<Marka> markas = FXCollections.observableArrayList();
    private final ObservableList<Equipment>  equipments = FXCollections.observableArrayList();
    private final ObservableList<Transmission> transmissions = FXCollections.observableArrayList();
    private final ObservableList<Instancetransmission> instancetransmissions = FXCollections.observableArrayList();


    @FXML
    void initialize(){
        initData();
        initComboBox();
    }

    private void initData(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        DAO<Marka, Integer> markaDAO = new ServiceMarka(factory);
        markas.addAll(markaDAO.readAll());

        DAO<Equipment, Integer> equipmentIntegerDAO = new ServiceEquipment(factory);
        equipments.addAll(equipmentIntegerDAO.readAll());

        DAO<Transmission, Integer> transmissionIntegerDAO = new ServiceTransmission(factory);
        transmissions.addAll(transmissionIntegerDAO.readAll());

        DAO<Instancetransmission, Integer> instancetransmissionIntegerDAO = new ServiceInstanceTransmission(factory);
        instancetransmissions.addAll(instancetransmissionIntegerDAO.readAll());
    }

    private void initComboBox() {
        comboMark.setItems(markas);
        comboEquipment.setItems(equipments);
        comboTransmission.setItems(transmissions);
    }

    @FXML
    void imageClick() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите путь к изображению");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Все файлы", "*.png", "*.jpg")
        );
        String namePhoto = null;

        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null){
            namePhoto = selectedFile.getName();
        }

        if (selectedFile != null){
            imageView.setImage(new Image(selectedFile.toURI().toURL().openStream()));
            File source = new File(selectedFile.getAbsolutePath());
            File dest = new File("./src/main/resources/image/" + namePhoto);
            copyImage(source, dest);
        } else {
            imageView.setImage(new Image(instancemodel.getImage()));
        }
    }

    @SneakyThrows
    private void copyImage(File source, File dest){
        Files.copy(source.toPath(),dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    private void clearScreen(){}

    @FXML
    void pressUp(ActionEvent event) {}


    public void setData(Instancemodel instancemodel){
        this.instancemodel = instancemodel;
        txtMarka.setText(instancemodel.getModel().getMarka().getName());
        txtModel.setText(instancemodel.getModel().getName());
        txtCost.setText(String.format("%.3f руб.", instancemodel.getCost()));
        imageView.setImage(new Image(instancemodel.getImage()));
    }

    @FXML
    public void pressPTS(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/PTS.fxml"));
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Данные ПТС");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon/666.png")));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
