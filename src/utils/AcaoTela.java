/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author Rafan
 */
public class AcaoTela {
     public void alterClick(Label lbl, Map<String,Label> mapLabels){
        lbl.getStylesheets().clear();
        lbl.getStylesheets().add(getClass().getResource("/css/labelClick.css").toExternalForm());
        for(String nome : mapLabels.keySet()){
            Label label = mapLabels.get(nome);
            if(!label.getText().equals(lbl.getText())){
                label.getStylesheets().clear();
                label.getStylesheets().add(getClass().getResource("/css/labelDesClick.css").toExternalForm());   
            }
        }
    }
     
         public void alterView(Pane pnlPrincipal ,String name) throws IOException{
         pnlPrincipal.getChildren().setAll((Pane)FXMLLoader.load(getClass().getResource("/view/"+name+".fxml")));
    }
}
