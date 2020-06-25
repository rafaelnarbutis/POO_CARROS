/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.CarroDao;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import mask.Mask;
import mask.TypeMask;
import model.Carro;
import objView.CarroView;
import utils.AcaoTela;
import utils.Alerta;
import utils.ValorLabels;

/**
 * FXML Controller class
 *
 * @author Rafan
 */
public class CarrosController implements Initializable {

    @FXML
    private JFXTextField txtMarca;
    @FXML
    private JFXTextField txtModelo;
    @FXML
    private JFXTextField txtKm;
    @FXML
    private JFXTextField txtAno;
    @FXML
    private TableView<CarroView> tblCarros;
    @FXML
    private TableColumn<CarroView, String> collMarca;
    @FXML
    private TableColumn<CarroView, String> collModelo;
    @FXML
    private TableColumn<CarroView, String> collPlaca;
    @FXML
    private TableColumn<CarroView, String> collKm;
    @FXML
    private TableColumn<CarroView, String> collTipo;
    @FXML
    private TableColumn<CarroView, Integer> collAno;
    @FXML
    private TableColumn<CarroView, String> collTanque;
    @FXML
    private TableColumn<CarroView, String> collStatus;
    @FXML
    private TableColumn<CarroView, String> collValor;
    @FXML
    private TableColumn<CarroView, String> collAluguelI;
    @FXML
    private TableColumn<CarroView, String> collAluguelF;
    @FXML
    private JFXTextField txtPlaca;
    @FXML
    private JFXTextField txtValor;
    private JFXButton btnAtualizar;
    @FXML
    private AnchorPane pnlCarro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String valida = "Q,W,E,R,T,Y,U,I,O,P,A,S,D,F,G,H,J,K,L,Ç,Z,X,C,V,B,N,M,1,2,3,4,5,6,7,8,9,0";
        Mask.maskStatic(txtPlaca, TypeMask.PLACA_CARRO, valida);
        collAno.setCellValueFactory(new PropertyValueFactory("ano"));
        collKm.setCellValueFactory(new PropertyValueFactory("km"));
        collMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        collModelo.setCellValueFactory(new PropertyValueFactory("modelo"));
        collPlaca.setCellValueFactory(new PropertyValueFactory("placa"));
        collStatus.setCellValueFactory(new PropertyValueFactory("disponivel"));
        collTanque.setCellValueFactory(new PropertyValueFactory("tanque"));
        collTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        collValor.setCellValueFactory(new PropertyValueFactory("valor"));
        collAluguelI.setCellValueFactory(new PropertyValueFactory("dataAluguelI"));
        collAluguelF.setCellValueFactory(new PropertyValueFactory("dataAluguelF"));
        popularTabela(new controller.CarroController().buscarCarros(new Carro("", "", "")));
    }

    @FXML
    private void btnBuscar(ActionEvent event) {
        Carro carro = new Carro(txtMarca.getText(), txtModelo.getText(), txtPlaca.getText());
        if (txtValor.getText().trim().length() != 0) {
            carro.setValor(Double.parseDouble(txtValor.getText()));
        }
        if (txtKm.getText().trim().length() != 0) {
            carro.setKm(Double.parseDouble(txtKm.getText()));
        }
        if (txtAno.getText().trim().length() != 0) {
            carro.setAno(Integer.valueOf(txtAno.getText()));
        }
        popularTabela(new controller.CarroController().buscarCarros(carro));
    }

    @FXML
    private void btnAtualizar(ActionEvent event) throws IOException {
        CarroView carroView = tblCarros.getSelectionModel().getSelectedItem();
        if (carroView != null) {
            new AcaoTela().alterClick(ValorLabels.getMaptLabels().get("Comprar/Atualizar Carro"), ValorLabels.getMaptLabels());
            new AcaoTela().alterView((Pane) pnlCarro, "ComprarCarro");

            ComprarCarroController.btnAtualizarStatic.setVisible(true);
            ComprarCarroController.btnComprarStatic.setVisible(false);
            ComprarCarroController.id = carroView.getId();

            ComprarCarroController.txtMarcaStatic.setText(carroView.getMarca());
            ComprarCarroController.txtModeloStatic.setText(carroView.getModelo());
            ComprarCarroController.txtKmStatic.setText(carroView.getKm());
            ComprarCarroController.txtPlacaStatic.setText(carroView.getPlaca());
            ComprarCarroController.txtAnoStatic.setText(String.valueOf(carroView.getAno()));
            ComprarCarroController.txtValorStatic.setText(carroView.getValor());
            ComprarCarroController.comboTanqueStatic.getSelectionModel().select(carroView.getTanque());
            ComprarCarroController.comboTipoStatic.getSelectionModel().select(carroView.getTipo());
        } else {
            Alerta.criarAlerta("Erro ao atualizar", "Selecione um carro na tabela para ataulizar", Alert.AlertType.INFORMATION).showAndWait();
        }
    }

    public void popularTabela(List<CarroView> lista) {
        tblCarros.setItems(FXCollections.observableArrayList(lista));
    }

    @FXML
    private void btnRemover(ActionEvent event) {
        CarroView carroView = tblCarros.getSelectionModel().getSelectedItem();
        if (carroView != null) {
            if (new controller.CarroController().excluirCarro(carroView.getId())) {
                popularTabela(new controller.CarroController().buscarCarros(new Carro("", "", "")));
                Alerta.criarAlerta("Sucesso ao excluir", "Carro excluido com sucesso!", Alert.AlertType.INFORMATION).showAndWait();
            } else {
                Alerta.criarAlerta("Erro ao excluir", "Verifique se o carro esta disponivel", Alert.AlertType.INFORMATION).showAndWait();
            }
        } else {
            Alerta.criarAlerta("Erro ao excluir", "Selecione um carro na tabela para excluir", Alert.AlertType.INFORMATION).showAndWait();
        }
    }

}
