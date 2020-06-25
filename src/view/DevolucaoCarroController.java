/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.CarroController;
import controller.RetirarController;
import dao.RetirarDao;
import dao.TipoTanqueDAO;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import mask.Mask;
import mask.TypeMask;
import model.Carro;
import model.Cliente;
import model.Retirada;
import model.ValidaCombo;
import model.ValidaText;
import model.abs.Valida;
import objView.CarroView;
import utils.Alerta;
import utils.Componentes;
import utils.Valor;

/**
 * FXML Controller class
 *
 * @author Rafan
 */
public class DevolucaoCarroController implements Initializable {

    private JFXComboBox<String> comboTanque;
    @FXML
    private JFXTextField txtPlacaDevolve;
    @FXML
    private JFXTextField txtKmDevolve;
    @FXML
    private JFXComboBox<String> comboTanqueDevolve;
    @FXML
    private JFXTextField txtPlacaRetira;
    @FXML
    private JFXTextField txtCPFRetira;
    private List<Valida> listaValidaDevolucao = new ArrayList<>();
    private List<Valida> listaValidaRetirada = new ArrayList<>();
    private Componentes componente = new Componentes();
    private Cliente clienteTemp;
    private CarroView carroTemp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new TipoTanqueDAO().getTiposDeTanques().forEach(c -> comboTanqueDevolve.getItems().add(c.getDescricao()));
        String valida = "Q,W,E,R,T,Y,U,I,O,P,A,S,D,F,G,H,J,K,L,Ç,Z,X,C,V,B,N,M,1,2,3,4,5,6,7,8,9,0";

        Mask.maskStatic(txtCPFRetira, TypeMask.CPF, "1,2,3,4,5,6,7,8,9,0");
        Mask.maskStatic(txtPlacaDevolve, TypeMask.PLACA_CARRO, valida);
        Mask.maskStatic(txtPlacaRetira, TypeMask.PLACA_CARRO, valida);
        Mask.maskPrivate(txtKmDevolve, new Locale("pt", "BR"), 8, "1,2,3,4,5,6,7,8,9,0");

        listaValidaRetirada.add(new ValidaText(txtCPFRetira, 11, "CPF"));
        listaValidaRetirada.add(new ValidaText(txtPlacaRetira, 8, "Placa"));

        listaValidaDevolucao.add(new ValidaText(txtKmDevolve, 0, "Km"));
        listaValidaDevolucao.add(new ValidaText(txtPlacaDevolve, 8, "Placa"));
        listaValidaDevolucao.add(new ValidaCombo(comboTanqueDevolve, "Tanque"));

        txtCPFRetira.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) {
                List<Cliente> lista = new controller.ClienteController().listarClientes("", txtCPFRetira.getText());
                if (lista.size() == 0) {
                    componente.erroTextFiled(txtCPFRetira, "CPF invalido");
                } else {
                    if (!lista.get(0).getCpf().equals(txtCPFRetira.getText())) {
                        componente.erroTextFiled(txtCPFRetira, "CPF invalido");
                    } else {
                        txtCPFRetira.setStyle("-fx-focus-color:blue;");
                        clienteTemp = lista.get(0);
                    }
                }
            }
        });

        txtPlacaDevolve.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) {
                List<CarroView> lista = new controller.CarroController().buscarCarros(new Carro("", "", txtPlacaDevolve.getText()));
                if (lista.size() == 0) {
                    componente.erroTextFiled(txtPlacaDevolve, "Placa invalida");
                } else {
                    if (!lista.get(0).getPlaca().equals(txtPlacaDevolve.getText())) {
                        componente.erroTextFiled(txtPlacaDevolve, "Placa invalida");
                    } else {
                        txtPlacaDevolve.setStyle("-fx-focus-color:blue;");
                        carroTemp = lista.get(0);
                    }
                }
            }
        });

        txtPlacaRetira.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue) {
                List<CarroView> lista = new controller.CarroController().buscarCarros(new Carro("", "", txtPlacaRetira.getText()));
                if (lista.size() == 0) {
                    componente.erroTextFiled(txtPlacaRetira, "Placa invalida");
                } else {
                    if (!lista.get(0).getPlaca().equals(txtPlacaRetira.getText())) {
                        componente.erroTextFiled(txtPlacaRetira, "Placa invalida");
                    } else {
                        txtPlacaRetira.setStyle("-fx-focus-color:blue;");
                        carroTemp = lista.get(0);
                    }
                }
            }
        });

    }

    @FXML
    private void btnDevolver(ActionEvent event) {
        if (componente.verificaErros(listaValidaDevolucao)) {
            SortedMap<String, Retirada> mapa = new RetirarController()
                    .listarRetirada(new Carro("", "", carroTemp.getPlaca()), new Cliente("", ""));
            if (mapa.size() > 0) {
                Retirada retirada = mapa.get(carroTemp.getPlaca());
                if (retirada != null) {
                    if (!retirada.getStatus()) {
                        Carro carroD = new Carro();
                        carroD.setId(retirada.getCarro().getId());
                        carroD.setPlaca(txtPlacaDevolve.getText());
                        carroD.setKm(new Valor().limpaValor(txtKmDevolve.getText()));
                        carroD.setTanque(comboTanqueDevolve.getSelectionModel().getSelectedItem().toString());
                        carroD.setDataAluguelFinal(LocalDate.now());
                        if (new controller.CarroController().devolverCarro(carroD, retirada.getCarro().getStatus())) {
                            limpa(listaValidaDevolucao);
                            if (retirada.getCarro().getValorDiaria() != null) {
                                long qtdDias = ChronoUnit.DAYS.between(
                                        retirada.getCarro().getDataAluguelInicial(),
                                        LocalDate.now()
                                );
                                Double valorFinal = qtdDias * retirada.getCarro().getValorDiaria();
                                Alerta.criarAlerta("Sucesso ao devolver carro", "Carro devolvido com sucesso!\nValor final do aluguel: "+valorFinal,
                                        Alert.AlertType.INFORMATION).showAndWait();
                            } else {
                                Alerta.criarAlerta("Sucesso ao devolver carro", "Carro devolvido com sucesso!",
                                        Alert.AlertType.INFORMATION).showAndWait();
                            }
                        } else {
                            Alerta.criarAlerta("Erro ao devolver carro", "Erro ao devolver carro!",
                                    Alert.AlertType.INFORMATION).showAndWait();

                        }
                    } else {
                        Alerta.criarAlerta("Erro ao devolver carro", "Verifique se já retirou esse veiculo!",
                                Alert.AlertType.INFORMATION).showAndWait();
                    }
                } else {
                    Alerta.criarAlerta("Erro ao devolver carro", "Nenhum carro para devolver com essa placa",
                            Alert.AlertType.INFORMATION).showAndWait();
                }
            } else {
                Alerta.criarAlerta("Erro ao devolver carro", "Nenhum carro com essa placa",
                        Alert.AlertType.INFORMATION).showAndWait();
            }
        } else {
            Alerta.criarAlertaErroCampo("Erro nos campos",
                    listaValidaDevolucao, Alert.AlertType.INFORMATION).showAndWait();
        }
    }

    @FXML
    private void btnRetira(ActionEvent event) {
        if (componente.verificaErros(listaValidaRetirada)) {

            SortedMap<String, Retirada> mapa = new RetirarController()
                    .listarRetirada(
                            new Carro("", "", carroTemp.getPlaca()), clienteTemp);
            if (mapa.size() != 0) {
                Retirada retirada = mapa.get(txtPlacaRetira.getText());
                if (retirada != null) {
                    if (retirada.getStatus()) {
                        if (verificaPrazoRetirada(retirada)) {
                            Integer id = retirada.getId();
                            if (new RetirarController().retirarCarro(id)) {
                                limpa(listaValidaRetirada);
                                Alerta.criarAlerta("Sucesso ao rerirar carro", "Carro retirado com sucesso!",
                                        Alert.AlertType.INFORMATION).show();
                            } else {
                                Alerta.criarAlerta("Erro ao rerirar carro", "Não foi possivel retirar o carro",
                                        Alert.AlertType.INFORMATION).show();
                            }
                        } else {
                            Alerta.criarAlerta("Erro ao rerirar carro", "Verifique se a data de retirada ja passou do prazo maximo",
                                    Alert.AlertType.INFORMATION).show();
                        }
                    } else {
                        Alerta.criarAlerta("Erro ao rerirar carro", "Não foi possivel localizar o carro na lista de retirada",
                                Alert.AlertType.INFORMATION).show();
                    }
                } else {
                    Alerta.criarAlerta("Erro ao retirar carro", "Não foi possivel retirar o carro com esse CPF",
                            Alert.AlertType.INFORMATION).show();

                }
            } else {
                Alerta.criarAlerta("Erro ao rerirar carro", "Nenhum carro disponivel",
                        Alert.AlertType.INFORMATION).show();
            }
        } else {
            Alerta.criarAlertaErroCampo("Erro nos campos", listaValidaRetirada, Alert.AlertType.INFORMATION).showAndWait();
        }
    }

    private void limpa(List<Valida> lista) {
        lista.forEach(c -> c.limpa());
    }

    private boolean verificaPrazoRetirada(Retirada retirada) {
        LocalDate dataAtual = LocalDate.now();
        if (retirada.getCarro().getDataAluguelInicial() == null
                && retirada.getCarro().getDataAluguelFinal() == null) {
            return true;
        }
        return !dataAtual.isBefore(retirada.getCarro().getDataAluguelInicial()) && !dataAtual.isAfter(retirada.getCarro().getDataAluguelFinal());

    }

}
