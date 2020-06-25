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
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import mask.Mask;
import mask.TypeMask;
import utils.Valor;

/**
 * FXML Controller class
 *
 * @author Rafan
 */
public class CaixaController implements Initializable {

    @FXML
    private JFXTextField txtValorCaixa;
    @FXML
    private JFXTextField txtCarrosVendios;
    @FXML
    private JFXTextField txtCarrosAlugados;
    @FXML
    private PieChart pieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Double valorCaixa = Double.sum(new controller.CaixaController().buscarSaldoAlugueis(),
                new controller.CaixaController().buscarSaldoCarrosVendidos());
        valorCaixa = valorCaixa - new controller.CaixaController().buscarSaldoCarrosComprados();

        Long qtdCarrosAlugados = new controller.CaixaController().buscarQtdCarrosAlugados();

        Long qtdCarrosVendidos = new controller.CaixaController().buscarQtdCarrosVendidos();
        
        txtCarrosAlugados.setText(qtdCarrosAlugados.toString());
        txtCarrosVendios.setText(qtdCarrosVendidos.toString());
        txtValorCaixa.setText(new Valor().formataValor(valorCaixa));
        
        pieChart.setTitle("Grafico");
        List<PieChart.Data> lista = new ArrayList<>();
        lista.add(new PieChart.Data("Vendidos", qtdCarrosVendidos));
        lista.add(new PieChart.Data("Alugados", qtdCarrosAlugados));
        
        pieChart.setData(FXCollections.observableArrayList(lista));
        
        for(PieChart.Data data : pieChart.getData()){
            Tooltip.install(data.getNode(), new Tooltip("Qtd: "+data.getPieValue()));
            data.getNode().setOnMouseEntered(event -> data.getNode().getStyleClass().add("onHover"));
            data.getNode().setOnMouseExited(event -> data.getNode().getStyleClass().add("onHover"));
        }
    }

}
