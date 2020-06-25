/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositoryLocal;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import javafx.collections.transformation.SortedList;
import model.TipoModelo;
import model.TipoStatusCarro;

/**
 *
 * @author Rafan
 */
public class MapTipoStatusCarro {
      private static SortedMap<String, TipoStatusCarro> mapTipoStatusCarro = new TreeMap<>();

    public static SortedMap<String, TipoStatusCarro> getMapTipoModelo() {
        return mapTipoStatusCarro;
    }

    public static void setMapTipoModelo(SortedMap<String, TipoStatusCarro> mapTipoStatusCarro) {
        MapTipoStatusCarro.mapTipoStatusCarro = mapTipoStatusCarro;
    }
}
