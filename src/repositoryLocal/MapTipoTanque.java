/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositoryLocal;

import java.util.HashMap;
import java.util.Map;
import model.TipoTanque;

/**
 *
 * @author Rafan
 */
public class MapTipoTanque {

    private static Map<String, TipoTanque> mapTipoTanque = new HashMap<>();

    public static Map<String, TipoTanque> getMapTipoTanque() {
        return mapTipoTanque;
    }

    public static void setMapTipoTanque(Map<String, TipoTanque> mapTipoTanque) {
        MapTipoTanque.mapTipoTanque = mapTipoTanque;
    }
}
