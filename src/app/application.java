/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import com.sun.org.apache.bcel.internal.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Rafan
 */
public class application extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Cars Shop");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icons8-car-52.png")));
        stage.setMinHeight(500);
        stage.setMaxHeight(500);
        stage.setMinWidth(935);
        stage.setMaxWidth(935);
        stage.show();
    }
    
}
