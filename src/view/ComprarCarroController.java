/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.TipoTanqueController;
import controller.TipocarroController;
import dao.TipoCarroDAO;
import dao.TipoTanqueDAO;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import mask.Mask;
import mask.TypeMask;
import model.Carro;
import model.ValidaCombo;
import model.ValidaText;
import model.abs.Valida;
import utils.Alerta;
import utils.Valor;

/**
 * FXML Controller class
 *
 * @author Rafan
 */
public class ComprarCarroController implements Initializable {

    @FXML
    private JFXTextField txtMarca;
    @FXML
    private JFXTextField txtModelo;
    @FXML
    private JFXComboBox<String> comboTipo;
    @FXML
    private JFXTextField txtKm;
    @FXML
    private JFXTextField txtAno;
    @FXML
    private JFXComboBox<String> comboTanque;
    @FXML
    private JFXTextField txtPlaca;
    @FXML
    private JFXTextField txtValor;
    @FXML
    public JFXButton btnAtualiza;
    @FXML
    public JFXButton btnComprar;
    
    public static JFXButton btnComprarStatic;
    
    public static JFXButton btnAtualizarStatic;
    
    public static JFXTextField txtMarcaStatic;
    
    public static JFXTextField txtModeloStatic;
    
    public static JFXTextField txtKmStatic;
    
    public static JFXTextField txtAnoStatic;
    
    public static JFXTextField txtPlacaStatic;
    
    public static JFXTextField txtValorStatic;
    
    public static JFXComboBox comboTipoStatic;
    
    public static JFXComboBox comboTanqueStatic;
    
    private List<Valida> listaValida = new ArrayList<>();
    public static Integer id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String valida = "Q,W,E,R,T,Y,U,I,O,P,A,S,D,F,G,H,J,K,L,Ç,Z,X,C,V,B,N,M,1,2,3,4,5,6,7,8,9,0";
        new TipocarroController().listarTipoCarros().forEach(c -> comboTipo.getItems().add(c.getDescricao()));
        new TipoTanqueController().listarTipoTaques().forEach(c -> comboTanque.getItems().add(c.getDescricao()));
        listaValida.add(new ValidaText(txtMarca, 4, "Marca"));
        listaValida.add(new ValidaText(txtModelo, 4, "Modelo"));
        listaValida.add(new ValidaText(txtPlaca, 8, "Placa"));
        listaValida.add(new ValidaText(txtValor, 7, "Valor"));
        listaValida.add(new ValidaText(txtAno, 4, "Ano"));
        listaValida.add(new ValidaCombo(comboTanque, "Tanque"));
        listaValida.add(new ValidaCombo(comboTipo, "Tipo"));
        listaValida.add(new ValidaText(txtKm, 0, "Km"));
        Mask.maskStatic(txtPlaca, TypeMask.PLACA_CARRO, valida);
        Mask.dynamicMask(txtValor, 15, "1,2,3,4,5,6,7,8,9,0", new Locale("pt", "BR"));
        Mask.maskPrivate(txtKm,new Locale("pt", "BR") ,8,"1,2,3,4,5,6,7,8,9,0");
        Mask.maskPrivate(txtAno,null,4,"1,2,3,4,5,6,7,8,9,0");
        
        btnComprarStatic = btnComprar;
        btnAtualizarStatic = btnAtualiza;
        txtAnoStatic = txtAno;
        txtKmStatic = txtKm;
        txtMarcaStatic = txtMarca;
        txtModeloStatic = txtModelo;
        txtPlacaStatic = txtPlaca;
        txtValorStatic = txtValor;
        comboTanqueStatic = comboTanque;
        comboTipoStatic = comboTipo;
        
        id = null;
   }

    @FXML
    private void btnComprar(ActionEvent event) throws SQLException {
        if (verificarErros()) {
            if(new controller.CarroController().inserircarro(popularObj())){
                limparCampos();
                Alerta.criarAlerta("Sucesso ao comprar carro", "Carro comprado com sucesso!", Alert.AlertType.INFORMATION).showAndWait();
            }else
                 Alerta.criarAlerta("Erro ao comprar carro", "Verifique se a placa já não existe no sistema!", Alert.AlertType.INFORMATION).showAndWait();
        } else 
            exibirErros();
    }

    @FXML
    private void btnAtualizar(ActionEvent event) {
        if(verificarErros()){
            if(new controller.CarroController().atualizarCarro(popularObj())){
                limparCampos();
                Alerta.criarAlerta("Sucesso ao atualizar carro", "Carro atualizado com sucesso!", Alert.AlertType.INFORMATION).showAndWait();
            }else
                Alerta.criarAlerta("Erro ao atualizar o carro", "Verifique se o carro esta disponivel no sistema!", Alert.AlertType.INFORMATION).showAndWait();
        }else
            exibirErros();
    }
    
    private Carro popularObj(){
            Carro carro = new Carro();
            carro.setTipo(comboTipo.getSelectionModel().getSelectedItem().toString());
            carro.setTanque(comboTanque.getSelectionModel().getSelectedItem().toString());
            carro.setMarca(txtMarca.getText().toUpperCase());
            carro.setModelo(txtModelo.getText().toUpperCase());
            carro.setValor(new Valor().limpaValor(txtValor.getText()));
            carro.setPlaca(txtPlaca.getText());
            carro.setKm(new Valor().limpaValor(txtKm.getText()));
            carro.setAno(Integer.valueOf(txtAno.getText()));
            return  carro;
    }
    
    private boolean verificarErros(){
        List<Valida> listaText = listaValida.stream().filter(c -> c.valida() != true).collect(Collectors.toList());
        if (listaText.size() == 0)
            return true;
        return false;
    }
    
    private void exibirErros(){
        Alerta.criarAlertaErroCampo("Campo com erros", listaValida.stream().filter(c -> c.valida() != true).collect(Collectors.toList()),Alert.AlertType.INFORMATION).showAndWait();
    }
    
    private void limparCampos(){
                 listaValida.forEach(c -> c.limpa());
    }
}
