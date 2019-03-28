package sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import data.SampleClass;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * source : https://stackoverflow.com/questions/41460739/json-to-java-hashmap-using-complex-key
 */
public class Main {


    // 1er point : 3 janvier 2002
    // 2e point : 15 juillet 2018
    static String mapInJson = "{\n" +
            "  \"map\" : [ {\n" +
            "    \"key\" : {\n" +
            "      \"x\" : -3.0,\n" +
            "      \"y\" : -1.0\n" +
            "    },\n" +
            "    \"value\" : 1010000000000\n" +
            "  }, {\n" +
            "    \"key\" : {\n" +
            "      \"x\" : 15.0,\n" +
            "      \"y\" : 7.0\n" +
            "    },\n" +
            "    \"value\" : 1531641463000\n" +
            "  } ],\n" +" \"anotherMap\" : {\n" +
            "    \"java.awt.Point[x=24,y=12]\" : 1553761742128,\n" +
            "    \"java.awt.Point[x=42,y=7]\" : 1553727600000\n" +
            "  },"+
            "  \"otherValue\" : \"ready to reload\"\n" +
            "};";

    public static void main(String [] args) throws ParseException, IOException {

        SimpleDateFormat df = new SimpleDateFormat("dd-M-yyyy");

        SampleClass toJSON = new SampleClass();
        toJSON.setOtherValue("just an exmample");

        toJSON.getMap().put(new Point(24,12), new Date());
        toJSON.getMap().put(new Point(42,7), df.parse("28-03-2019"));
        toJSON.getAnotherMap().put(new Point(24,12), new Date());
        toJSON.getAnotherMap().put(new Point(42,7), df.parse("28-03-2019"));

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(toJSON);

        System.out.println(json);



        ObjectMapper mapper = new ObjectMapper();
        SampleClass sample = mapper.readValue(mapInJson , SampleClass.class);

        System.out.println("*************** loaded SampleClass Object ***************");

        System.out.println("*************** Map : map");

        for(Map.Entry<Point,Date> element: sample.getMap().entrySet()) {
            System.out.println("point = "+element.getKey()+ " de type "+element.getKey().getClass());
            System.out.println("date = "+element.getValue()+ " de type "+element.getValue().getClass());
        }
        System.out.println("*************** Map : anotherMap");

        for(Map.Entry<Object,Date> element: sample.getAnotherMap().entrySet()) {
            System.out.println("object = "+element.getKey()+ " de type "+element.getKey().getClass());
            System.out.println("date = "+element.getValue()+ " de type "+element.getValue().getClass());
        }

        System.out.println("*************** String : otherValue");
        System.out.println("otherValue = "+sample.getOtherValue());

    }

}
