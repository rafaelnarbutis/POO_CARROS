/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositoryLocal;

import java.util.HashMap;
import java.util.Map;
import model.TipoCarro;

/**
 *
 * @author Rafan
 */
public class MapTipoCarro {
    private static Map<String, TipoCarro> mapTipocarros = new HashMap<>();

    public static Map<String, TipoCarro> getMapTipocarros() {
        return mapTipocarros;
    }

    public static void setMapTipocarros(Map<String, TipoCarro> mapTipocarros) {
        MapTipoCarro.mapTipocarros = mapTipocarros;
    }
    
}
