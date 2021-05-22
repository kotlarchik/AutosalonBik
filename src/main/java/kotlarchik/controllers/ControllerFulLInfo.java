package kotlarchik.controllers;

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

    // Говнокод
    @FXML
    void imageClick() throws IOException {
//        FileChooser chooser = new FileChooser();
//        File path = chooser.showOpenDialog(new Stage());
//        File file = new File(path.getAbsolutePath());
//        BufferedImage bufferedImage = ImageIO.read(file);
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
