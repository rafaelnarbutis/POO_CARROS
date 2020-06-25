/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositoryLocal;

import java.util.HashMap;
import java.util.Map;
import model.TipoCarro;
import model.TipoMarca;

/**
 *
 * @author Rafan
 */
public class MapTipoMarca {
     private static Map<String, TipoMarca> mapTipoMarca = new HashMap<>();

    public static Map<String, TipoMarca> getMapTipoMarca() {
        return mapTipoMarca;
    }

    public static void setMapTipoMarca(Map<String, TipoMarca> mapTipoMarcas) {
        MapTipoMarca.mapTipoMarca = mapTipoMarcas;
    }
}
