/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mask.*;
import model.ValidaText;
import utils.Alerta;
import controller.*;
import dao.ClienteDao;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import model.Cliente;
import model.abs.Valida;

/**
 * FXML Controller class
 *
 * @author Rafan
 */
public class ClienteController implements Initializable {

    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtCPF;
    @FXML
    private TableView<Cliente> tblCliente;
    @FXML
    private TableColumn<Cliente, String> collNome;
    @FXML
    private TableColumn<Cliente, String> collCPF;
    @FXML
    private TableColumn<Cliente, String> collModeloCarro;
    @FXML
    private TableColumn<Cliente, String> collPlaca;

    private List<Valida> listaValida = new ArrayList<>();

    private int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        collNome.setCellValueFactory(new PropertyValueFactory("nome"));
        collCPF.setCellValueFactory(new PropertyValueFactory("cpf"));
        collModeloCarro.setCellValueFactory(new PropertyValueFactory("modeloCarro"));
        collPlaca.setCellValueFactory(new PropertyValueFactory("placaCarro"));
        tblCliente.setOnMouseClicked(MouseEvent -> {
            if (MouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                Cliente cliente = tblCliente.getSelectionModel().getSelectedItem();
                if (cliente == null) {
                    return;
                }
                id = cliente.getId();
                txtCPF.setText(cliente.getCpf());
                txtNome.setText(cliente.getNome());
                txtNome.requestFocus();
                tblCliente.requestFocus();
            }
        });

        popularTabela(new ClienteDao().listarClientes(new Cliente("", "")));
        listaValida.add(new ValidaText(txtNome, 5, "Nome"));
        listaValida.add(new ValidaText(txtCPF, 11, "CPF"));
        Mask.maskStatic(txtCPF, TypeMask.CPF, "1,2,3,4,5,6,7,8,9,0");
    }

    @FXML
    private void btnCadastrar(ActionEvent event) {
        Long qtdErros = listaValida.stream().filter(c -> c.valida() != true).count();
        if (qtdErros > 0) {
            Alerta.criarAlertaErroCampo("Campos com erros", listaValida, AlertType.INFORMATION).showAndWait();
        } else {
            Boolean result = new controller.ClienteController()
                    .inserirCliente(txtNome.getText(), txtCPF.getText());
            if (result) {
                popularTabela(new controller.ClienteController().listarClientes("", ""));
                Alerta.criarAlerta("Sucesso", "Cadastro realizado com sucesso", AlertType.INFORMATION).showAndWait();
            } else {
                Alerta.criarAlerta("Erro ao cadastrar", "Verifique se esse usuario ja não existe", AlertType.INFORMATION).showAndWait();
            }
            listaValida.forEach(c -> c.limpa());
        }
    }

    @FXML
    private void btnBuscar(ActionEvent event) {
        popularTabela(new controller.ClienteController().listarClientes(txtNome.getText(), txtCPF.getText()));
    }

    @FXML
    private void btnRemover(ActionEvent event) {
        Long qtdErros = listaValida.stream().filter(c -> c.valida() != true && c.getComponente() == txtCPF).count();
        if (qtdErros > 0) {
            Alerta.criarAlerta("Campos CPF errado", "Verifique o campo\ncpf minimo 11", AlertType.ERROR).showAndWait();
        } else {
            Alert alerta = Alerta.criarAlerta("Excluir cliente", "Deseja mesmo excluir esse cliente?", AlertType.CONFIRMATION);
            Optional<ButtonType> result = alerta.showAndWait();

            if (result.get() == ButtonType.OK) {
                String cpf = "";

                Boolean resultDao = new controller.ClienteController()
                        .excluirCliente(txtCPF.getText());
                if (resultDao) {
                    Alerta.criarAlerta("Sucesso", "Cliente excluido com sucesso", AlertType.INFORMATION).showAndWait();
                } else {
                    Alerta.criarAlerta("Erro ao cadastrar", "Verifique se esse usuario ja não existe", AlertType.INFORMATION).showAndWait();
                }

            }
            listaValida.forEach(c -> c.limpa());
            popularTabela(new controller.ClienteController().listarClientes("", ""));
        }
    }

    @FXML
    private void btnAtualizar(ActionEvent event) {
        if (id != 0) {
            Long qtdErros = listaValida.stream().filter(c -> c.valida() != true && c.getComponente() == txtCPF).count();
            if (qtdErros > 0) {
                Alerta.criarAlerta("Campos errado", "Verifique o campo\nnome minimo 5, cpf minimo 11", AlertType.ERROR).showAndWait();
            } else {
                Alert alerta = Alerta.criarAlerta("Atualizar cliente", "Deseja mesmo atualizar esse cliente?", AlertType.CONFIRMATION);
                Optional<ButtonType> result = alerta.showAndWait();

                if (result.get() == ButtonType.OK) {
                    Boolean resultDao = new controller.ClienteController()
                            .atualizarCliente(id, txtNome.getText(), txtCPF.getText());
                    if (resultDao) {
                        Alerta.criarAlerta("Sucesso", "Cliente atualizado com sucesso", AlertType.INFORMATION).showAndWait();
                    } else {
                        Alerta.criarAlerta("Erro ao atualizar", "Verifique se esse usuario esta ativo", AlertType.INFORMATION).showAndWait();
                    }

                }
                id = 0;
                listaValida.forEach(c -> c.limpa());
                popularTabela(new controller.ClienteController().listarClientes("", ""));
            }
        } else {
            Alerta.criarAlerta("Erro ao atualizar", "Selecione o cliente na tabela", AlertType.INFORMATION).showAndWait();
        }
    }

    private void popularTabela(List<Cliente> lista) {
        tblCliente.getItems().clear();
        tblCliente.setItems(FXCollections.observableArrayList(lista));
    }
}
