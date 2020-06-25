/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controller.AlugarCarroController;
import dao.VendaDao;
import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import mask.Mask;
import mask.TypeMask;
import model.Carro;
import model.CarroAluguel;
import model.Cliente;
import model.ValidaDataPicker;
import model.ValidaText;
import model.abs.Valida;
import objView.CarroView;
import utils.Alerta;
import utils.Valor;

/**
 * FXML Controller class
 *
 * @author Rafan
 */
public class VendaRetiradaCarroController implements Initializable {

    @FXML
    private JFXTextField txtCPF;
    @FXML
    private JFXTextField txtPlacaVenda;
    @FXML
    private JFXTextField txtValor;
    @FXML
    private JFXTextField txtPlacaAluga;
    @FXML
    private JFXTextField txtCPFAluga;
    @FXML
    private JFXDatePicker dataIni;
    @FXML
    private JFXDatePicker dataFim;
    @FXML
    private JFXTextField txtValorDiaria;

    private List<Valida> listaValidaVenda = new ArrayList<>();
    private List<Valida> listaValidaAluga = new ArrayList<>();

    private Cliente clienteTemp;
    private CarroView carroTemp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String valida = "Q,W,E,R,T,Y,U,I,O,P,A,S,D,F,G,H,J,K,L,Ç,Z,X,C,V,B,N,M,1,2,3,4,5,6,7,8,9,0";

        Mask.maskStatic(txtCPF, TypeMask.CPF, "1,2,3,4,5,6,7,8,9,0");
        Mask.maskStatic(txtPlacaVenda, TypeMask.PLACA_CARRO, valida);
        Mask.dynamicMask(txtValor, 15, "1,2,3,4,5,6,7,8,9,0", new Locale("pt", "BR"));
        Mask.maskStatic(txtCPFAluga, TypeMask.CPF, "1,2,3,4,5,6,7,8,9,0");
        Mask.maskStatic(txtPlacaAluga, TypeMask.PLACA_CARRO, valida);
        Mask.dynamicMask(txtValorDiaria, 15, "1,2,3,4,5,6,7,8,9,0", new Locale("pt", "BR"));

        listaValidaVenda.add(new ValidaText(txtCPF, 11, "CPF"));
        listaValidaVenda.add(new ValidaText(txtPlacaVenda, 8, "Placa do carro"));
        listaValidaVenda.add(new ValidaText(txtValor, 7, "Preço"));
        listaValidaAluga.add(new ValidaText(txtPlacaAluga, 8, "Placa do Carro"));
        listaValidaAluga.add(new ValidaText(txtCPFAluga, 11, "CPF"));
        listaValidaAluga.add(new ValidaText(txtValorDiaria, 5, "valor da diaria"));
        listaValidaAluga.add(new ValidaDataPicker(dataIni, "Inicio"));
        listaValidaAluga.add(new ValidaDataPicker(dataFim, "Fim"));

        txtCPF.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) {
                List<Cliente> lista = new controller.ClienteController().listarClientes("", txtCPF.getText());
                if (lista.size() == 0) {
                    alteraTextField(txtCPF, "CPF invalido");
                } else {
                    if (!lista.get(0).getCpf().equals(txtCPF.getText())) {
                        alteraTextField(txtCPF, "CPF invalido");
                    } else {
                        txtCPF.setStyle("-fx-focus-color:blue;");
                        clienteTemp = lista.get(0);
                    }
                }
            }
        });

        txtCPFAluga.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) {
                List<Cliente> lista = new controller.ClienteController().listarClientes("", txtCPFAluga.getText());
                if (lista.size() == 0) {
                    alteraTextField(txtCPFAluga, "CPF invalido");
                } else {
                    if (!lista.get(0).getCpf().equals(txtCPFAluga.getText())) {
                        alteraTextField(txtCPFAluga, "CPF invalido");
                    } else {
                        clienteTemp = lista.get(0);
                    }
                }
            }
        });

        txtPlacaVenda.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) {
                List<CarroView> lista = new controller.CarroController().buscarCarros(new Carro("", "", txtPlacaVenda.getText()));
                if (lista.size() == 0) {
                    alteraTextField(txtPlacaVenda, "Placa invalida");
                } else {
                    if (!lista.get(0).getPlaca().equals(txtPlacaVenda.getText())) {
                        alteraTextField(txtPlacaVenda, "Placa invalida");
                    } else {
                        carroTemp = lista.get(0);
                    }
                }
            }
        });

        txtPlacaAluga.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) {
                List<CarroView> lista = new controller.CarroController().buscarCarros(new Carro("", "", txtPlacaAluga.getText()));
                if (lista.size() == 0) {
                    alteraTextField(txtPlacaAluga, "Placa invalida");
                } else {
                    if (!lista.get(0).getPlaca().equals(txtPlacaAluga.getText())) {
                        alteraTextField(txtPlacaAluga, "Placa invalida");
                    } else {
                        carroTemp = lista.get(0);
                    }
                }
            }
        });

    }

    @FXML
    private void btnVenda(ActionEvent event) {
        if (verifica(listaValidaVenda)) {
            if (carroTemp.getDisponivel().toUpperCase().equals("DISPONIVEL")) {
                Carro carro = new Carro();
                carro.setId(carroTemp.getId());
                carro.setValor(new Valor().limpaValor(txtValor.getText()));
                if (new controller.VendaController().venderCarro(carro, clienteTemp)) {
                    limpa(listaValidaVenda);
                    Alerta.criarAlerta("Sucesso na venda", "Carro vendido com sucesso!",
                            Alert.AlertType.INFORMATION).showAndWait();
                } else {
                    Alerta.criarAlerta("Erro ao vender", "Verifique se o carro esta disponivel!",
                            Alert.AlertType.INFORMATION).showAndWait();
                }
            } else {
                Alerta.criarAlerta("Carro não disponivel", "Este carro não esta disponivel"
                        + "no sistema!", Alert.AlertType.INFORMATION).showAndWait();
            }
        } else {
            Alerta.criarAlertaErroCampo("Erro nos campos", listaValidaVenda.stream()
                    .filter(c -> c.valida() != true)
                    .collect(Collectors.toList()), Alert.AlertType.INFORMATION).showAndWait();
        }
    }

    @FXML
    private void btnAlugar(ActionEvent event) {
        if (verifica(listaValidaAluga)) {
            if (!dataFim.getValue().isBefore(dataIni.getValue())){
                if (carroTemp.getDisponivel().toUpperCase().equals("DISPONIVEL")) {
                    CarroAluguel carro = new CarroAluguel();
                    carro.setId(carroTemp.getId());
                    carro.setDiaria(new Valor().limpaValor(txtValorDiaria.getText()));
                    carro.setDataInicial(dataIni.getValue());
                    carro.setDataFinal(dataFim.getValue());
                    if (new AlugarCarroController().AlugarCarro(carro, clienteTemp)) {
                        limpa(listaValidaAluga);
                        Alerta.criarAlerta("Sucesso no aluguel", "Carro alugado com sucesso!",
                                Alert.AlertType.INFORMATION).showAndWait();
                    } else {
                        Alerta.criarAlerta("Erro ao alugar", "Verifique se o carro esta disponivel!",
                                Alert.AlertType.INFORMATION).showAndWait();
                    }
                } else {
                    Alerta.criarAlerta("Carro não disponivel", "Este carro não esta disponivel"
                            + "no sistema!", Alert.AlertType.INFORMATION).showAndWait();
                }
            } else {
                Alerta.criarAlerta("Erro nas datas", "Data inicio maior que a final", Alert.AlertType.INFORMATION).showAndWait();
            }
        } else {
            Alerta.criarAlertaErroCampo("Erro nos campos",
                    listaValidaAluga.stream()
                            .filter(c -> c.valida() != true)
                            .collect(Collectors.toList()), Alert.AlertType.INFORMATION).showAndWait();
        }

    }

    private boolean verifica(List<Valida> lista) {
        return lista.stream().filter(c -> c.valida() != true).count() == 0;
    }

    private void alteraTextField(TextField textField, String msg) {
        textField.setTooltip(new Tooltip(msg));
        textField.setText("");
        textField.setStyle("-fx-prompt-text-fill: red;");
    }

    private void limpa(List<Valida> lista) {
        lista.forEach(c -> c.limpa());
    }
}
