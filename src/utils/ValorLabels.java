/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Label;

/**
 *
 * @author Rafan
 */
public class ValorLabels {
    private static Map<String, Label> mapLabels = new HashMap<>();

    public static Map<String, Label> getMaptLabels() {
        return mapLabels;
    }

    public static void setMapLabels(Map<String, Label> mapLabels) {
        ValorLabels.mapLabels = mapLabels;
    }

}
