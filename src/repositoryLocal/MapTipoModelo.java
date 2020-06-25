/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositoryLocal;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import model.TipoModelo;
import model.TipoTanque;

/**
 *
 * @author Rafan
 */
public class MapTipoModelo {

    private static Map<String, TipoModelo> mapTipoModelo = new TreeMap<>();

    public static Map<String, TipoModelo> getMapTipoModelo() {
        return mapTipoModelo;
    }

    public static void setMapTipoModelo(Map<String, TipoModelo> mapTipoModelo) {
        MapTipoModelo.mapTipoModelo = mapTipoModelo;
    }
}
