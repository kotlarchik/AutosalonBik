package kotlarchik.controllers;

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
import java.util.List;
import java.util.Set;

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
    private ComboBox<Gears> comboCountGears;

    private Instancemodel instancemodel;

    private Options option;

    private final ObservableList<Marka> markas = FXCollections.observableArrayList();
    private final ObservableList<Equipment>  equipments = FXCollections.observableArrayList();
    private final ObservableList<Transmission> transmissions = FXCollections.observableArrayList();
    private final ObservableList<Gears> gears = FXCollections.observableArrayList();
    private final ObservableList<Options> optionsList = FXCollections.observableArrayList();


    @FXML
    void initialize(){
        initData();
        initComboBox();
        initTxt();
    }

    private void initData(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        DAO<Options, Integer> optionsDao = new ServiceOptions(factory);
        optionsList.addAll(optionsDao.readAll());

        DAO<Marka, Integer> markaDAO = new ServiceMarka(factory);
        markas.addAll(markaDAO.readAll());

        DAO<Equipment, Integer> equipmentIntegerDAO = new ServiceEquipment(factory);
        equipments.addAll(equipmentIntegerDAO.readAll());

        DAO<Transmission, Integer> transmissionIntegerDAO = new ServiceTransmission(factory);
        transmissions.addAll(transmissionIntegerDAO.readAll());

        DAO<Gears, Integer> gearsIntegerDAO = new ServiceGears(factory);
        gears.addAll(gearsIntegerDAO.readAll());
    }

    private void initComboBox() {
        comboMark.setItems(markas);
        comboEquipment.setItems(equipments);
        comboTransmission.setItems(transmissions);
        comboCountGears.setItems(gears);
    }

    private String namePhoto = null;
    @FXML
    void imageClick() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите путь к изображению");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Все файлы", "*.png", "*.jpg")
        );

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

    private void clearScreen(){
        markas.clear();
        equipments.clear();
        transmissions.clear();
        gears.clear();
        optionsList.clear();
        initialize();

        txtModel.setText(instancemodel.getModel().getName());
    }

    @FXML
    void pressUp(ActionEvent event) throws IOException {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

//        Instancemodel;
        DAO<Instancemodel, Integer> instancemodelDAO = new ServiceInstanceModel(factory);
        instancemodel.setCode(txtCode.getText());
        instancemodel.setCost(Double.parseDouble(txtCost.getText()));
        if (comboEquipment.getValue() != null){
            instancemodel.setEquipment(comboEquipment.getValue());
        }

        if (namePhoto != null) {
            instancemodel.setImage("image/" + namePhoto);
        } else {
            instancemodel.setImage(instancemodel.getImage());
        }
        instancemodelDAO.update(instancemodel);


//        Mark;
        DAO<Model, Integer> modelIntegerDAO = new ServiceModel(factory);

        Model model = instancemodel.getModel();
        if (!txtModel.getText().isEmpty()){
            model.setName(txtModel.getText());
        }

        if (comboMark.getValue() != null) {
            model.setMarka(comboMark.getValue());
        }
        modelIntegerDAO.update(model);

//        Options;
        DAO<Options, Integer> optionsDAO = new ServiceOptions(factory);
        Options options = option;

        if (!txtEquipmentInfo.getText().isEmpty() && comboEquipment.getValue() != null){
            options.setName(txtEquipmentInfo.getText());
            options.setEquipment(comboEquipment.getValue());
            optionsDAO.update(options);
        } else if (comboEquipment.getValue() == null){
            options.setEquipment(option.getEquipment());
            options.setName(txtEquipmentInfo.getText());
            optionsDAO.update(options);
        }



//        Transmission;
//        DAO<Instancetransmission, Integer> instancetransmissionDAO = new ServiceInstanceTransmission(factory);
//        Instancetransmission instancetransmission = new Instancetransmission();
//        ObservableList<Instancetransmission> instancetransmissionList = FXCollections.observableArrayList();
//        instancetransmissionList.addAll(instancetransmissionDAO.readAll());
//        for (Instancetransmission instancetransmission1:instancetransmissionList) {
//            if (instancetransmission1.getTransmission().getId() ==
//                    options.getInstancetransmission()
//                            .getTransmission()
//                            .getId()){
//                instancetransmission = instancetransmission1;
//            }
//        }
//
//
//        if (comboTransmission.getValue() != null){
//            instancetransmission.setTransmission(comboTransmission.getValue());
//        } else {
//            instancetransmission.setTransmission(options.getInstancetransmission().getTransmission());
//        }
//
//        instancetransmissionDAO.update(instancetransmission);
//
//
        clearScreen();
    }

    private void initTxt(){
        comboEquipment.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue != null)
            txtEquipment.setText(oldValue.getName());
        });

        comboTransmission.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue != null)
            txtTransmission.setText(oldValue.getType());
        });

        comboCountGears.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue != null)
            txtCountGears.setText(String.valueOf(oldValue.getNumber()));
        });

        comboMark.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue != null)
            txtMarka.setText(oldValue.getName());
        });
    }

    public void setData(Instancemodel instancemodel){
        this.instancemodel = instancemodel;
        txtMarka.setText(instancemodel.getModel().getMarka().getName());
        txtModel.setText(instancemodel.getModel().getName());
        txtCost.setText(String.format("%.0f", instancemodel.getCost()));
        imageView.setImage(new Image(instancemodel.getImage()));
        txtCode.setText(instancemodel.getCode());


        for (Options options1: optionsList) {
            if (options1.getEquipment().getId() == instancemodel.getEquipment().getId()){
                txtEquipmentInfo.setText(options1.getName());
                txtTransmission.setText(options1.getInstancetransmission().getTransmission().getType());
                txtEquipment.setText(options1.getEquipment().getName());
                txtCountGears.setText(String.valueOf(options1.getInstancetransmission().getGears().getNumber()));
                this.option = options1;
            }
        }
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
