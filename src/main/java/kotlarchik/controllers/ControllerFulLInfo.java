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
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

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

    @FXML
    void pressDel(ActionEvent event) {}

    @FXML
    void pressUp(ActionEvent event) {}

    @SneakyThrows
    private void copyImage(File source, File dest){
        Files.copy(source.toPath(),dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }


    public void setData(Instancemodel instancemodel){
        this.instancemodel = instancemodel;
        txtMarka.setText(instancemodel.getModel().getMarka().getName());
        txtModel.setText(instancemodel.getModel().getName());
        txtCost.setText(String.format("%.3f руб.", instancemodel.getCost()));
        imageView.setImage(new Image(instancemodel.getImage()));
    }
}
