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
import kotlarchik.service.ServiceInstanceModel;
import kotlarchik.service.ServiceMarka;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

    // NE TROJJJ!
    @FXML
    void imageClick() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите путь к картинке");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Все файлы", "*.png", "*.jpg")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null){
            imageView.setImage(new Image(selectedFile.toURI().toURL().openStream()));

        } else {
            imageView.setImage(new Image(instancemodel.getImage()));
        }
    }

    @FXML
    void pressDel(ActionEvent event) {
//        SessionFactory factory = new Configuration().configure().buildSessionFactory();
//        DAO<Instancemodel, Integer> instancemodelDAO = new ServiceInstanceModel(factory);
//        instancemodelDAO.delete(instancemodel);

        hide.getScene().getWindow().hide();
    }

    @FXML
    void pressUp(ActionEvent event) {

    }

    public void setData(Instancemodel instancemodel){
        this.instancemodel = instancemodel;
        txtMarka.setText(instancemodel.getModel().getMarka().getName());
        txtModel.setText(instancemodel.getModel().getName());
        txtCost.setText(String.format("%.3f руб.", instancemodel.getCost()));
        imageView.setImage(new Image(instancemodel.getImage()));
    }
}
