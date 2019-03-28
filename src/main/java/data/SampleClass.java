package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import maptojson.JSONToMap;
import maptojson.MapToJSON;

import java.awt.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// annotation pour l'exemple (montre la conversion en chaine de caractère pour une Map (anotherMap), dans l'autre sens, il faut faire un Deserializer).
@JsonIgnoreProperties(ignoreUnknown = true)
public class SampleClass {

    @JsonSerialize(using = MapToJSON.class)
    @JsonDeserialize(using = JSONToMap.class)
    private Map<Point, Date> map ;



    // JSON : Object => String
    // Key : Object (pour pouvoir manipuler dans les deux sens Java <--> JSON
    private Map<Object, Date> anotherMap ;

    private String otherValue;



    // constructeur vide important pour jackson
    public SampleClass() {
        otherValue = "";
        map = new HashMap<>();
        anotherMap = new HashMap<>();
    }


    /************ getter & setter *************/

    public Map<Point, Date> getMap() {
        return map;
    }

    public void setMap(Map<Point, Date> map) {
        this.map = map;
    }


    public Map<Object, Date> getAnotherMap() {
        return anotherMap;
    }

    public void setAnotherMap(Map<Object, Date> anotherMap) {
        this.anotherMap = anotherMap;
    }


    public String getOtherValue() {
        return otherValue;
    }

    public void setOtherValue(String otherValue) {
        this.otherValue = otherValue;
    }

}
