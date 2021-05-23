package kotlarchik.controllers;

import com.sun.glass.ui.CommonDialogs;
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
import kotlarchik.model.Instancemodel;
import kotlarchik.model.Marka;

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
    private ComboBox<Marka> combo;

    private Instancemodel instancemodel;

    private String path;

    // Говнокод(Уже рабочий, но не имеет полной логики);
    @FXML
    void imageClick() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());

//        Если путь не пустой;
        if (selectedFile != null){
//            Задаём новое изображение(берём адресс(toURI().toURL()) фотографии и открываем поток(.openStream()));
            imageView.setImage(new Image(selectedFile.toURI().toURL().openStream()));
//            В противном случае(Если мы не выбрали путь) просто верни нам уже существуещее изображение из Instancemodel;
        } else {
            imageView.setImage(new Image(instancemodel.getImage()));
        }

//        Здесь должен быть код для сохранения фотографии в /image/"название фотки", но мне лень это реализовывать :(;
//        P.S. Говнокодил AlexLifeless;
//        К слову с вас 3 hundred bucks и дизайн;
    }

    @FXML
    void pressDel(ActionEvent event) {

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
