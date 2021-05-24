package kotlarchik.controllers;

import com.sun.glass.ui.CommonDialogs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kotlarchik.dao.DAO;
import kotlarchik.model.Instancemodel;
import kotlarchik.model.Marka;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import kotlarchik.service.ServiceInstanceModel;
import kotlarchik.service.ServiceMarka;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ControllerFulLInfo {
    @FXML
    private ImageView imageView;

    @FXML
    private TextField txtMarka;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtCost;

    @FXML
    private Button hide;

    @FXML
    private ComboBox<String> combo;

    private Instancemodel instancemodel;

    private ObservableList<Marka> markas = FXCollections.observableArrayList();
    private ObservableList<Instancemodel> instancemodels = FXCollections.observableArrayList();

    @FXML
    void initialize(){
        initData();
        initComboBox();
    }

    private void initData(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        DAO<Instancemodel, Integer> instancemodelDAO = new ServiceInstanceModel(factory);

        DAO<Marka, Integer> markaDAO = new ServiceMarka(factory);
        markas.addAll(markaDAO.readAll());
    }

    private void initComboBox() {
        ObservableList<String> comboss = FXCollections.observableArrayList();

        comboss.add("Все марки");
            for (Marka marka: markas){
                comboss.add(marka.getName());
            }
        combo.setValue(comboss.get(0));

        combo.setItems(comboss);
    }

    @FXML
    void imageClick() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(new Stage());
        String namePhoto = selectedFile.getName();

        File source = new File(selectedFile.getAbsolutePath());
        File dest = new File("./src/main/resources/image/" + namePhoto);

        if (selectedFile != null){
            imageView.setImage(new Image(selectedFile.toURI().toURL().openStream()));
            copyImage(source, dest);
        } else {
            imageView.setImage(new Image(instancemodel.getImage()));
        }
    }

    @SneakyThrows
    private void copyImage(File source, File dest){
        Files.copy(source.toPath(),dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }


    @FXML
    void pressDel(ActionEvent event) {
//        SessionFactory factory = new Configuration().configure().buildSessionFactory();
//        DAO<Instancemodel, Integer> instancemodelDAO = new ServiceInstanceModel(factory);
//        instancemodelDAO.delete(instancemodel);
        hide.getScene().getWindow().hide();
    }

    @FXML
    void pressUp(ActionEvent event) {}


    public void setData(Instancemodel instancemodel){
        this.instancemodel = instancemodel;
        txtMarka.setText(instancemodel.getModel().getMarka().getName());
        txtModel.setText(instancemodel.getModel().getName());
        txtCost.setText(String.format("%.3f руб.", instancemodel.getCost()));
        imageView.setImage(new Image(instancemodel.getImage()));
    }
}
