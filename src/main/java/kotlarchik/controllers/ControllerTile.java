package kotlarchik.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kotlarchik.model.Instancemodel;
import kotlarchik.model.Marka;
import kotlarchik.model.Model;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class ControllerTile {
    @FXML
    private ImageView imageView;

    @FXML
    private Label labelMarka;

    @FXML
    private Label labelModel;

    @FXML
    private Label labelCost;

    @FXML
    private Label code;

    @FXML
    private VBox vBox;

    private Instancemodel instancemodel;

    @FXML
    void initialize(){
        visibleTile();
    }

    public void initData(Instancemodel instancemodel){
        this.instancemodel = instancemodel;
        code.setText("Комплектация:" + instancemodel.getCode());
        imageView.setImage(new Image(instancemodel.getImage()));
        labelCost.setText(String.format("%.3f руб.", instancemodel.getCost()));
        labelMarka.setText(instancemodel.getModel().getMarka().getName());
        labelModel.setText(String.valueOf(instancemodel.getModel()));
    }

    @FXML
    public void click(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FullInfo.fxml"));
        AnchorPane anchorPane = loader.load();
        ControllerFulLInfo controllerFulLInfo = loader.getController();
        controllerFulLInfo.setData(instancemodel);

        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Подробная информация об автомобиле");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon/666.png")));
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.show();
    }

    private void visibleTile(){
        vBox.hoverProperty().addListener((observableValue, aBoolean, t1) -> {
            if (t1 == true){
                vBox.setStyle("-fx-border-color: #38ff00; -fx-border-radius: 40; -fx-border-width: 3");
            } else {
                vBox.setStyle("-fx-border-color: #ff6bbc; -fx-border-radius: 40; -fx-border-width: 3");
            }
        });
    }
}
