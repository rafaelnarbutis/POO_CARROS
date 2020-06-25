/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import utils.AcaoTela;
import utils.ValorLabels;

/**
 * FXML Controller class
 *
 * @author Rafan
 */
public class HomeController implements Initializable {

    @FXML
    private Pane pnlPrincipal;
    @FXML
    private Label lblCarros;
    @FXML
    private Label lblClientes;
    @FXML
    public Label lblCompraCarros;
    @FXML
    private Label lblVendeCarro;
    @FXML
    private Label lblDevolucao;
    @FXML
    private Label lblCaixa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO
        
        Map<String, Label> mapLabels = new HashMap<>();
        mapLabels.put(lblCaixa.getText(), lblCaixa);
        mapLabels.put(lblCarros.getText(), lblCarros);
        mapLabels.put(lblClientes.getText(), lblClientes);
        mapLabels.put(lblCompraCarros.getText(), lblCompraCarros);
        mapLabels.put(lblDevolucao.getText() ,lblDevolucao);
        mapLabels.put(lblVendeCarro.getText(), lblVendeCarro);
        new ValorLabels().setMapLabels(mapLabels);
        try {
            lblCarros(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
      
    }    

    @FXML
    private void lblCarros(MouseEvent event) throws IOException {
        new AcaoTela().alterClick(lblCarros, ValorLabels.getMaptLabels());
        new AcaoTela().alterView(pnlPrincipal, "Carros");
    }

    @FXML
    private void lblCliente(MouseEvent event) throws IOException {
        new AcaoTela().alterClick(lblClientes, ValorLabels.getMaptLabels());
        new AcaoTela().alterView(pnlPrincipal, "Cliente");
    }

    @FXML
    public void lblComprarCarro(MouseEvent event) throws IOException {
        new AcaoTela().alterClick(lblCompraCarros, ValorLabels.getMaptLabels());
        new AcaoTela().alterView(pnlPrincipal, "ComprarCarro");
    }

    @FXML
    private void lblVenderCarro(MouseEvent event) throws IOException {
        new AcaoTela().alterClick(lblVendeCarro, ValorLabels.getMaptLabels());
        new AcaoTela().alterView(pnlPrincipal, "VendaRetiradaCarro");
    }

    @FXML
    private void lblDevolucaoCarro(MouseEvent event) throws IOException {
        new AcaoTela().alterClick(lblDevolucao, ValorLabels.getMaptLabels());
        new AcaoTela().alterView(pnlPrincipal, "DevolucaoCarro");
    }

    @FXML
    private void lblCaixa(MouseEvent event) throws IOException {
        new AcaoTela().alterClick(lblCaixa, ValorLabels.getMaptLabels());
        new AcaoTela().alterView(pnlPrincipal, "Caixa");
    }
    
}
